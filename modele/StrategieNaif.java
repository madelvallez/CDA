package modele;

import java.util.ArrayList;
import java.util.List;

public class StrategieNaif implements StrategieJeu {
    public StrategieNaif(){
    }


    @Override
    public Coup donnerCoup(List<Coup> coupsPossibles, Plateau p) {
        Coup choix = null;
        if (coupsPossibles.size() == 0) {
            choix = Coup.coupPasser();
        } else {
            choix = coupsPossibles.get((int) (Math.random() * coupsPossibles.size()));

        }
        return choix;
    }
}
