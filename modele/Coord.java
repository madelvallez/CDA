package modele;
//coordonnées comme couple d'entiers (-1,-1) est la coord erreur
//coordonnées représentant Passer : (-2, -2)
// coordonnée représentant fin de partie : (-3,-3)
public class  Coord implements Comparable<Coord>{
    private int x;
    private int y;
    private Coord(int x, int y){
        this.x = x;
        this.y = y;
    }
    public Coord(){
        this(-1, -1);
    }

    @Override
    public String toString() {
        return "Coord{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public int compareTo(Coord o) {
        if (o == null || getClass() != o.getClass()) return -1;
        Coord coord = (Coord) o;
        if (this.x>coord.x){
            return 1;
        }
        else if(this.x<coord.x){
            return -1;
        }
        else{
            if (this.y> coord.y){
                return 1;
            } else if (this.y< coord.y) {
                return -1;
            }
            else{
                return 0;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coord coord = (Coord) o;

        if (x != coord.x) return false;
        return y == coord.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
