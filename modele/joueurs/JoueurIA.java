package modele.joueurs;

import java.util.List;
import modele.strategies_de_jeu.*;
import modele.plateau.*;
import static modele.plateau.Plateau.BLANC;
import static modele.plateau.Plateau.NOIR;

public class JoueurIA implements Joueur {
    private String nom;
    private String couleur;
    private int nbVictoires;

    private StrategieJeu strategie;

    public JoueurIA(String nom, String couleur, String niveau) throws CouleurException {
        if ((!NOIR.equals(couleur)) && (!BLANC.equals(couleur))) {
            throw new CouleurException(couleur + " n'est pas une couleur de jeu");
        }
        this.nom = nom;
        this.couleur = couleur;
        this.nbVictoires = 0;
        if ("Naif".equals(niveau)){
            this.strategie = new StrategieNaif();
        }else { // if ("MinMax1".equals(niveau))
            this.strategie = new MinMax1(this.couleur);
        }
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

    public boolean getIa() {
        return true;
    }

    public void setCouleur(String couleur) throws CouleurException {
        if ((!NOIR.equals(couleur)) && (!BLANC.equals(couleur))) {
            throw new CouleurException(couleur + " n'est pas une couleur de pion");
        }
        this.couleur = couleur;
    }

    public void incrementeNbVictoires() {
        this.nbVictoires++;
    }


    public Coup choisirCoup(List<Coup> coupsPossibles, Plateau plateau) {
        return strategie.donnerCoup(coupsPossibles, plateau);
    }

//    private Coup jeuIAMinMax(Plateau plateau, ArrayList<Coup> coupsPossibles) {
//        Plateau p = plateau.copie();
//        ArbreConfigurations ia = new NoeudConfigurations(p, Coup.coupDejaAppliqué(couleur), couleur, true, 3, coupsPossibles);
//        return ((NoeudConfigurations)ia).getMeilleurCoup();
//    }
}