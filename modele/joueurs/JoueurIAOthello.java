package modele.joueurs;

import java.util.List;

import controleur.CouleurException;
import modele.strategies_de_jeu.*;
import modele.plateau.*;

public class JoueurIAOthello extends JoueurOthello implements JoueurIA {

    private StrategieJeu strategie;

    public JoueurIAOthello(String nom, String couleur, String niveau) throws CouleurException {
        super(nom,couleur);
        if ("Naif".equals(niveau)){
            this.strategie = new StrategieNaif();
        }else { // if ("MinMax1".equals(niveau))
            this.strategie = new MinMax1(this.getCouleur());
        }
    }

    public boolean getIA(){
        return true;
    }

    public Coup choisirCoup(List<Coup> coupsPossibles, PlateauOthello plateau, Joueur j) {
        return strategie.donnerCoup(coupsPossibles, plateau, new Joueur[] {j, this});
    }

//    private Coup jeuIAMinMax(PlateauOthello plateau, ArrayList<Coup> coupsPossibles) {
//        PlateauOthello p = plateau.copie();
//        ArbreConfigurations ia = new NoeudConfigurations(p, Coup.coupDejaAppliqué(couleur), couleur, true, 3, coupsPossibles);
//        return ((NoeudConfigurations)ia).getMeilleurCoup();
//    }
}