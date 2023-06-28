package crypto;
import crypto.algorithms.Algorithm;

public interface CryptoExecutor {
    void encryption(Algorithm algo);
    void decryption(Algorithm algo);
}
