package modele;

import java.util.SortedSet;
import java.util.List;
import java.util.SortedMap;

import static modele.Plateau.NOIR;
import static  modele.Plateau.BLANC;
public class Noeud {
    private Coup coup;
    private Plateau plateau;
    private boolean isMax;
    int score;
    boolean scoreConnu= false;
    private String couleur;

    private SortedSet<Noeud> filsPossibles;

    public Noeud(Coup c, Plateau p, boolean isMax, String coul){
        this.coup = c;
        this.plateau = p.copie();
        this.isMax = isMax;
        this.couleur = coul;
    }

    private void calculerScore(int profCalc, List<Coup> coupsPossibles){
        if (!scoreConnu){
            score = this.minMax(profCalc, isMax, coupsPossibles).score;
        }
    }

    private Noeud minMax(int profCalc, boolean isMax, List<Coup> coupsPossibles){
        if (coupsPossibles.size()==0){
           //cherher qui a gangé
            int nbPionJoueur = this.plateau.score(this.couleur);
            int nbPionAdv = this.plateau.score(Plateau.opposeCouleur(this.couleur));
            if (nbPionAdv>nbPionJoueur){
                this.score = -1000;
            }else{
                this.score = 1000;
            }
        } else {
            for (Coup c : coupsPossibles) {
                this.filsPossibles.add(new Noeud(c, plateau, !isMax, Plateau.opposeCouleur(this.couleur)));
                //appliquer le coup sur le plateau
            }
            if (profCalc == 0) {//pas besin de calculer les scores
                this.score = this.fonctionEvaluation();
                return this;
            } else {
                for (Noeud f : filsPossibles) {
                    List<Coup> coupsFils = f.plateau.listeCoupsPossibles(f.couleur);
                    calculerScore(profCalc - 1, coupsFils);
                }
                if (isMax) {
                    Noeud max = ;
                    for (Noeud n : filsPossibles) {
                        if (max.score < n.score) {

                        }
                    }
                }
            }
        }
    }


}
