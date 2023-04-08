package modele.plateau;

import modele.joueurs.Joueur;

import java.util.List;

public interface Plateau {
    void initialiser();
    List<Coup> listeCoupsPossibles(Joueur j);
    void appliqueCoup(Coup c);
    int score(Joueur j);
    boolean estFiniePartie();

}
