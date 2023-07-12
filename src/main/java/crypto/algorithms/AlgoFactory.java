package crypto.algorithms;


public class AlgoFactory {
    public Cipher create(byte key, String name) {return new Cipher(key, name);}
    public ReverseAlgo create(Algorithm inner) {return new ReverseAlgo(inner);}
    public DoubleAlgo create(Algorithm firstInner, Algorithm secondInner) {return new DoubleAlgo(firstInner, secondInner);}
}
