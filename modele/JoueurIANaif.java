package modele;

import java.util.ArrayList;

public class JoueurIANaif extends Joueur{


    public JoueurIANaif(String nom, String couleur, boolean ia) throws CouleurException {
        super(nom, couleur, ia);
    }

    @Override
    public Coup choisirCoup(ArrayList<Coup> coupsPossibles,int[] emplacement) {
        return coupsPossibles.get((int)( Math.random() * coupsPossibles.size()));
    }
}
