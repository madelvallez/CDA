package modele;

import java.util.ArrayList;

public class JoueurHumain extends Joueur{

    public JoueurHumain(String nom, String couleur, boolean EST_IA) throws CouleurException {
        super(nom, couleur, EST_IA);
    }

    @Override
    Coup choisirCoup(ArrayList<Coup> coupsPossibles) {
        return null;
    }
}
