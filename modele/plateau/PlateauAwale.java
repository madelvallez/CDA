package modele.plateau;

import modele.joueurs.Joueur;
import modele.joueurs.JoueurAwale;

import java.util.ArrayList;
import java.util.List;

public class PlateauAwale implements Plateau{

    private int[][] grille;
    private int largeur = 6; //nombre de puis par champ 6 dans le jeu original

    private PlateauAwale copie(){
        /*
        renvoie un plateau avec le même contenu que this
         */
        PlateauAwale cop = new PlateauAwale();
        cop.largeur = this.largeur;
        for(int ligne =0; ligne<=2; ligne++){
            for(int puis=0; puis<this.largeur; puis++){
                cop.grille[ligne][puis] = this.grille[ligne][puis];
            }
        }
        return cop;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void initialiser() {
        /*
        met tout les puis à 4
         */
        for (int ligne=0; ligne <= 2 ; ligne++){
            for (int puis=0; puis < this.largeur; puis++){
                this.grille[ligne][puis] = 4;
            }
        }
    }

//--------------- analyseCoup ----------------------------------------------------
    private CoupAwale analyseCoup(int ligne , int puis){
        /*
        renvoie le coup (coord, capture) correspondant au choix (ligne, puis)
        ou le coupIllegal si le coup n'est pas légal (coordonnées illegales, puis vide, affame adversaire)
         */
        //verification de la légalité des valeurs
        if(0<=ligne && ligne<2 && 0<=puis && puis<this.largeur ){
            return CoupAwale.coupIllegal();
        }
        if (this.grille[ligne][puis] == 0){
            return CoupAwale.coupIllegal();
        }
        CoupAwale coup = new CoupAwale(ligne, puis);
        PlateauAwale simulation = simuleAppliqueCoup(coup);
        if(simulation.affameAdversaire(ligne)){
            return CoupAwale.coupIllegal();
        }
        return coup ;
    }

    private PlateauAwale simuleAppliqueCoup(CoupAwale coup){
        /*
        applique le coup sur une copie de this et renvoie le résultat
         */
        PlateauAwale simulation = this.copie();
        simulation.appliqueCoup(coup);
        return simulation;
    }

    private boolean affameAdversaire(int champJoueur){
        /*
        teste si l'adversaire (joueur du chzmp opposé au champ donné) est affamé sur le plateau this
         */
        int champAdv = (champJoueur+1)/2;
        for (int puis : this.grille[champAdv]){
            if (puis!=0){
                return false;
            }
        }
        return true;
    }

//---- listeCoupPossibles -----------------------------------------------------------------------
    @Override
    public List<Coup> listeCoupsPossibles(Joueur j) {
        /*
        renvoie la liste des coup possibles (= légaux ) pour le joueur j
         */
        List<Coup> coupsPossibles = new ArrayList<Coup>();
        int ligne = ((JoueurAwale)j).getLigne();
        for (int puis=0; puis < this.largeur; puis++){
            Coup c = analyseCoup(ligne, puis);
            if(!c.isIllegal()){
                coupsPossibles.add(c);
            }
        }
        return coupsPossibles; //------------------------------------------------------------------------------
    }

//---- appliqueCoup ---------------------------------------------------------------------------------
    @Override
    public void appliqueCoup(Coup coup) {
        /*
        applique le coup coup au plateau this
         */
        CoupAwale c = (CoupAwale) coup;
        int ligne = c.getLigne();
        int i = c.getPuis();
        int prise = this.grille[ligne][i];
        while (prise != 0) {
            int[] pasSuivant = avancer(ligne, i);
            ligne = pasSuivant[0];
            i = pasSuivant[1];
            if (estCaseDepart(ligne, i, c)) { //si on est sur la case de départ, il ne faut pas deposer de graine
                pasSuivant = avancer(ligne, i);
                ligne = pasSuivant[0];
                i = pasSuivant[1];
            }
            this.grille[ligne][i]++;
            prise--;
        }
        int capture = capturer(ligne, i, c.getLigne());
        //la ligne identifie le joueur car il ne peut prendre que dans son champ
        c.ajouterCapture(capture);
    }

    private int[] avancer(int x, int y){
        /*
        renvoie dans un int[] la position suivant celle donnée par x et y
        */
        y++;
        if (y==this.largeur){
            y=0;
            x= (x+1)/2; //changement de champ
        }
        return new int[]{x,y};
    }

    private boolean estCaseDepart(int x, int y, Coup coup){
        /*
        teste si la case (x,y) est la case où on a joué (= pris les graines)
         */
        CoupAwale c = (CoupAwale)coup;
        return (x== c.getLigne()) && (y== c.getPuis());
    }

    private int[] reculer(int x, int y){
        /*
        renvoie dans un int[] la position prédédant celle donnée par x et y
        */
        y--;
        if (y== -1){
            y=this.largeur -1;
            x= (x+1)/2; //changement de champ (de joueur)
        }
        return new int[]{x,y};
    }

    private int capturer(int ligne, int puis, int ligneJoueur){
        /*
        effectue la capture après le semage des graines prises et renvoie le nombre de graines raflées
         */
        int capture = 0;
        boolean continuer = true;
        while (ligneJoueur == ligne && continuer){
            if (this.grille[ligne][puis] == 2 || this.grille[ligne][puis]==3){ //si il a une quantité attrapable
                capture += this.grille[ligne][puis];
                this.grille[ligne][puis] = 0;
            }
            else{
                continuer = false;
            }
            int[] pasPrecedent = reculer(ligne, puis);
            ligne = pasPrecedent[0];
            puis = pasPrecedent[1];
        }
        return capture;
    }

//---- score ----------------------------------------------------------------------------------------
    @Override
    public int score(Joueur j) {
        // QUEL SENS ???? : nombre de graine dans le camp du joueur donné (interet pour fin de partie)
        int ligne  = ((JoueurAwale)j).getLigne();
        int scoreJ = 0;
        for (int puis =0; puis <= this.largeur; puis++){
            scoreJ+=this.grille[ligne][puis];
        }
        return scoreJ;
    }

//---- estFiniePartie -----------------------------------------------------------------------------
    private int scoreParIndice(int ligne){
        int scoreJ = 0;
        for (int puis =0; puis <= this.largeur; puis++){
            scoreJ+=this.grille[ligne][puis];
        }
        return scoreJ;
    }

    @Override
    public boolean estFiniePartie() {
        /*
        teste si la partie est finie (=score de l'un des deux joueurs est 0)
         */
        return scoreParIndice(0)==0 || scoreParIndice(1)==0 ;
    }
}
