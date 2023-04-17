package modele.joueurs;

import modele.plateau.CouleurException;
import static modele.plateau.PlateauOthello.BLANC;
import static modele.plateau.PlateauOthello.NOIR;

public class JoueurHumainOthello extends JoueurOthello implements JoueurHumain {

    public JoueurHumainOthello(String nom, String couleur) throws CouleurException {
        super(nom, couleur);
        if ((!NOIR.equals(couleur))&& (!BLANC.equals(couleur))){
            throw new CouleurException(couleur +" n'est pas une couleur de jeu");
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public boolean getIA(){
        return false;
    }
}
