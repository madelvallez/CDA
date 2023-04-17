package controleur;

import modele.joueurs.*;
import modele.plateau.Coup;
import modele.plateau.CoupOthello;
import modele.plateau.PlateauOthello;
import vue.Ihm;
import vue.IhmOth;

import java.util.ArrayList;
import java.util.Arrays;

import static modele.plateau.PlateauOthello.BLANC;
import static modele.plateau.PlateauOthello.NOIR;

public class ControleurOth extends Controleur{


    private PlateauOthello plateau;
    private final int dim = 8;
    private final static String[] IADISPO = new String[]{"Naif", "MinMax1"};

    private boolean continuer = true ;
    private Joueur joueur;
    private Joueur adversaire;

    public ControleurOth(Ihm ihm) {
        super(ihm);
        this.plateau = new PlateauOthello(this.dim);
    }


    public void jouerOth(){

        this.definirJoueur(); //definition et instanciation du joueur (anciennement joueur1)
        this.definirAdversaire();
        boolean veutRejouer = true;
        while(veutRejouer){
            plateau.initialiser();

            Ihm.message(joueur.getNom()+" joue avec les pions "+ ((JoueurOthello)joueur).getCouleur()+" et "+
                    adversaire.getNom()+" joue avec les pions" + ((JoueurOthello)adversaire).getCouleur());
            String tour = NOIR;
            while (!this.plateau.estFiniePartie()){
                Coup coupJoue = this.demanderCoup(tour);
                //if (coupJoue.getX() != -3 && coupJoue.getY()!=-3) {
                this.jouerCoup(coupJoue); // teste si le coup est -passer- puis l'applique correctement
                tour = NOIR.equals(tour) ? BLANC : NOIR; //on passe au joueur suivant
                //}else{
                veutRejouer=false;
                //}

            }
            IhmOth.afficherPlateau(plateau);
            //annonce victoire et scores de la partie
            int scoreJoueur = this.plateau.score(this.joueur);
            int scoreAdversaire = this.plateau.score(this.adversaire);
            if (scoreJoueur>scoreAdversaire){
                this.joueur.incrementeNbVictoires();
            } else if (scoreJoueur<scoreAdversaire) {
                this.adversaire.incrementeNbVictoires();
            }
            if (!(adversaire instanceof JoueurIA)){ //on échange les couleurs si les deux joueurs sont humains
                ((JoueurOthello)adversaire).setCouleur(NOIR.equals(((JoueurOthello)adversaire).getCouleur()) ? BLANC:NOIR);
                ((JoueurOthello)joueur).setCouleur(NOIR.equals(((JoueurOthello)joueur).getCouleur()) ? BLANC:NOIR);
            }
            Ihm.affichageScore(this.joueur.getNom(), this.adversaire.getNom(), scoreJoueur, scoreAdversaire);
            if (continuer) {
                veutRejouer = rejouer();
            }

        }
        //Ihm.affichageVictoire(joueur.getNom(), adversaire.getNom(), joueur.getNbVictoires(), adversaire.getNbVictoires());
    }

    private void definirJoueur(){
        String nom1= recupererNom(1);
        this.joueur = new JoueurHumainOthello(nom1,NOIR);
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

        int[] emplacement ;
        Coup coupChoisi = new CoupOthello();
        boolean bienJoue = false;
        while (!bienJoue) {
            IhmOth.afficherPlateau(plateau);
            ArrayList<Coup> coupsPossibles = this.plateau.listeCoupsPossibles(joueurCourant(couleur));
            if (!(joueurCourant(couleur) instanceof JoueurIA)) {
                emplacement = IhmOth.demanderCoup(joueurCourant(couleur).getNom(), couleur, true, this.dim);
                if (emplacement[0]!=-3 && emplacement[1]!=-3){
                    if (emplacement[0]==-2 && emplacement[1]==-2 && coupsPossibles.size()==0){
                        coupChoisi=CoupOthello.coupPasser();
                    }else{
                        for (Coup i : coupsPossibles) {
                            if (i.getX() == emplacement[0] && i.getY() == emplacement[1]) {
                                coupChoisi = i;
                            }
                        }
                    }
                }else{
                    coupChoisi=new CoupOthello(emplacement[0],emplacement[1],NOIR);
                }
            }else{
                coupChoisi=((JoueurIA)joueurCourant(couleur)).choisirCoup(coupsPossibles, this.plateau, joueur);
                IhmOth.afficherCoupJoue(joueurCourant(couleur).getNom(), coupChoisi.getX(), coupChoisi.getY());
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

}
