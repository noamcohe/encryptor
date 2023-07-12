package crypto;
import crypto.algorithms.Algorithm;
import java.nio.file.Path;

public interface CryptoExecutor {
    void encryption(Algorithm algo, Path srcPath, Path destPath);
    void decryption(Algorithm algo, Path srcPath, Path destPath);
}
