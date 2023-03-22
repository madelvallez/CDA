package modele;

import java.util.SortedSet;
import java.util.ArrayList;

import static modele.Plateau.NOIR;
import static  modele.Plateau.BLANC;
public class Noeud implements Comparable<Noeud>{
    private Coup coup;
    private Plateau plateau;
    private boolean isMax;
    int score;
    boolean scoreConnu= false;
    private String couleur;

    private SortedSet<Noeud> filsPossibles;

    public Noeud(Coup c, Plateau plateau_parent, boolean isMax, String coul){
        this.coup = c;
        this.plateau = plateau_parent.copie();
        this.plateau.appliqueCoup(c);
        this.isMax = isMax;
        this.couleur = coul;
    }



    private void calculerScore(int profCalc){
        if (!scoreConnu){
            score = this.minMax(profCalc).score;
        }
    }

    private Noeud minMax(int profCalc){
        ArrayList<Coup> coupsPossibles = this.plateau.listeCoupsPossibles(this.couleur);
        if (coupsPossibles.size()==0){ //si pas de coup possible
            if (this.plateau.estFiniePartie()){ //si c'est une configuration finale
                //cherher qui a gagné
                int nbPionJoueur = this.plateau.score(this.couleur);
                int nbPionAdv = this.plateau.score(Plateau.opposeCouleur(this.couleur));
                if (nbPionAdv>nbPionJoueur){
                    this.score = -1000;
                }else{
                    this.score = 1000;
                }
                return this;
            }else{ // sinon, coupPasser est le seul coup possible
                Coup cFils = Coup.coupPasser();
                filsPossibles.add(new Noeud(cFils, this.plateau, !isMax, Plateau.opposeCouleur(couleur)));
                calculerScore(profCalc-1);
                assert filsPossibles.size()==1;
                return filsPossibles.first();
            }
        } else { //il y a des coups possibles : evaluons-les puis choisisons le bon
            if (profCalc == 0) {//pas besoin de calculer les fils
                this.score = this.fonctionEvaluation();
                return this;
            } else { //caculons les fils pour choisir le bon
                for (Coup c : coupsPossibles) {//création des fils du noeud courant
                this.filsPossibles.add(new Noeud(c, this.plateau, !isMax, Plateau.opposeCouleur(this.couleur)));
                }
                for (Noeud f : filsPossibles) { //calcul des scores des fils (appe rec caché)
                    calculerScore(profCalc - 1);
                }
                //recherhe du meilleur coup
                Noeud meilleur ;
                if (isMax) {
                    meilleur = filsPossibles.first();
                }else{
                    meilleur = filsPossibles.last();
                }
                return meilleur;
            }
        }
    }


    @Override
    public int compareTo(Noeud noeud) {
        return this.score - noeud.score ;
    }
}
