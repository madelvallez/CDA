package modele.arbres_de_configurations;

import modele.joueurs.Joueur;
import modele.plateau.*;
import java.util.List;

public class FeuilleConfigurations extends ArbreConfigurations{

    public FeuilleConfigurations(PlateauOthello p, Coup c, Joueur[] joueurs, int numJ, boolean isMax, int prof){
        super(p, c, joueurs, numJ, isMax);
        List<Coup> fantome = null;
        this.minMax(fantome, prof);
    }


    @Override
    void minMax(List<Coup> coupsPossibles, int prof) {
        if (this.getP().estFiniePartie()) { //si c'est une configuration finale
            //cherher qui a gagné
            int nbPionJoueur = this.getP().score(this.getJoueurCourant());
            int nbPionAdv = this.getP().score(getJoueurCourant());
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
