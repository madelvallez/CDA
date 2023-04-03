package modele;

public class MinMax1 extends StrategieMinMax{
    public MinMax1(String coul) {
        super(coul);
    }
    @Override
    public int fonctionEvaluation(Plateau p, String coul) {
        int DIM = p.getDIM();
        int score = 0;
        String coulOpp = Plateau.opposeCouleur(coul); //on évalue du point de vue de la prochaine personne à jouer (et qui ne joue pas)
        for(int i=0; i<DIM; i++){
            for(int j=0; j<DIM; j++){
                if (coulOpp.equals(p.getGrille()[i][j])) {
                    if (((i == 0) && (j == 0)) || ((i == 0) && (j == DIM - 1)) || ((i == DIM - 1) && (j == 0)) || ((i == DIM - 1) && (j == DIM - 1))) {
                        score += 11;
                    } else if ((i == 0) || (j == 0) || (i == DIM - 1) || (j == DIM - 1)) {
                        score += 6;
                    } else {
                        score += 1;
                    }
                }
            }
        }
        return score;
    }
}
