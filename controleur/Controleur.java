package controleur;

import modele.plateau.*;
import modele.joueurs.*;
import vue.Ihm;

import java.util.ArrayList;
import java.util.Arrays;

import static modele.plateau.PlateauOthello.BLANC;
import static modele.plateau.PlateauOthello.NOIR;

public class Controleur {
    private Ihm ihm;
    private PlateauOthello plateau;
    private final int dim = 8;
    private Joueur joueur;
    private Joueur adversaire;
    private final static String[] IADISPO = new String[]{"Naif", "MinMax1"};

    private boolean continuer = true ;
    public Controleur(Ihm ihm){
        this.ihm = ihm;
        this.plateau = new PlateauOthello(this.dim);

    }

    public void jouer(){

        this.definirJoueur(); //definition et instanciation du joueur (anciennement joueur1)
        this.definirAdversaire();
        boolean veutRejouer = true;
        while(veutRejouer){
            plateau.initialiser();
            Ihm.message(joueur.getNom()+" joue avec les pions "+ ((JoueurOthello)joueur).getCouleur()+" et "+
                            adversaire.getNom()+" joue avec les pions" + ((JoueurOthello)adversaire).getCouleur());
            String tour = NOIR;
            while (!this.plateau.estFiniePartie() && continuer){
                Coup coupJoue = this.demanderCoup(tour);
                if (coupJoue.getX() != -3 && coupJoue.getY()!=-3) {
                    this.jouerCoup(coupJoue); // teste si le coup est -passer- puis l'applique correctement
                    tour = NOIR.equals(tour) ? BLANC : NOIR; //on passe au joueur suivant
                }else{
                    continuer=false;
                    veutRejouer=false;
                }

            }
            Ihm.afficherPlateau(plateau);
            //annonce victoire et scores de la partie
            int scoreJoueur = this.plateau.score(this.joueur);
            int scoreAdversaire = this.plateau.score(this.adversaire);
            if (scoreJoueur>scoreAdversaire){
                this.joueur.incrementeNbVictoires();
            } else if (scoreJoueur<scoreAdversaire) {
                this.adversaire.incrementeNbVictoires();
            }
            if (!(adversaire instanceof JoueurIA)){ //on echange les couleurs si les deux joueurs sont humain
                ((JoueurOthello)adversaire).setCouleur(NOIR.equals(((JoueurOthello)adversaire).getCouleur()) ? BLANC:NOIR);
                ((JoueurOthello)joueur).setCouleur(NOIR.equals(((JoueurOthello)joueur).getCouleur()) ? BLANC:NOIR);
            }
            Ihm.affichageScore(this.joueur.getNom(), this.adversaire.getNom(), scoreJoueur, scoreAdversaire);
            if (continuer) {
                veutRejouer = rejouer();
            }

        }
        Ihm.affichageVictoire(joueur.getNom(), adversaire.getNom(), joueur.getNbVictoires(), adversaire.getNbVictoires());
    }

    private void definirJoueur(){
        String nom1= recupererNom(1);
        this.joueur = (Joueur)(new JoueurHumainOthello(nom1,NOIR));
    }
    private String recupererNom(int numJ){
        String nom = Ihm.demanderNom(numJ);
        if (nom.equals("")){
            return recupererNom(numJ);
        }
        if (numJ==2 && nom.equals(this.joueur.getNom())){
            Ihm.messageErreur("Tu ne peux pas donner le même nom que ton adversaire");
            return recupererNom(numJ);
        }
        return nom;
    }

    private void definirAdversaire() {
        boolean defAdv = false;
        while (!defAdv) {
            String rep = Ihm.demanderAdversaire();
            if ("non".equals(rep)) {
                String nomAdv = Ihm.demanderNom(2);
                this.adversaire = new JoueurHumainOthello(nomAdv, BLANC);
                defAdv=true;
            } else if ("oui".equals(rep)) {
                boolean defNivIA = false;
                while (!defNivIA) {
                    String rep2 = Ihm.demanderNiveauIA(IADISPO);
                    if (IADISPO[0].equals(rep2)) {
                        this.adversaire = new JoueurIAOthello(IADISPO[0], BLANC,rep2);
                        defNivIA = true;
                    } else if (IADISPO[1].equals(rep2)) {
                        this.adversaire = new JoueurIAOthello(IADISPO[1], BLANC, rep2);
                        defNivIA = true;
                    } else {
                        Ihm.messageErreur("Répondre avec un nom parmi " + Arrays.toString(IADISPO));
                    }
                }
                defAdv=true;
            }
            else{
                Ihm.messageErreur("Repondre avec oui ou non");
            }
        }
    }

