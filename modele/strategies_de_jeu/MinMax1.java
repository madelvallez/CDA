package modele.strategies_de_jeu;

import modele.plateau.*;
import modele.arbres_de_configurations.*;
import java.util.List;

public class MinMax1 extends StrategieMinMax{
    public MinMax1(String coul) {
        super(coul);
    }


    @Override
    public Coup donnerCoup(List<Coup> coupsPossibles, Plateau plateau) {
        {
            Plateau p = plateau.copie();
            ArbreConfigurations ia = new NoeudConfigurations(p, Coup.coupDejaAppliqué(getCouleur()), getCouleur(), true, 3, coupsPossibles);
            return ((NoeudConfigurations) ia).getMeilleurCoup();

        }
    }
}
