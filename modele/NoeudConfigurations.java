package modele;

import java.util.ArrayList;
import java.util.TreeSet;

public class NoeudConfigurations implements ArbreConfigurations{
    private int score;
    private Plateau p;
    private Coup c;
    private String couleur;
    private boolean isMax;
    private TreeSet<ArbreConfigurations> fils;
    private ArbreConfigurations meilleurFils;

    public NoeudConfigurations(Plateau pere, Coup c,String couleur, boolean isMax){
        this.c = c;
        this.p = pere.copie();
        this.p.appliqueCoup(this.c);
        this.isMax= isMax;
        this.couleur = couleur;



        this.meilleurFils = this.minMax();
        this.score = this.meilleurFils.getScore();

    }


    @Override
    public ArbreConfigurations minMax() {
//        ArrayList<Coup> coupsPossibles = this.p.listeCoupsPossibles(this.couleur);
//        if (coupsPossibles.size()==0){ //si pas de coup possible
//            if (this.p.estFiniePartie()){ //si c'est une configuration finale
//                //cherher qui a gagné
//                int nbPionJoueur = this.p.score(this.couleur);
//                int nbPionAdv = this.p.score(Plateau.opposeCouleur(this.couleur));
//                if (nbPionAdv>nbPionJoueur){
//                    this.score = -1000;
//                }else{
//                    this.score = 1000;
//                }
//                return this;
//            }else{ // sinon, coupPasser est le seul coup possible
//                Coup cFils = Coup.coupPasser();
//                filsPossibles.add(new Noeud(cFils, this.plateau, !isMax, Plateau.opposeCouleur(couleur)));
//                calculerScore(profCalc-1);
//                assert filsPossibles.size()==1;
//                return filsPossibles.first();
//            }
//        } else { //il y a des coups possibles : evaluons-les puis choisisons le bon
//            if (profCalc == 0) {//pas besoin de calculer les fils
//                this.score = this.fonctionEvaluation();
//                return this;
//            } else { //caculons les fils pour choisir le bon
//                for (Coup c : coupsPossibles) {//création des fils du noeud courant
//                    this.filsPossibles.add(new Noeud(c, this.plateau, !isMax, Plateau.opposeCouleur(this.couleur)));
//                }
//                for (Noeud f : filsPossibles) { //calcul des scores des fils (appe rec caché)
//                    calculerScore(profCalc - 1);
//                }
//                //recherhe du meilleur coup
//                Noeud meilleur ;
//                if (isMax) {
//                    meilleur = filsPossibles.first();
//                }else{
//                    meilleur = filsPossibles.last();
//                }
//                return meilleur;
//            }
//        }
        return null;
    }



    @Override
    public int fonctionEvaluation() {
        return 0;
    }

    @Override
    public int compareTo(ArbreConfigurations arbreConfigurations) {
        return score - arbreConfigurations.getScore();
    }

    @Override
    public int getScore(){
        return score;
    }
}
