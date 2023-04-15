package modele.plateau;

public class CoupAwale implements Coup{
    private int ligne; // 0 ou 1
    private int puis; // entier entre 0 et 5
    private boolean aEffet;

    private int capture ;

    public CoupAwale(int ligne, int puis){
        this.puis = puis;
        this.ligne = ligne;
        this.aEffet = false;
        this.capture = 0;
    }

    public static CoupAwale coupPasser(){ return new CoupAwale(-2, -2);} // -2 -2
    public static CoupAwale coupIllegal(){return new CoupAwale(-1, -1);} // -1 -1
    public static CoupAwale coupDejaApplique(){return new CoupAwale(-4, -4);} // -4 -4
    public static CoupAwale coupFinPartie(){return new CoupAwale(-3, -3);} //-3 -3

    public int getLigne() {
        return ligne;
    }

    public int getPuis() {
        return puis;
    }

    public boolean getaEffet() {
        return aEffet;
    }

    public void setaEffet(boolean aEffet) {
        this.aEffet = aEffet;
    }

    public int getCapture(){
        return this.capture;
    }

    public void ajouterCapture(int gain){
        this.aEffet = aEffet || gain!=0 ; //si on ajoute effectivement des graines à la capture, le coup a un effet
        this.capture+= gain;
    }

    public boolean isIllegal(){
        return this.ligne==-1 && this.puis==-1;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public String toString() {
        return "CoupAwale{" +
                "ligne=" + ligne +
                ", puis=" + puis +
                '}';
    }
}
