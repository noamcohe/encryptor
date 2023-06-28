package crypto.algorithms;

public record DoubleAlgo(Algorithm firstInnerAlgo, Algorithm secondInnerAlgo) implements Algorithm {
    @Override
    public byte[] encrypt(byte[] data) {return (secondInnerAlgo().encrypt(firstInnerAlgo().encrypt(data)));}

    @Override
    public byte[] decrypt(byte[] data) {return (firstInnerAlgo().decrypt(secondInnerAlgo().decrypt(data)));}

    @Override
    public String key() {return "%s|%s".formatted(firstInnerAlgo.key(), secondInnerAlgo.key());}
}
