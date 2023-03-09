package controleur;

import modele.Joueur;
import vue.Ihm;
import modele.Plateau;
import modele.Coup;
import java.util.ArrayList;

public class controleur
    {
        // Declare the constants
        /** A constant representing an empty square on the board. */
        private static final String NO_CHIP = Plateau.VIDE;
        /** A constant representing a black peg on the board. */
        private static final String BLACK_UP = Plateau.NOIR;
        /** A constant representing a white peg on the board. */
        private static final String WHITE_UP = Plateau.BLANC;
        /** A constant indicating the size of the game board. */
        private static final int BOARD_SIZE = 8;

        // Declare the instance variables
        /** This board contains the physical state of the game. */
        private Plateau Board = new Plateau(BOARD_SIZE);

        private Ihm ihm ;
        private Joueur joueur1;
        private Joueur joueur2;

        private String[] IADISPO = new String[]{"Naif", "MinMax"};
        public controleur(Ihm ihm)
        {
            // initialisation du plateau de jeu
            this.Board.initialiser() ;
            //initialisation de l'ihm
            this.ihm = ihm;

        }


        public void play()
        {
            // definition des joueurs + recuperation des nom
            String nom1= recupererNom(1);
            this.joueur1 = new Joueur(nom1,BLACK_UP);
            String nom2= recupererNom(2);
            this.joueur2 = new Joueur(nom2, WHITE_UP);

            String ia =Ihm.demanderAdversaire();
            if (ia.equals("oui")){
                this.playIA();
            } else if (!ia.equals("non")) {
                this.play();
            }
            /*
            DEFINITR LES JOUEURS ET RECUPERER LEUR NOMS => dans le constructeur
            */
            // le premier coup est fait par le NOIR
            String move = BLACK_UP;

            // tant qu'il est encore possible de jouer
            while (!this.Board.estFiniePartie()) {
                int row = 0;
                int col = 0;
                boolean valid = false; //indique si le joueur courant a joué (un coup legal au moins)

                while (!valid) {
                    try {
                        Ihm.AfficherPlateau(this.Board);
                        int[] coup = Ihm.DemanderCoup(joueurCourant(move).getNom(), true);

                        row = coup[0];
                        col = coup[1];
                        this.takeTurn(move, row, col); //applique le coup si legal   //ce qui rique de lever une erreur
                        valid = true; //si tout c'est bien passé
                    } catch (MauvaisCoupException e) {
                        valid = false; //en cas d'erreur
                    }
                }

                // After the turn, switch the players
                if (move == BLACK_UP) {
                    move = WHITE_UP;
                } else {
                    move = BLACK_UP;
                }
            }
            //définition du vainqueur de la partie (pas de la suite de partie)
            int score1 = Board.score(joueur1.getCouleur());
            int score2 = Board.score(joueur2.getCouleur());
            if(score1>score2) {
                Ihm.Message(joueur1.getNom()+" a gagné");
                joueur1.incrementeNbVictoires();
            } else if (score1<score2) {
                Ihm.Message(joueur2.getNom()+" a gagné");
                joueur2.incrementeNbVictoires();
            }
            else {
                Ihm.Message("ex-aequo");
            }
            //proposition de rejouer
            this.rejouer();
        }
        public void playIA(){
            String niv= Ihm.demanderNiveauIA(this.IADISPO);
            if (niv.equals("Naif")){
                this.Naif();
            } else if (niv.equals("MinMax")) {
                this.MinMax();
            }else{
                this.playIA();
            }
        }
        public void Naif(){}

        public void MinMax(){}
        /**
         * This method will handle the logic of a single turn in the Othello game.
         * It should "flip" the opponents pegs wherever they are surrounded in a line
         * by the latest peg played and the first peg of the player's same colour in
         * that line.
         * The method this.updateView() should be called at the end of this method.
         * PRE: 0 <= row < this.gameBoard.getRows() && 0 <= col < this.gameBoard.getColumns()
         *      colour == BLACK_UP || WHITE_UP
         *      The row and col values are a valid location for a move in the game.
         * POST: The pegs of the opposite colour are flipped according to the rules of Othello.
         */
        private void takeTurn(String turn, int row, int col) throws MauvaisCoupException
        {
            //recuperation de la liste des coups possibles pour la couleur col
            ArrayList<Coup> coupsPossibles = this.Board.listeCoupsPossibles(turn);
            if (validMove(turn, row, col, coupsPossibles)){
                this.Board.appliqueCoup(coupsPossibles.get(indiceCoup(row, col, coupsPossibles)));
            } else if (row==-2 && coupsPossibles.size()==0) {

                Ihm.Message("Vous avez passé votre tour");

            } else{
                Ihm.MessageErreur("Ce coup n'est pas valide");
                throw new MauvaisCoupException() ;
            }

        }

    /**
     * Cette methode determine si le joueur a fait un coup valide
     * Un coup valide  : le joueur choisi une case VIDE
     * POST: Retourne true si le coup est valide
     */
    private boolean validMove(String turn, int row, int col, ArrayList<Coup> coupsPossibles)
    {

        String oppCol=BLACK_UP;
        if (turn==BLACK_UP)
        {
            oppCol=WHITE_UP;
        }

        if ((0<= row && row <=7 && 0<=col && col<=7) && (estCoupPossible(row, col, coupsPossibles))){
            return true;
        }
        else{
            return false;
        }

    }

    private Joueur joueurCourant(String coul){
        if (this.joueur1.getCouleur()==coul){
            return this.joueur1;
        }
        else{
            return this.joueur2;
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

    private String recupererNom(int numJ){
        String nom = Ihm.DemanderNom(numJ);
        if (nom==""){
            return recupererNom(numJ);
        }
        if (numJ==2 && this.joueur1.getNom()==nom){
            Ihm.MessageErreur("Tu ne peux pas donner le même nom que ton adversaire");
            return recupererNom(numJ);
        }
        return nom;
    }

    private void rejouer(){
        String rep = Ihm.MessageRejouer();
        if (rep.equals("Oui")){
            //on echange les joueurs pour la partie suivante
            this.joueur1.setCouleur( joueur1.getCouleur()==BLACK_UP? WHITE_UP:BLACK_UP);
            this.joueur2.setCouleur( joueur2.getCouleur()==BLACK_UP? WHITE_UP:BLACK_UP);
            this.Board.initialiser();
            Ihm.Message("Chaque joueur joue dans la couleur de son adversaire de la partie précédente");
            this.play();
        }
        else if (rep.equals("Non")){
            Ihm.AffichageScore(this.joueur1.getNom(), this.joueur2.getNom(),this.joueur1.getNbVictoires(),this.joueur2.getNbVictoires());
        }
        else{
            Ihm.ErreurFin();
            this.rejouer();
        }
    }

    private boolean estCoupPossible(int x, int y, ArrayList<Coup> coupsPossibles){
        for(int i =0; i<coupsPossibles.size(); i++){
            Coup coup = coupsPossibles.get(i);
            if (coup.getX() == x && coup.getY()==y){
                return true;
            }
        }
        return false;
    }
}
