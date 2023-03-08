package main;

import controleur.controleur;
import vue.Ihm;

public class Main {
    public static void main(String[] args) {
        Ihm ihm = new Ihm();
        controleur c = new controleur(ihm);
        c.play();

    }
}