package modele.plateau;

public class CoupAwale implements Coup{
    private int ligne; // 0 ou 1
    private int puis; // entiern entre 0 et 5

    public CoupAwale(int ligne, int puis){
        this.puis = puis;
        this.ligne = ligne;
    }

    public static Coup coupPasser(){ return new CoupAwale(-2, -2);} // -2 -2
    public static Coup coupIllegal(){return new CoupAwale(-1, -1);} // -1 -1
    public static Coup coupDejaApplique(){return new CoupAwale(-4, -4);} // -4 -4
    public static Coup coupFinPartie(){return new CoupAwale(-3, -3);} //-3 -3

    public int getLigne() {
        return ligne;
    }

    public int getPuis() {
        return puis;
    }

    @Override
    public String toString() {
        return "CoupAwale{" +
                "ligne=" + ligne +
                ", puis=" + puis +
                '}';
    }
}
