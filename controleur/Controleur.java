package controleur;

import modele.Coup;
import modele.Plateau;
import vue.Ihm;
import modele.Joueur;

import java.util.ArrayList;
import java.util.Arrays;

import static modele.Plateau.BLANC;
import static modele.Plateau.NOIR;

public class Controleur {
    private Ihm ihm;
    private Plateau plateau;
    private final int dim = 8;

    private Joueur joueur;
    private Joueur adversaire;
    private final static String[] IADISPO = new String[]{"Naif", "MinMax"};

    public Controleur(Ihm ihm){
        this.ihm = ihm;
        this.plateau = new Plateau(this.dim);

    }

    public void jouer(){

        this.definirJoueur(); //definition et instanciation du joueur (anciennement joueur1)
        this.definirAdversaire();
        boolean veutRejouer = true;
        while(veutRejouer){
            plateau.initialiser();
            Ihm.Message(joueur.getNom()+" joue avec les pions "+ joueur.getCouleur()+" et"+
                            adversaire.getNom()+" joue avec les pions" + adversaire.getCouleur());
            String tour = NOIR;
            while (!this.plateau.estFiniePartie()){
                Coup coupJoue = this.demanderCoup(tour);
                this.jouerCoup(coupJoue); // teste si le coup est -passer- puis l'applique correctement
                tour = tour==NOIR ? BLANC : NOIR; //on passe au joueur suivant
            }
            //annonce victoire et scores de la partie
            int scoreJoueur = this.plateau.score(this.joueur.getCouleur());
            int scoreAdversaire = this.plateau.score(this.adversaire.getCouleur());
            if (scoreJoueur>scoreAdversaire){
                this.joueur.incrementeNbVictoires();
            } else if (scoreJoueur<scoreAdversaire) {
                this.adversaire.incrementeNbVictoires();
            }
            Ihm.AffichageScore(this.joueur.getNom(), this.adversaire.getNom(), scoreJoueur, scoreAdversaire);


            veutRejouer = rejouer();
        }
    }

    private void definirJoueur(){
        String nom1= recupererNom(1);
        this.joueur = new JoueurHumain(nom1,NOIR);
    }
    private String recupererNom(int numJ){
        String nom = Ihm.DemanderNom(numJ);
        if (nom.equals("")){
            return recupererNom(numJ);
        }
        if (numJ==2 && nom.equals(this.joueur.getNom())){
            Ihm.MessageErreur("Tu ne peux pas donner le même nom que ton adversaire");
            return recupererNom(numJ);
        }
        return nom;
    }

    private void definirAdversaire() {
        boolean defAdv = false;
        while (!defAdv) {
            String rep = Ihm.demanderAdversaire();
            if ("non".equals(rep)) {
                String nomAdv = Ihm.DemanderNom(2);
                this.adversaire = new JoueurHumain(nomAdv, BLANC);
                defAdv=true;
            } else if ("oui".equals(rep)) {
                boolean defNivIA = false;
                while (!defNivIA) {
                    String rep2 = Ihm.demanderNiveauIA(IADISPO);
                    if (IADISPO[0].equals(rep2)) {
                        this.adversaire = new JoueurIANaif(IADISPO[0], BLANC);
                        defNivIA = true;
//                    } else if (IADISPO[1].equals(rep2)) {
//                        this.adversaire = new JoueurIAMoyen(IADISPO[1], BLANC);
//                        defNivIA = true;
                    } else {
                        Ihm.MessageErreur("Répondre avec un nom parmis " + Arrays.toString(IADISPO));
                    }
                }
                defAdv=true;
            }
            else{
                Ihm.MessageErreur("Repondre avec oui ou non");
            }
        }
    }

    private Coup demanderCoup(String couleur) {
        ArrayList<Coup> coupsPossibles = this.plateau.listeCoupsPossibles(couleur);
        return joueurCourant(couleur).choisirCoup(coupsPossibles);
    }

    private Joueur joueurCourant(String couleur){
        if(this.joueur.getCouleur().equals(couleur)){
            return this.joueur;
        }
        else{
            return this.adversaire;
        }
    }

    private void jouerCoup(Coup coup){
        if (coup.getX()==-2 && coup.getY()==-2){
            Ihm.Message(this.adversaire.getNom()+" a passé son tour");
        }else{
            this.plateau.appliqueCoup(coup);
        }
    }

    private boolean estCoupValide(int row, int col, ArrayList<Coup> coupsPossibles){
        /*
         * prend les indices pour une ligne et une colonne et la liste des coups possibles
         * teste si le coup (row, col) est légal
         */
        if (0<= row && row <this.dim && 0<=col && col<this.dim) { //test si les coordonnées sont légales
            //teste si le coup est dans la liste des coupsPossibles (donc si il est possible)
            for (Coup coup : coupsPossibles) {
                if (coup.getX() == row && coup.getY() == col) {
                    return true;
                }
            }
            return false;
        }
        else{
            return false;
        }
    }
    private int indiceCoup(int x, int y, ArrayList<Coup> coupsPossibles){
        /*
         * prend les coordonnées d'n coup valide 
         * renvoie le coup correspondant dans la liste
         */
        for(int i =0; i<coupsPossibles.size(); i++){
            Coup coup = coupsPossibles.get(i);
            if (coup.getX() == x && coup.getY()==y){
                return i;
            }
        }
        return -1;
    }

    private boolean rejouer(){
        while (true) {
            String rep = Ihm.MessageRejouer();
            if ("oui".equals(rep)) {
                return true;
            } else if ("non".equals(rep)) {
                return false;
            }else{
                Ihm.ErreurFin();
            }
        }

    }

}

/* ARCHIVE DE CODE QUI PEUT INTERESSER AILLEURS
------TOUR DE JEU HUMAIN-----------------------------------------------------------------------------------------
    if (this.joueur.getCouleur().equals(couleur)) { //si c'est au joueur de jouer
            while (true) {
                Ihm.AfficherPlateau(this.plateau);
                int[] coup = Ihm.DemanderCoup(this.joueur.getNom(), true);
                int row = coup[0];
                int col = coup[1];
                if (this.estCoupValide(row, col, coupsPossibles)) {
                    Coup coupJoue = coupsPossibles.get(indiceCoup(row, col, coupsPossibles));
                    return coupJoue;
                } else if (row == -2 && col==-2 && coupsPossibles.size() == 0) {
                    Ihm.Message(this.joueur.getNom() + " a passé son tour");
                    return new Coup(-2, -2, NOIR); //Coup qui represente passer son tour

                } else {
                    Ihm.MessageErreur("Ce coup n'est pas valide");
                }
            }
        } else { //c'est à l'adversaire de jouer
            return this.adversaire.choisirCoup(coupsPossibles);
        }
-------APPLICATION COUP POUR IA------------------------------------------------------------------------------------
        if (!plateau.estFiniePartie()) {
                    //faire jouer l'adversaire
                    Ihm.AfficherPlateau(this.plateau);
                    ArrayList<Coup> coupsPossibles = this.plateau.listeCoupsPossibles(BLANC);
                    Coup coupAdv = this.adversaire.choisirCoup(coupsPossibles);
                    if(this.adversaire.getIa()) {
                        Ihm.commenterAction_IA(coupAdv.getX(), coupAdv.getY());
                    }else if (coupAdv.getX()== && coupAdv.getY()==-2){
                        Ihm.Message(this.adversaire.getNon()+" a passé son tour");
                    }
                    if(coupAdv.getX()!=-2) {
                        this.plateau.appliqueCoup(coupAdv);
                    }


*/