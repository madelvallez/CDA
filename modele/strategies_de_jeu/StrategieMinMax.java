package modele.strategies_de_jeu;

import java.util.List;

import modele.joueurs.Joueur;
import modele.plateau.*;

public abstract class StrategieMinMax implements StrategieJeu{

    private String couleur ;

    public StrategieMinMax(String coul){
        this.couleur = coul;

    }

    @Override
    public abstract Coup donnerCoup(List<Coup> coupsPossibles, PlateauOthello plateau, Joueur[] joueurs) ;

    //abstract int fonctionEvaluation(PlateauOthello p, String couleur);

    public String getCouleur() {
        return couleur;
    }

}
