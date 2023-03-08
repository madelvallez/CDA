package controleur;

import modele.Plateau;
import vue.Ihm;
import modele.Joueur;
import modele.Adversaire;
import static modele.Plateau.blanc;
import static modele.Plateau.noir;
import static modele.Plateau.vide;

public class Controleur {
    private Ihm ihm;
    private Plateau plateau;
    private final int dim = 8;

    private Joueur joueur;
    private Adversaire adversaire;
    private String[] iaDispo = new String[]{"Naif", "MinMax"};

    public Controleur(Ihm ihm){
        this.ihm = ihm;
        this.plateau = new Plateau(this.dim);

    }

    public void jouer(){

        this.definirJoueur(); //definition et instanciation du joueur (anciennement joueur1)
        this.definirAdversaire();
    }

    private void definirJoueur(){
        String nom1= recupererNom(1);
        this.joueur = new Joueur(nom1,noir);
    }
    private String recupererNom(int numJ){
        String nom = Ihm.DemanderNom(numJ);
        if (nom.equals("")){
            return recupererNom(numJ);
        }
        if (numJ==2 && nom.equals(this.joueur.getNom())){
            Ihm.MessageErreur("Tu ne peux pas donner le même nom que ton adversaire");
            return recupererNom(numJ);
        }
        return nom;
    }

    private void definirAdversaire() {
        boolean defAdv = false;
        while (defAdv == false) {
            String rep = Ihm.demanderAdversaire();
            if ("non".equals(rep)) {
                this.adversaire = new AdversaireHumain();
                defAdv=true;
            } else if ("oui".equals(rep)) {
                boolean defNivIA = false;
                while (defNivIA == false) {
                    String rep2 = Ihm.demanderNiveauIA(this.iaDispo);
                    if (iaDispo[0].equals(rep2)) {
                        this.adversaire = new AdversaireIANaif();
                        defNivIA = true;
                    } else if (iaDispo[1].equals(rep2)) {
                        this.adversaire = new AdversaireIAMoyen();
                        defNivIA = true;
                    } else {
                        Ihm.MessageErreur("Répondre avec " + this.iaDispo.toString());
                    }
                }
                defAdv=true;
            }
            else{
                Ihm.MessageErreur("Repondre avec oui ou non");
            }
        }
    }



}
