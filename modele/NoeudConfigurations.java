package modele;

import java.util.ArrayList;
import java.util.TreeSet;

public class NoeudConfigurations extends ArbreConfigurations{
    ;
    private TreeSet<ArbreConfigurations> fils;
    private ArbreConfigurations meilleurFils;

    public NoeudConfigurations(Plateau pere, Coup c,String couleur, boolean isMax, int prof){
        super(pere,c,couleur,isMax, prof);




        this.meilleurFils = trouverMeilleurFils();
        this.setScore(this.meilleurFils.getScore());

    }


    @Override
    public void minMax() {
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
    }

    private ArbreConfigurations trouverMeilleurFils() throws RuntimeException{
        for (ArbreConfigurations f : this.fils){
            if (f.getScore()== this.getScore()){
                return f;
            }
        }
        throw new RuntimeException("Le score de ce Noeud ne correspond à aucun score de ses fils... Un bug dans minMax?");
    }


}
