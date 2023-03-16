package modele;

import java.util.ArrayList;

public class JoueurHumain extends Joueur{


    public JoueurHumain(String nom, String couleur, boolean ia) throws CouleurException {
        super(nom, couleur, ia);
    }

    @Override
    public Coup choisirCoup(ArrayList<Coup> coupsPossibles, int[] emplacment) {
        Coup c = new Coup();
        for ( Coup i : coupsPossibles){
            if (i.getX()==emplacment[0]&&i.getY()==emplacment[1]){
                c=i;
            }
        }
        return c;
    }


}
