package crypto.algorithms;

public interface Algorithm {
    byte[] encrypt(byte[] data);
    byte[] decrypt(byte[] data);
    String key();
}
