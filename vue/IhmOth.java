package vue;

import modele.plateau.PlateauOthello;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IhmOth extends Ihm {
    public IhmOth(Ihm ihm) {
    }

    public static void afficherPlateau(PlateauOthello plateau) {
    System.out.println(plateau);
}

    public static int[] demanderCoup(String nom ,String couleur ,boolean erreur, int DIM){
        System.out.println(nom +"("+couleur+"), Veuillez renseigner coup de la forme de un Nombre puis un espace puis une Lettre  ou P pour passer"+"\n"
                +"Si vous souhaitez arrêter, veuillez écrire arret");
        if (erreur) {
            System.out.println("Rappel : Un coup est dit valide s'il retourne au moins un pion.");
        }
        int [] emplacement = new int [2];
        sc = new Scanner(System.in);
        int a ;
        try {
            a = sc.nextInt();
        }catch(InputMismatchException e){
            a = 0;
        }
        String b = sc.next();
        a--;

        int c =-1 ;
//        if (b.equals("A")){
//            c=0;
//        } else if (b.equals("B")) {
//            c=1;
//        } else if (b.equals("C")) {
//            c=2;
//        } else if (b.equals("D")) {
//            c=3;
//        } else if (b.equals("E")) {
//            c=4;
//        } else if (b.equals("F")) {
//            c=5;
//        } else if (b.equals("G")) {
//            c=6;
//        } else if (b.equals("H")) {
//            c=7;
//        } else if (b.equals("P")) {
//            return new int[] {-2,-2};
//
//        }
        for (int i=65; i<65+DIM;  i++){
            if (b.equals(String.valueOf((char)i))){
                c= i-65;
            }
        }

        if (b.equals("arret")){
            c= -3;
            a=-3;
        }

        if (b.equals("P")) {
            return new int[] {-2,-2};
        }

        emplacement[0]=a;
        emplacement[1]=c;
        return emplacement;


    }

    public static void afficherCoupJoue(String nomJ, int x, int y){
        if (x!=-2){
            System.out.println(nomJ + " a joué en "+(x+1) + " "+(char)(y+65));
        }
    }
}
