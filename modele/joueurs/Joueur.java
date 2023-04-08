package modele.joueurs;
import modele.plateau.CouleurException;

import static modele.plateau.Plateau.BLANC;
import static modele.plateau.Plateau.NOIR;


public abstract class Joueur {

   private String nom;
   private int nbVictoires;

   public Joueur(String nom){
       this.nom = nom;
       this.nbVictoires = 0;
   }

    @Override
    public String toString() {
     return "Joueur{" +
             "nom='" + nom + '\'' +
             ", nbVictoires=" + nbVictoires +
             '}';
    }


  public int getNbVictoires() {
   return nbVictoires;
  }

  public String getNom() {
   return nom;
  }

  public void incrementeNbVictoires() {
   this.nbVictoires++;
  }


}
