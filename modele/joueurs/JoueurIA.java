package modele.joueurs;

import modele.plateau.*;

import java.util.List;

public interface JoueurIA{
    Coup choisirCoup(List<Coup> coupsPossibles, Plateau p);
}
