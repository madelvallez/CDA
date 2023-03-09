package modele;

import java.util.ArrayList;
import static java.util.Arrays.fill;
//import modele.Coup;

public class Plateau {

    public final static String BLANC = " \u26AA ";
    public final static String NOIR = " \u26AB ";
    public final static String VIDE = " \uD83D\uDFE9 ";
    private String[][] grille ;
    private int dim;

    public Plateau(int dim){
        this.dim = dim;
        this.grille = new String[dim][dim];
        for (int i=0; i<this.dim; i++){
            fill(this.grille[i], VIDE);
        }

    }

    @Override
    public String toString() {
        String res = "   A   B   C   D   E    F   G   H"+"\n";
        for (int i=0; i< this.dim; i++){
            res = res + Integer.toString(i+1);
            for(int j=0; j<this.dim; j++){
                res = res  + this.grille[i][j] ;
            }
            res = res + "\n";
        }
        return res;
    }

    public void initialiser(){
        /**
         * initialise le plateau pour un début de partie
         */
        //fait un plateau VIDE
        for(int i=0; i<this.dim; i++){
            for (int j=0; j<this.dim; j++) {
                this.grille[i][j] = VIDE;
            }
        }
        //ajoute les pions du centre
        int ind_centre = (this.dim-1) /2;
        this.grille[ind_centre][ind_centre] = BLANC;
        this.grille[ind_centre][ind_centre+1] = NOIR;
        this.grille[ind_centre+1][ind_centre] = NOIR;
        this.grille[ind_centre+1][ind_centre+1] = BLANC;
    }

    private void placerPion(String couleur, int i, int j) throws CouleurException{
        /**
         * place un pion de la couleur @param couleur ( BLANC ou NOIR ) dans la case [i][j]
         * la case est supposée légale
         */
        if ((couleur != NOIR)&& (couleur!= BLANC)){
            throw new CouleurException(couleur +" n'est pas une couleur de pion");
        }
        else {
            this.grille[i][j] = couleur;
        }
    }

    private Coup analyseCoup(int x, int y, String coul) throws CouleurException{
        //teste si la couleur est légale
        if ((coul != NOIR)&& (coul!= BLANC)){
            throw new CouleurException(coul +" n'est pas une couleur de pion");
        }
        /* les cordonnées sont OK*/
        if (!(0<=x && x<this.dim && 0<=y && y<this.dim)){
            return new Coup();
        }
        if (grille[x][y]!= VIDE){
            return new Coup();
        }
        String coulOpp = coul==NOIR ? BLANC : NOIR; //donne la couleur opposée
        Coup coup = new Coup(x,y, coul);

        //a un effet sur la ligne ------------------------------------------------------------------------------
        //cote gauche de la ligne
        int j=y-1;
        while (j>=0 && this.grille[x][j]==coulOpp){
            j--;
        }
        //si on sort et que on n'as pas trouvé un bord, on verifie que le pion suivant est de la bonne couleur
        if (j>=0 && grille[x][j]==coul && j<y-1){
            coup.getG().setX(x) ;        // ^pour eviter le cas couleur a cote d'elle même
            coup.getG().setY(j);
            coup.setAEffet(true);
        }
        //cote droit sur la ligne
        j=y+1;
        while (j<this.dim && this.grille[x][j]==coulOpp){
            j++;
        }
        //si on sort et que on n'as pas trouvé un bord, on verifie que le pion suivant est de la bonne couleur
        if (j<this.dim && grille[x][j]==coul && j>y+1){
            coup.getD().setX(x);
            coup.getD().setY(j);
            coup.setAEffet(true);
        }
        //a un effet sur la colonne-----------------------------------------------------------------------------
        // cote haut de lacolonne
        int i = x-1;
        while(i>=0 && this.grille[i][y]==coulOpp){
            i--;
        }
        if(i>=0 && grille[i][y]==coul && i<x-1){
            coup.getH().setX(i);
            coup.getH().setY(y);
            coup.setAEffet(true);
        }
        //cote bas de la colonne
        i=x+1;
        while(i<this.dim && this.grille[i][y]==coulOpp){
            i++;
        }
        if (i<this.dim && this.grille[i][y]==coul && i> x+1){
            coup.getB().setX(i);
            coup.getB().setY(y);
            coup.setAEffet(true);
        }
        //a effet diag hg-bd------------------------------------------------------------------------------------------
        //cote haut
        i=x-1;
        j=y-1;
        while(i>=0 && j>=0 && this.grille[i][j]==coulOpp){
            i--;
            j--;
        }
        if (i>=0 && j>=0 && this.grille[i][j]==coul && i<x-1 && j<y-1){
            coup.getHg().setX(i);
            coup.getHg().setY(j);
            coup.setAEffet(true);
        }
        //cote bas
        i=x+1;
        j=y+1;
        while(i<this.dim && j<this.dim && this.grille[i][j]==coulOpp){
            i++;
            j++;
        }
        if(i<this.dim && j<this.dim && this.grille[i][j]==coul && i>x+1 && j> y+1){
            coup.getBd().setX(i);
            coup.getBd().setY(j);
            coup.setAEffet(true);
        }
        //a effet diag hg-bd------------------------------------------------------------------------------------
        //cote haut
        i=x-1;
        j=y+1;
        while(i>=0 && j<this.dim && this.grille[i][j]==coulOpp){
            i--;
            j++;
        }
        if (i>=0 && j<this.dim && this.grille[i][j]==coul && i<x-1 && j>y+1 ){
            coup.getHd().setX(i);
            coup.getHd().setY(j);
            coup.setAEffet(true);
        }
        //cotebas
        i=x+1;
        j=y-1;
        while(i<this.dim && j>=0 && this.grille[i][j]==coulOpp){
            i++;
            j--;
        }
        if(i<this.dim && j>=0 && this.grille[i][j]==coul && i>x+1 && j<y-1){
            coup.getBg().setX(i);
            coup.getBg().setY(j);
            coup.setAEffet(true);
        }
        return coup;
    }

