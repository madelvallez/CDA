package modele;

import java.util.ArrayList;
import java.util.TreeSet;

public class NoeudConfigurations extends ArbreConfigurations{
    ;
    private TreeSet<ArbreConfigurations> fils;
    private ArbreConfigurations meilleurFils;

    public NoeudConfigurations(Plateau p, Coup c,String couleur, boolean isMax, int prof,ArrayList<Coup> coupsPossibles){
        super(p,c,couleur,isMax);
        this.minMax(coupsPossibles, prof);
    }


    @Override
    public void minMax(ArrayList<Coup> coupsPossibles, int prof) {
        //creation des fils
        assert coupsPossibles.size() != 0;
        String couleurFils = Plateau.opposeCouleur(this.getCouleur());
        if (prof==1){//les fils sont tous des feuilles quoi qui se passe
            for (Coup coup : coupsPossibles) {//recuperartion des infos et instanciation des fils comme feuille
                Plateau plateau = this.getP().copie();
                plateau.appliqueCoup(coup);
                this.fils.add(new FeuilleConfigurations(plateau, coup, couleurFils, !this.isMax(), 0));
            }
        }else {
            for (Coup coup : coupsPossibles) {//recuperartion des infos et instanciation des fils
                Plateau plateau = this.getP().copie();
                plateau.appliqueCoup(coup);
                ArrayList<Coup> coupsPossFils = plateau.listeCoupsPossibles(couleurFils);
                if (coupsPossFils.size() == 0 && plateau.estFiniePartie()) {//partie finie donc feuille
                    this.fils.add(new FeuilleConfigurations(plateau, coup, couleurFils, !this.isMax(), prof - 1));
                } else if (coupsPossFils.size() == 0) {//partie pas finie donc CoupPasser donc noeud
                    coupsPossFils.add(Coup.coupPasser());
                    this.fils.add(new NoeudConfigurations(plateau, coup, couleurFils, !this.isMax(), prof - 1, coupsPossFils));
                } else {
                    this.fils.add(new NoeudConfigurations(plateau, coup, couleurFils, !this.isMax(), prof - 1, coupsPossFils));
                }
            }
        }
        //recherche du meilleur coup
        if (this.isMax()) {
            this.meilleurFils = this.fils.first();
        } else {
            this.meilleurFils = this.fils.last();
        }
        //recuperation du score
        this.setScore(this.meilleurFils.getScore());

    }
    public Coup getMeilleurCoup(){
        return this.meilleurFils.getC();
    }

}
