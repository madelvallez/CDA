package modele;

public class FeuilleConfigurations implements ArbreConfigurations{
    private int score;

    @Override
    public ArbreConfigurations minMax() {
        return null;
    }

    @Override
    public int fonctionEvaluation() {
        return 0;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(ArbreConfigurations arbreConfigurations) {
        return score - arbreConfigurations.getScore();
    }
}
