package modele.strategies_de_jeu;

import java.util.List;

import modele.joueurs.Joueur;
import modele.plateau.*;

public interface StrategieJeu {

    Coup donnerCoup(List<Coup> coupsPossibles, PlateauOthello p, Joueur[] joueurs);
}
