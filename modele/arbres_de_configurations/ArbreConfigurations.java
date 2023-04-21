package modele.arbres_de_configurations;

import modele.joueurs.Joueur;
import modele.joueurs.JoueurOthello;
import modele.plateau.*;
import java.util.List;

public abstract class ArbreConfigurations implements Comparable<ArbreConfigurations> {
    private int score; //issu du calcul de minMax ou de la fonction d'evaluation
    private PlateauOthello p;//configuration sur laquelle on reflechit (le coup donné est deja joué)
    private Coup c;//coup appliqué pour obtenir cette situation
    private Joueur[] joueurs;//les joueurs
    private int numJ;
    private boolean isMax;//si on cherche à maximiser ou minimiser le score dans ce noeud (vis à vis de ses fils)

    public ArbreConfigurations(PlateauOthello p, Coup c, Joueur[] joueurs, int numJ, boolean isMax){
        this.c = c;
        this.p = p;
        this.isMax= isMax;
        this.joueurs = joueurs;
        this.numJ = numJ;

    }
//    private void calculerScore(int prof) {
//        ArrayList<Coup> coupsPossibles = this.p.listeCoupsPossibles(this.couleur);
//        if (coupsPossibles.size() == 0) { //si pas de coup possible
//            if (this.p.estFiniePartie()) { //si c'est une configuration finale
//                //cherher qui a gagné
//                int nbPionJoueur = this.p.score(this.couleur);
//                int nbPionAdv = this.p.score(PlateauOthello.opposeCouleur(this.couleur));
//                if (nbPionAdv > nbPionJoueur) {
//                    this.score = -1000;
//                } else {
//                    this.score = 1000;
//                }
//            }else{
//                coupsPossibles.add(Coup.coupPasser());
//            }
//        }
//    }
    abstract void minMax( List<Coup> coupsPossibles, int prof);
    public int fonctionEvaluation(){
        int DIM = this.p.getDIM();
        int score = 0;
        Joueur joueur = this.joueurs[numJ];
        String coulOpp= PlateauOthello.opposeCouleur(((JoueurOthello)joueur).getCouleur()); //on évalue du point de vue de la prochaine personne à jouer (et qui ne joue pas)
        for(int i=0; i<DIM; i++){
            for(int j=0; j<DIM; j++){
                if (coulOpp.equals(this.p.getGrille()[i][j])) {
                    if (estUnCoin(i,j,DIM)) {
                        score += 11;
                    } else if (estUnBord(i,j,DIM)) {
                        score += 6;
                    } else {
                        score += 1;
                    }
                }
            }
        }
        return score;
    }

    private boolean estUnCoin(int i, int j, int DIM){
        boolean coinHG = (i == 0) && (j == 0);
        boolean coinHD =  ((i == 0) && (j == DIM - 1));
        boolean coinBG = ((i == DIM - 1) && (j == 0));
        boolean coinBD = ((i == DIM - 1) && (j == DIM - 1));
        return ( coinHG || coinHD || coinBG || coinBD);
    }

    private boolean estUnBord(int i, int j,int DIM){
        boolean enHaut = (i == 0);
        boolean enBas =  (i == DIM - 1);
        boolean aGauche= (j == 0);
        boolean aDroite =  (j == DIM - 1);
        return enHaut || aDroite || enBas|| aGauche;
    }

    public int getScore(){
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getCouleur() {
        return ((JoueurOthello)joueurs[numJ]).getCouleur();
    }

    public Joueur getJoueurCourant(){
        return joueurs[numJ];
    }

    public Joueur[] getJoueurs(){
        return joueurs;
    }

    public int getNumJ() {
        return numJ;
    }

    public PlateauOthello getP() {
        return p;
    }

    public boolean isMax() {
        return isMax;
    }

    public Coup getC() {
        return c;
    }

    @Override
    public int compareTo(ArbreConfigurations arbreConfigurations) {
        return score - arbreConfigurations.getScore();
    }

}
