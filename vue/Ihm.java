package vue;


import java.util.Scanner;
import modele.plateau.PlateauOthello;


public class Ihm {
    static Scanner sc ;

    public Ihm(){    }


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
        System.out.println(nom1 + " a obtenu "+ j1 + " pions");
        System.out.println((nom2 + " a obtenu " + j2 + " pions"));
    }
    public  static void affichageVictoire(String nom1, String nom2, int j1, int j2){
        System.out.println(nom1 +" a " + j1 + " victoires");
        System.out.println(nom2 +" a " + j2 + " victoires");

    }

    public static void message(String msg) {
        System.out.println(msg);
    }

    public static String demanderJeu(){
        message("Quel jeu souhaiter vous jouer (Othello ou Awale)");
        sc = new Scanner(System.in);
        String b=sc.next();
        if( !(b.equals("Othello")||b.equals("Awale"))){
            message("Veuillez renseigner un jeu");
            demanderJeu();
        }
        return b;
    }

//    public static void afficherCoupJoue(String nomJ, int x, int y){
//        if (x!=-2){
//            System.out.println(nomJ + " a joué en "+(Integer)(x+1) + " "+(String.valueOf((char)(y+65))));
//        }
//    }
}

