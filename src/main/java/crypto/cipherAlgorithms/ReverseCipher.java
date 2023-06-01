package crypto.cipherAlgorithms;
import utils.Constants;

public class ReverseCipher extends Cipher {
    private final Cipher innerCipher;

    public ReverseCipher(Cipher innerCipher) {
        this.innerCipher = innerCipher;
    }
    public Cipher innerCipher() {
        return innerCipher;
    }

    @Override
    public String encrypt(String data) {
        initializeInnerCipher();
        return innerCipher().decrypt(data);
    }
    @Override
    public String decrypt(String data) {
        initializeInnerCipher();
        return innerCipher().encrypt(data);
    }

    public void initializeInnerCipher() {
        this.innerCipher.key(this.key());
        this.innerCipher.encryptionFlag(!this.encryptionFlag());
        this.innerCipher.sourceFilePath(this.sourceFilePath());
        this.innerCipher.destFilePath(this.destFilePath());
    }

    @Override
    public String toString() {
        return """
                
                %s
                %s%b
                %s%d
                %s%s
                %s%s
                
                %s
                """.formatted(Constants.REVERSE_NAME,
                Constants.ENC_NAME, encryptionFlag(),
                Constants.KEY_MESSAGE, key(),
                Constants.INPUT_FILE, sourceFilePath(),
                Constants.OUTPUT_FILE, destFilePath(),
                innerCipher());
    }
}
