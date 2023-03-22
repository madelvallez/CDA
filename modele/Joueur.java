package modele;


public interface Joueur {



    @Override
     String toString() ;

     String getCouleur();

     int getNbVictoires();

     String getNom();

     boolean getIa() ;

     void setCouleur(String couleur) throws CouleurException;


     void incrementeNbVictoires();


}
