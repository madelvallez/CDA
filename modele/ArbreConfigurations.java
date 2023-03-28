package modele;

import java.util.ArrayList;

public abstract class ArbreConfigurations implements Comparable<ArbreConfigurations> {
    private int score;
    private Plateau p;
    private Coup c;
    private String couleur;
    private boolean isMax;

    public ArbreConfigurations(Plateau p, Coup c, String couleur, boolean isMax){
        this.c = c;
        this.p = p;
        this.isMax= isMax;
        this.couleur = couleur;

    }
//    private void calculerScore(int prof) {
//        ArrayList<Coup> coupsPossibles = this.p.listeCoupsPossibles(this.couleur);
//        if (coupsPossibles.size() == 0) { //si pas de coup possible
//            if (this.p.estFiniePartie()) { //si c'est une configuration finale
//                //cherher qui a gagné
//                int nbPionJoueur = this.p.score(this.couleur);
//                int nbPionAdv = this.p.score(Plateau.opposeCouleur(this.couleur));
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
    abstract void minMax(ArrayList<Coup> coupsPossibles, int prof);
    public int fonctionEvaluation(){
        return 0;
    }

    public int getScore(){
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getCouleur() {
        return couleur;
    }

    public Plateau getP() {
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
