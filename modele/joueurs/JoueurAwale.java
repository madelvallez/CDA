package modele.joueurs;

public abstract class JoueurAwale extends Joueur{
    private int ligne ;
    private int grenier;

    public JoueurAwale(String nom, int ligne){
        super(nom);
        assert ligne==0 || ligne==1;
        this.ligne = ligne;
        this.grenier = 0;
    }

    @Override
    public String toString() {
        return "JoueurAwale{" +
                ((Joueur)this).toString()+
                "ligne=" + ligne +
                ", grenier=" + grenier +
                '}';
    }

    public int getGrenier() {
        return grenier;
    }

    public int getLigne() {
        return ligne;
    }

    public void ajouterGrenier(int gain){
        assert gain>=0;
        this.grenier+=gain;
    }
}
