package modele;

import java.util.List;

public class FeuilleConfigurations extends ArbreConfigurations{

    public FeuilleConfigurations(Plateau p, Coup c, String couleur, boolean isMax, int prof){
        super(p, c, couleur, isMax);
        List<Coup> fantome = null;
        this.minMax(fantome, prof);
    }


    @Override
    void minMax(List<Coup> coupsPossibles, int prof) {
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
            this.setScore((StrategieMinMax).fonctionEvaluation(this.getP(), this.getCouleur()));
        }

    }
}
