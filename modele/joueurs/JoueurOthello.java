package modele.joueurs;

import modele.plateau.CouleurException;

import java.util.Objects;

import static modele.plateau.Plateau.BLANC;
import static modele.plateau.Plateau.NOIR;

public abstract class JoueurOthello extends Joueur{
    private String couleur;

    public JoueurOthello(String nom, String couleur){
        super(nom);
        this.couleur = couleur;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) throws CouleurException {
        if ((!Objects.equals(couleur, NOIR))&& (!Objects.equals(couleur, BLANC))){
            throw new CouleurException(couleur +" n'est pas une couleur de pion");
        }
        this.couleur = couleur;
    }
}
