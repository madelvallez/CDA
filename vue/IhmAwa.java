package vue;

import modele.plateau.PlateauAwale;

import java.util.Scanner;

public class IhmAwa extends Ihm{
    Scanner sc;
    public IhmAwa(Ihm ihm) {    }

    public int demanderCoup(){
        message("Quel est votre coup ?");
        sc=new Scanner(System.in);
        int b=sc.nextInt();
        return b;
    }
    public void affichageplateauAwa(){
        System.out.println();
    }

    public void affichagePlateau(PlateauAwale p){
        System.out.println(p);
    }

}
