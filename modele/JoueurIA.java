package modele;

import java.util.ArrayList;

public class JoueurIA extends Joueur{

    private String niveau;

    public String getNiveau() {
        return niveau;
    }

    public JoueurIA(String nom, String couleur, boolean ia, String niveau) throws CouleurException {
        super(nom, couleur, ia);
        this.niveau=niveau;
    }


    public Coup choisirCoup(ArrayList<Coup> coupsPossibles) {
        if(this.niveau.equals("Naif")){
            if (coupsPossibles.size()==0){
                return Coup.coupPasser();
            }
            return coupsPossibles.get((int)( Math.random() * coupsPossibles.size()));
        }
        return null;
    }
}
