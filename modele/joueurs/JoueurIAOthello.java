package modele.joueurs;

import java.util.List;
import modele.strategies_de_jeu.*;
import modele.plateau.*;
import static modele.plateau.Plateau.BLANC;
import static modele.plateau.Plateau.NOIR;

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

    public Coup choisirCoup(List<Coup> coupsPossibles, Plateau plateau) {
        return strategie.donnerCoup(coupsPossibles, plateau);
    }

//    private Coup jeuIAMinMax(Plateau plateau, ArrayList<Coup> coupsPossibles) {
//        Plateau p = plateau.copie();
//        ArbreConfigurations ia = new NoeudConfigurations(p, Coup.coupDejaAppliqué(couleur), couleur, true, 3, coupsPossibles);
//        return ((NoeudConfigurations)ia).getMeilleurCoup();
//    }
}