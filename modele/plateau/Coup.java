package modele.plateau;

import static modele.plateau.Plateau.BLANC;
import static modele.plateau.Plateau.NOIR;

public class Coup{
    private boolean aEffet ;
    private int x;
    private int y;
    private String couleur;
    private Coord g; //coord du pion en face si le coup a un effet sur la gauche
    private Coord d;//coord du pion en face si le coup a un effet sur la droite
    private Coord h;//coord du pion en face si le coup a un effet sur le haut
    private Coord b;//coord du pion en face si le coup a un effet sur le bas
    private Coord hg; //coord du pion en face si le coup a un effet sur la diag haut gauche
    private Coord hd; //coord du pion en face si le coup a un effet sur la diag haut droite
    private Coord bd; //coord du pion en face si le coup a un effet sur la diag bas droite
    private Coord bg; //coord du pion en face si le coup a un effet sur la diag bas gauche

    public Coup(int x, int y, String coul) throws CouleurException{
        if ((coul != NOIR)&& (coul!= BLANC)){
            throw new CouleurException(coul +" n'est pas une couleur de pion");
        }
        this.x = x;
        this.y = y;
        this.couleur = coul;
        this.aEffet = false;
        this.g = new Coord();
        this.d = new Coord();
        this.h = new Coord();
        this.b = new Coord();
        this.hg = new Coord();
        this.hd = new Coord();
        this.bd = new Coord();
        this.bg = new Coord();
    }
    public Coup(){
        this(-1,-1, NOIR);
    }
    public static Coup coupPasser(){ //coup représentant le fait de passer son tour
        return new Coup(-2, -2, NOIR);
    }
    public static Coup coupIllegal(){ //coup représentant un coup (de joueur humain) invalide ou en attente
        return new Coup(-1,-1, NOIR);
    }
    public static Coup coupFinPartie(){ //coup représentant le choix de mettre fin au jeu (arret)
        return new Coup(-3, -3, NOIR);
    }

    public static Coup coupDejaAppliqué(String couleur){
        return new Coup(-4, -4, couleur);
    }


    @Override
    public String toString() {
        return "Coup{" +
                "aEffet=" + aEffet +
                ", x=" + x +
                ", y=" + y +
                ", couleur='" + couleur + '\'' +
                ", g=" + g +
                ", d=" + d +
                ", h=" + h +
                ", b=" + b +
                ", hg=" + hg +
                ", hd=" + hd +
                ", bd=" + bd +
                ", bg=" + bg +
                '}';
    }

    public boolean getaEffet() {
        return aEffet;
    }
    public Coord getB() {
        return b;
    }

    public Coord getBd() {
        return bd;
    }

    public Coord getBg() {
        return bg;
    }

    public Coord getD() {
        return d;
    }

    public Coord getG() {
        return g;
    }

    public Coord getH() {
        return h;
    }

    public Coord getHd() {
        return hd;
    }

    public Coord getHg() {
        return hg;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setAEffet(boolean aEffet) {
        this.aEffet = aEffet;
    }
}
