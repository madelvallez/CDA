package controleur;

public class MauvaisCoupException extends RuntimeException{
    public MauvaisCoupException(){
        super();
    }
    public MauvaisCoupException(String s){
        super(s);
    }
}

