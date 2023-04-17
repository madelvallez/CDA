package controleur;

import modele.joueurs.Joueur;
import modele.joueurs.JoueurAwale;
import modele.joueurs.JoueurHumainAwale;


import modele.joueurs.JoueurIA;
import modele.plateau.Coup;
import modele.plateau.CoupAwale;
import modele.plateau.CoupOthello;
import modele.plateau.PlateauAwale;

import vue.Ihm;
import vue.IhmAwa;
import vue.IhmOth;

import java.util.ArrayList;

import static modele.plateau.PlateauOthello.NOIR;

public class ControleurAwa extends Controleur{

    private PlateauAwale plateau;

    private final static String[] IADISPO = new String[]{"Naif", "MinMax1"};

    private boolean continuer = true ;
    private Joueur joueur;
    private Joueur adversaire;

    public ControleurAwa(Ihm ihm) {
        super(ihm);
        this.plateau=new PlateauAwale();
    }

    public void jouerAwa() {
        this.definirJoueur();
        this.definirAdversaire();
        boolean rejouer=true;
        int tour=1;
        while(rejouer){
            plateau.initialiser();
            while(!this.plateau.estFiniePartie()) {
                IhmAwa.tourDeJouer((JoueurAwale) joueur, (JoueurAwale) adversaire,tour);
                CoupAwale coup=this.demanderCoup(tour);
                this.jouerCoup(coup);
                tour= tour==1 ? 0 : 1;
                continuer=false;
                rejouer=false;
            }
            int scoreJoueur = this.plateau.score(this.joueur);
            int scoreAdversaire = this.plateau.score(this.adversaire);
            if (scoreJoueur>scoreAdversaire){
                this.joueur.incrementeNbVictoires();
            } else if (scoreJoueur<scoreAdversaire) {
                this.adversaire.incrementeNbVictoires();
            }
        }
    }

    private void jouerCoup(CoupAwale coup) {
        this.plateau.appliqueCoup(coup);
    }


    private CoupAwale demanderCoup(int tour) {

        int puis;
        CoupAwale coupChoisi = new CoupAwale(-1,-1);
        boolean bienJoue = false;
        while (!bienJoue) {
            IhmAwa.affichagePlateau(plateau);
            puis=IhmAwa.demanderCoup();
            if (tour==1) {
                coupChoisi = new CoupAwale(tour, puis - 1);
            }else{
                coupChoisi= new CoupAwale(tour,6-puis);
            }
            if (coupChoisi.getX()!=-1 && coupChoisi.getY()!=-1){
                bienJoue=true;
            }else{
                Ihm.messageErreur("Ce Coup n'est pas valide");
            }
        }
        return coupChoisi;
    }


    private void definirJoueur(){
        String nom=Ihm.demanderNom(1);
        this.joueur=new JoueurHumainAwale(nom,1);
    }

    private void definirAdversaire() {
        boolean ok = false;
        while (!ok) {
            String b = Ihm.demanderAdversaire();
            if (b.equals("non")) {
                String nom = Ihm.demanderNom(0);
                this.adversaire = new JoueurHumainAwale(nom, 0);
                ok = true;
            } else if ("oui".equals(b)) {
                Ihm.messageErreur("Il n'est pas encore possible de jouer conttre un ordinateur");
            }
        }
    }



}
