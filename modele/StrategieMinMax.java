package modele;

import java.util.ArrayList;
import java.util.List;

public abstract class StrategieMinMax implements StrategieJeu{

    private String couleur ;

    public StrategieMinMax(String coul){
        this.couleur = coul;

    }

    @Override
    public Coup donnerCoup(List<Coup> coupsPossibles, Plateau plateau) {
        Plateau p = plateau.copie();
        ArbreConfigurations ia = new NoeudConfigurations(p, Coup.coupDejaAppliqué(couleur), couleur, true, 3, coupsPossibles);
        return ((NoeudConfigurations) ia).getMeilleurCoup();

    }

    abstract int fonctionEvaluation(Plateau p, String couleur);

    public String getCouleur() {
        return couleur;
    }

}
