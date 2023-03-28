package modele;

import java.util.ArrayList;

public class FeuilleConfigurations extends ArbreConfigurations{

    public FeuilleConfigurations(Plateau pere, Coup c, String couleur, boolean isMax, int prof){
        super(pere, c, couleur, isMax, prof);
    }


    @Override
    void minMax() {
        if (this.getP().estFiniePartie()) { //si c'est une configuration finale
            //cherher qui a gagné
            int nbPionJoueur = this.getP().score(this.getCouleur());
            int nbPionAdv = this.getP().score(Plateau.opposeCouleur(this.getCouleur()));
            if (nbPionAdv > nbPionJoueur) {
                this.setScore(-1000);
            } else {
                this.setScore(1000);
            }
        }else{
            this.setScore(this.fonctionEvaluation());
        }

    }
}
