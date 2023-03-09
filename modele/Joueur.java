package modele;

import static modele.Plateau.NOIR;
import static modele.Plateau.BLANC;
public class Joueur {
    private String nom;
    private String couleur;
    private int nbVictoires;

    public Joueur(String nom, String couleur)throws CouleurException{
        if ((couleur != NOIR)&& (couleur!= BLANC)){
            throw new CouleurException(couleur +" n'est pas une couleur de jeu");
        }
        this.nom = nom;
        this.couleur = couleur;
        this.nbVictoires=0;
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "nom='" + nom + '\'' +
                ", couleur='" + couleur + '\'' +
                ", nbVictoires=" + nbVictoires +
                '}';
    }

    public String getCouleur() {
        return couleur;
    }

    public int getNbVictoires() {
        return nbVictoires;
    }

    public String getNom() {
        return nom;
    }

    public void setCouleur(String couleur) throws CouleurException {
        if ((couleur != NOIR)&& (couleur!= BLANC)){
            throw new CouleurException(couleur +" n'est pas une couleur de pion");
        }
        this.couleur = couleur;
    }

    public void incrementeNbVictoires() {
        this.nbVictoires++;
    }
}
