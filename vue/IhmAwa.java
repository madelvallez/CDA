package vue;

import modele.joueurs.JoueurAwale;
import modele.plateau.PlateauAwale;

import java.util.Scanner;

public class IhmAwa extends Ihm{
    private static Scanner sc;
    public IhmAwa(Ihm ihm) {    }

    public static int demanderCoup(){
        message("Quel est votre coup ?(entre 1 et 6)");
        sc=new Scanner(System.in);
        int b;
        if(sc.hasNextInt()){
            b=sc.nextInt();
            if(b<1 ||b>6){
                message("Veuillez renseigner un nombre entre 1 et 6.");
                b=demanderCoup();
            }
        }else{
            message("Veuillez renseigner un nombre entre 1 et 6.");
            b=demanderCoup();
        }

        return b;
    }
    public static void affichageplateauAwa(){
        System.out.println();
    }

    public static void affichagePlateau(PlateauAwale p){
        System.out.println(p);
    }

    public static void tourDeJouer(JoueurAwale j1,JoueurAwale j2 ,int i){
        if (i==1) {
            message("C'est à " + j1.getNom() + " de jouer");
        }else{
            message("C'est à " + j2.getNom() + " de jouer");
        }
    }


}