    public ArrayList<Coup> listeCoupsPossibles(String coul) throws CouleurException{
        //teste si coul est legal
        if ((coul != NOIR)&& (coul!= BLANC)){
            throw new CouleurException(coul +" n'est pas une couleur de pion");
        }
        ArrayList<Coup> liste = new ArrayList();
        Coup coup;
        for (int i=0; i<this.dim; i++){
            for (int j=0; j<this.dim; j++){
                coup = this.analyseCoup(i,j,coul);
                if (coup.getaEffet()){
                    liste.add(coup);
                }
            }
        }
        return liste;
    }

    private void retournerPion(int x, int y)throws CouleurException{
        /**
         * on suppose qu'il y a deja un pion
         */
        String couleur = this.grille[x][y];
        if ((couleur != NOIR)&& (couleur!= BLANC)){
            throw new CouleurException(couleur +" n'est pas une couleur de pion -> il n'y a pas de pion en ("
            + ((Integer)x).toString()+", "+((Integer)y).toString()+")");
        }
        this.grille[x][y] = this.grille[x][y]==NOIR ? BLANC :NOIR;
    }

    public void appliqueCoup(Coup coup) {
        /**
         * Applque le coup sur this.grille
         * /!\ le coup est supposé légal
         */
        Coord coupleVIDE = new Coord();
        String coul = coup.getCouleur();
        // on place le pion
        this.placerPion(coup.getCouleur(), coup.getX(), coup.getY());
        //traitement de la ligne
        if (!(coup.getG().equals(coupleVIDE))) {
            for (int j = coup.getY()-1; j > coup.getG().getY(); j--) {
                this.retournerPion(coup.getX(), j);
            }
        }
        if (!coup.getD().equals(coupleVIDE)){
            for(int j = coup.getY()+1; j<coup.getD().getY(); j++){
                this.retournerPion(coup.getX(), j);
            }
        }
        //traitement colonne
        if (!coup.getH().equals(coupleVIDE)){
            for(int i=coup.getX()-1; i>coup.getH().getX(); i--){
                this.retournerPion(i,coup.getY());
            }
        }
        if(!coup.getB().equals(coupleVIDE)){
            for(int i=coup.getX()+1; i<coup.getB().getX(); i++){
                this.retournerPion(i,coup.getY());
            }
        }
        //traitemment diagonale hg_bd
        if(!coup.getHg().equals(coupleVIDE)){
            for(int i=coup.getX()-1, j=coup.getY()-1; (i>coup.getHg().getX() && j>coup.getHg().getY()); i--, j--){
                this.retournerPion(i,j);
            }
        }
        if(!coup.getBd().equals(coupleVIDE)){
            for(int i=coup.getX()+1, j=coup.getY()+1; (i<coup.getBd().getX() && j<coup.getBd().getY()); i++,j++){
                this.retournerPion(i,j);
            }
        }
        //traitement diagonale hd-bg
        if(!coup.getHd().equals(coupleVIDE)){
            for(int i=coup.getX()-1, j=coup.getY()+1; (i>coup.getHd().getX() && j<coup.getHd().getY()); i--, j++){
                this.retournerPion(i,j);
            }
        }
        if(!coup.getBg().equals(coupleVIDE)){
            for(int i=coup.getX()+1, j=coup.getY()-1; (i<coup.getBg().getX() && j>coup.getBg().getY()); i++, j--){
                this.retournerPion(i,j);
            }
        }
    }

    public int score(String couleur)throws CouleurException{
        /**
         * @param couleur : une couleur de pion (NOIR ou BLANC)
         * @return le nb de pion de la couleur [couleur] sur le plateau
         */
        if ((couleur != NOIR)&& (couleur!= BLANC)){
            throw new CouleurException(couleur +" n'est pas une couleur de pion");
        }
        int cpt = 0;
        for(int i=0; i<this.dim; i++){
            for (int j=0;j<this.dim; j++){
                if (this.grille[i][j]==couleur){
                    cpt++;
                }
            }
        }
        return cpt;
    }

    public boolean estFiniePartie(){
        ArrayList<Coup> coupsNOIR = listeCoupsPossibles(NOIR);
        if (coupsNOIR.size() !=0){
            return false;
        }
        ArrayList<Coup> coupsBLANC = listeCoupsPossibles(BLANC);
        if(coupsBLANC.size()!=0){
            return false;
        }
        return true;
    }

    public String[][] getGrille() {
        return grille;
    }
}
