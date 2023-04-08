package modele.arbres_de_configurations;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import modele.joueurs.Joueur;
import modele.joueurs.JoueurOthello;
import modele.plateau.*;

public class NoeudConfigurations extends ArbreConfigurations{
    private List<ArbreConfigurations> fils=new ArrayList<ArbreConfigurations>();
    private ArbreConfigurations meilleurFils;

    public NoeudConfigurations(PlateauOthello p, Coup c, Joueur[] joueurs, int numJ, boolean isMax, int prof, List<Coup> coupsPossibles){
        super(p,c,joueurs, numJ,isMax);
        this.minMax(coupsPossibles, prof);
    }

    @Override
    public void minMax(List<Coup> coupsPossibles, int prof) {
        //creation des fils
        if (coupsPossibles.size() == 0){ //il n'y a aucun coup possible, il faut passer
            coupsPossibles.add(Coup.coupPasser());
        }
        int numJFils = (getNumJ()+1)/2;
        Joueur joueurFils = getJoueurs()[numJFils];
        if (prof==1){//les fils sont tous des feuilles quoi qui se passe
            for (Coup coup : coupsPossibles) {//recuperation des infos et instanciation des fils comme feuille
                PlateauOthello plateau = this.getP().copie();
                plateau.appliqueCoup(coup);
                this.fils.add(new FeuilleConfigurations(plateau, coup, getJoueurs(), numJFils, !this.isMax(), 0));
            }
        }else {
            for (Coup coup : coupsPossibles) {//recuperartion des infos et instanciation des fils
                PlateauOthello plateau = this.getP().copie();
                plateau.appliqueCoup(coup);
                ArrayList<Coup> coupsPossFils = plateau.listeCoupsPossibles(getJoueurCourant());
                if (coupsPossFils.size() == 0 && plateau.estFiniePartie()) {//partie finie donc feuille
                    this.fils.add(new FeuilleConfigurations(plateau, coup, getJoueurs(), numJFils, !this.isMax(), prof - 1));
                } else if (coupsPossFils.size() == 0) {//partie pas finie donc CoupPasser donc Noeud
                    coupsPossFils.add(Coup.coupPasser());
                    this.fils.add(new NoeudConfigurations(plateau, coup, getJoueurs(), (getNumJ()+1)/2, !this.isMax(), prof - 1, coupsPossFils));
                } else {
                    this.fils.add(new NoeudConfigurations(plateau, coup, getJoueurs(), (getNumJ()+1)/2, !this.isMax(), prof - 1, coupsPossFils));
                }
            }
        }
        //recherche du meilleur coup
        if (this.isMax()) {
            this.meilleurFils = Collections.max(this.fils); //le plus grand
        } else {
            this.meilleurFils = Collections.min(this.fils); //le plus petit
        }
        //recuperation du score
        this.setScore(this.meilleurFils.getScore());

    }
    public Coup getMeilleurCoup(){
        return this.meilleurFils.getC();
    }

}
