package modele;

import java.util.ArrayList;

import static modele.Plateau.NOIR;
import static modele.Plateau.BLANC;
public abstract class Joueur {
    private final String nom;
    private String couleur;
    private int nbVictoires;

    static boolean EST_IA ;

    public Joueur(String nom, String couleur,boolean EST_IA)throws CouleurException{
        if ((couleur != NOIR)&& (couleur!= BLANC)){
            throw new CouleurException(couleur +" n'est pas une couleur de jeu");
        }
        this.nom = nom;
        this.couleur = couleur;
        this.nbVictoires=0;
        this.EST_IA =EST_IA;
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

    abstract Coup choisirCoup(ArrayList<Coup> coupsPossibles);
}
