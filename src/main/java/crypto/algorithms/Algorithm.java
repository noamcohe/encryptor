package crypto.algorithms;

public interface Algorithm {
    public byte[] encrypt(byte[] data);
    public byte[] decrypt(byte[] data);
    public String key();
}
