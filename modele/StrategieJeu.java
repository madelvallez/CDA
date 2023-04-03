package modele;

import java.util.List;

public interface StrategieJeu {

    Coup donnerCoup(List<Coup> coupsPossibles, Plateau p);
}
