package modele;

import java.util.ArrayList;

public class JoueurIANaif extends Joueur{


    public JoueurIANaif(String nom, String couleur, boolean ia) throws CouleurException {
        super(nom, couleur, ia);
    }

    @Override
    public Coup choisirCoup(ArrayList<Coup> coupsPossibles,int[] emplacement) {
        if (coupsPossibles.size()==0){
            return Coup.coupPasser();
        }
        return coupsPossibles.get((int)( Math.random() * coupsPossibles.size()));
    }
}
