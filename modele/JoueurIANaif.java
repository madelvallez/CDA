package modele;

import java.util.ArrayList;

public class JoueurIANaif extends Joueur{

    public JoueurIANaif(String nom, String couleur, boolean EST_IA) throws CouleurException {
        super(nom, couleur, EST_IA);
    }

    @Override
    Coup choisirCoup(ArrayList<Coup> coupsPossibles) {
        int n=0;
        for (Coup i : coupsPossibles){
            n++ ;
        }
        Coup c = coupsPossibles.get((int) Math.random() * n);
        return c;
    }
}
