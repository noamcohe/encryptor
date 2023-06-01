package crypto.cipherAlgorithms;
import utils.Constants;
import java.nio.file.Path;
import java.util.Random;

public abstract class Cipher {
    private int key;
    private Path sourceFilePath;
    private Path destFilePath;
    private boolean encryptionFlag;
    public boolean isSucceeded;


    public int key() {
        return key;
    }
    public void key(int key) {
        this.key = key;
    }
    public Path sourceFilePath() {
        return sourceFilePath;
    }
    public void sourceFilePath(Path inputFilePath) {
        this.sourceFilePath = inputFilePath;
    }
    public Path destFilePath() {
        return destFilePath;
    }
    public void destFilePath(Path outputFilePath) {
        this.destFilePath = outputFilePath;
    }
    public boolean encryptionFlag() {
        return encryptionFlag;
    }
    public void encryptionFlag(boolean encryptionFlag) {this.encryptionFlag = encryptionFlag;}
    public boolean isSucceeded() {
        return this.isSucceeded;
    }
    public void isSucceeded(boolean isSucceeded) {
        this.isSucceeded = isSucceeded;
    }


    public abstract String encrypt(String data);
    public abstract String decrypt(String data);
    public int getRandomKey() {
        return (new Random()).nextInt(1, Constants.CHARACTERS_RANGE);
    }
}
