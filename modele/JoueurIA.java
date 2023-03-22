package modele;

import java.util.ArrayList;

import static modele.Plateau.BLANC;
import static modele.Plateau.NOIR;

public class JoueurIA implements Joueur{
    private String nom;
    private String couleur;
    private int nbVictoires;

    private String niveau;

    public String getNiveau() {
        return niveau;
    }

    public JoueurIA(String nom, String couleur, String niveau) throws CouleurException {
        if ((!NOIR.equals(couleur))&& (!BLANC.equals(couleur))){
            throw new CouleurException(couleur +" n'est pas une couleur de jeu");
        }
        this.nom = nom;
        this.couleur = couleur;
        this.nbVictoires=0;
        this.niveau=niveau;
    }

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

    public boolean getIa() { return true;    }

    public void setCouleur(String couleur) throws CouleurException {
        if ((!NOIR.equals(couleur))&& (!BLANC.equals(couleur))){
            throw new CouleurException(couleur +" n'est pas une couleur de pion");
        }
        this.couleur = couleur;
    }

    public void incrementeNbVictoires() {
        this.nbVictoires++;
    }


    public Coup choisirCoup(ArrayList<Coup> coupsPossibles) {
        if(this.niveau.equals("Naif")){
            if (coupsPossibles.size()==0){
                return Coup.coupPasser();
            }
            return coupsPossibles.get((int)( Math.random() * coupsPossibles.size()));
//        }else if (this.niveau.equals("MinMax")){
//
//            miniMax.miniMax(2,0, true, score, score.size());
        }
        return null;
    }
}
