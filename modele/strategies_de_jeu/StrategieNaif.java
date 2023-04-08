package modele.strategies_de_jeu;

import modele.joueurs.Joueur;
import modele.plateau.*;

import java.util.List;

public class StrategieNaif implements StrategieJeu {
    public StrategieNaif(){
    }


    @Override
    public Coup donnerCoup(List<Coup> coupsPossibles, PlateauOthello p, Joueur[] joueurs) {
        Coup choix = null;
        if (coupsPossibles.size() == 0) {
            choix = Coup.coupPasser();
        } else {
            choix = coupsPossibles.get((int) (Math.random() * coupsPossibles.size()));

        }
        return choix;
    }
}
