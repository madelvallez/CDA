package main;

import controleur.Controleur;
import modele.joueurs.JoueurAwale;
import modele.joueurs.JoueurHumainAwale;
import modele.plateau.PlateauAwale;
import modele.plateau.Coup;
import vue.Ihm;

import java.util.List;

public class Main {
    public static void main(String[] args) {

//        Ihm ihm = new Ihm();
//        Controleur c = new Controleur(ihm);
//        c.jouer();


       // serie tests awale Marine (à supprimer une fois controleur fonctionnel
        PlateauAwale p = new PlateauAwale();
        JoueurAwale j = new JoueurHumainAwale("test", 0);
        System.out.println(p);
        List<Coup> lesCoup0 = p.listeCoupsPossibles(j);
        System.out.println(lesCoup0);
        p.appliqueCoup(lesCoup0.get(0));
        System.out.println(p);
        System.out.println(p.score(j));
        System.out.println(p.estFiniePartie());
    }
}