    private Coup demanderCoup(String couleur) {

        int[] emplacement = new int[2];
        Coup coupChoisi = new Coup();
        boolean bienJoue = false;
        while (!bienJoue) {
            Ihm.afficherPlateau(plateau);
            ArrayList<Coup> coupsPossibles = this.plateau.listeCoupsPossibles(joueurCourant(couleur));
            if (!(joueurCourant(couleur) instanceof JoueurIA)) {
                emplacement = Ihm.demanderCoup(joueurCourant(couleur).getNom(), couleur, true, this.dim);
                if (emplacement[0]!=-3 && emplacement[1]!=-3){
                    if (emplacement[0]==-2 && emplacement[1]==-2 && coupsPossibles.size()==0){
                        coupChoisi=Coup.coupPasser();
                    }else{
                        for (Coup i : coupsPossibles) {
                            if (i.getX() == emplacement[0] && i.getY() == emplacement[1]) {
                                coupChoisi = i;
                            }
                        }
                    }
                }else{
                    coupChoisi=new Coup(emplacement[0],emplacement[1],NOIR);
                }
            }else{
                coupChoisi=((JoueurIA)joueurCourant(couleur)).choisirCoup(coupsPossibles, this.plateau, joueur);
                Ihm.afficherCoupJoue(joueurCourant(couleur).getNom(), coupChoisi.getX(), coupChoisi.getY());
            }
            if (coupChoisi.getX()!=-1 && coupChoisi.getY()!=-1){
                bienJoue=true;
            }else{
                Ihm.messageErreur("Ce Coup n'est pas valide");
            }
        }
        return coupChoisi;
    }


    public Joueur joueurCourant(String couleur){
        if(((JoueurOthello)this.joueur).getCouleur().equals(couleur)){
            return this.joueur;
        }
        else{
            return this.adversaire;
        }
    }

    private void jouerCoup(Coup coup){
        if (coup.getX()==-2 && coup.getY()==-2){
            Ihm.message(this.adversaire.getNom()+" a passé son tour");
        }else{
            this.plateau.appliqueCoup(coup);
        }
    }



    private boolean rejouer(){
        while (true) {
            String rep = Ihm.messageRejouer();
            if ("Oui".equals(rep)) {
                return true;
            } else if ("Non".equals(rep)) {
                return false;
            }else{
                Ihm.erreurFin();
            }
        }
    }

}

/* ARCHIVE DE CODE QUI PEUT INTERESSER AILLEURS
------TOUR DE JEU HUMAIN-----------------------------------------------------------------------------------------
    if (this.joueur.getCouleur().equals(couleur)) { //si c'est au joueur de jouer
            while (true) {
                Ihm.AfficherPlateau(this.plateau);
                int[] coup = Ihm.DemanderCoup(this.joueur.getNom(), true);
                int row = coup[0];
                int col = coup[1];
                if (this.estCoupValide(row, col, coupsPossibles)) {
                    Coup coupJoue = coupsPossibles.get(indiceCoup(row, col, coupsPossibles));
                    return coupJoue;
                } else if (row == -2 && col==-2 && coupsPossibles.size() == 0) {
                    Ihm.Message(this.joueur.getNom() + " a passé son tour");
                    return new Coup(-2, -2, NOIR); //Coup qui represente passer son tour

                } else {
                    Ihm.MessageErreur("Ce coup n'est pas valide");
                }
            }
        } else { //c'est à l'adversaire de jouer
            return this.adversaire.choisirCoup(coupsPossibles);
        }
-------APPLICATION COUP POUR IA------------------------------------------------------------------------------------
        if (!plateau.estFiniePartie()) {
                    //faire jouer l'adversaire
                    Ihm.AfficherPlateau(this.plateau);
                    ArrayList<Coup> coupsPossibles = this.plateau.listeCoupsPossibles(BLANC);
                    Coup coupAdv = this.adversaire.choisirCoup(coupsPossibles);
                    if(this.adversaire.getIa()) {
                        Ihm.commenterAction_IA(coupAdv.getX(), coupAdv.getY());
                    }else if (coupAdv.getX()== && coupAdv.getY()==-2){
                        Ihm.Message(this.adversaire.getNon()+" a passé son tour");
                    }
                    if(coupAdv.getX()!=-2) {
                        this.plateau.appliqueCoup(coupAdv);
                    }

    private boolean estCoupValide(int row, int col, ArrayList<Coup> coupsPossibles){
            if (0<= row && row <this.dim && 0<=col && col<this.dim) { //test si les coordonnées sont légales
            //teste si le coup est dans la liste des coupsPossibles (donc si il est possible)
            for (Coup coup : coupsPossibles) {
            if (coup.getX() == row && coup.getY() == col) {
            return true;
            }
            }
            return false;
            }
            else{
            return false;
            }
            }
    private int indiceCoup(int x, int y, ArrayList<Coup> coupsPossibles){

            for(int i =0; i<coupsPossibles.size(); i++){
            Coup coup = coupsPossibles.get(i);
            if (coup.getX() == x && coup.getY()==y){
            return i;
            }
            }
            return -1;
            }



*/