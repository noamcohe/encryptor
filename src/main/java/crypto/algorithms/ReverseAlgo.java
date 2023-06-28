package crypto.algorithms;

public record ReverseAlgo(Algorithm innerAlgo) implements Algorithm {
    @Override
    public byte[] encrypt(byte[] data) {
        return innerAlgo().decrypt(data);
    }
    @Override
    public byte[] decrypt(byte[] data) {
        return innerAlgo().encrypt(data);
    }
    @Override
    public String key() {
        return innerAlgo.key();
    }
}
