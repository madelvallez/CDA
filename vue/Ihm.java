package vue;

import java.util.InputMismatchException;
import java.util.Scanner;
import modele.plateau.Plateau;


public class Ihm {
    static Scanner sc ;

    public Ihm(){    }

    public static void afficherPlateau(Plateau plateau) {
        System.out.println(plateau);
    }

    public static String demanderNom(int i){
        System.out.println("Quel est le nom du joueur "+i+" ?");
        Scanner sc= new Scanner(System.in);
        return sc.next();
    }
    public static String demanderAdversaire(){
        System.out.println("Souhaitez vous jouer contre un Ordinateur (oui/non)");
        Scanner sc= new Scanner(System.in);
        return sc.next();
    }
    public static String demanderNiveauIA(String[] list){
        String mess= "Lequel parmi : \n";
        for (int i= 0 ; i<list.length ; i++){
            mess += "- "+ list[i] +"\n";
        }
        System.out.println(mess);
        Scanner sc = new Scanner(System.in);
        return sc.next();
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

    public static String messageRejouer(){
        System.out.println("Souhaitez vous rejouer (Oui /Non) ?");
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }

    public static void erreurFin(){
        System.out.println("Veuiller ne mettre que Oui ou Non comme réponse");
    }

    public static void messageErreur(String erreur){
        System.out.println(erreur);
    }
    public static void affichageScore(String nom1, String nom2, int j1, int j2){
        System.out.println(nom1 + " a obtenu "+ (Integer)j1 + " pions");
        System.out.println((nom2 + " a obtenu " + (Integer)j2 + " pions"));
    }
    public  static void affichageVictoire(String nom1, String nom2, int j1, int j2){
        System.out.println(nom1 +" a " + (Integer)j1 + " victoires");
        System.out.println(nom2 +" a " + (Integer)j2 + " victoires");

    }

    public static void message(String msg) {
        System.out.println(msg);
    }

    public static void afficherCoupJoue(String nomJ, int x, int y){
        System.out.println(nomJ + " a joué en "+(Integer)(x+1) + " "+(String.valueOf((char)(y+65))));
    }
}

