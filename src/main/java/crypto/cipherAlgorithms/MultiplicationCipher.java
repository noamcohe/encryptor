package crypto.cipherAlgorithms;
import utils.Constants;
import java.util.Random;

public class MultiplicationCipher extends Cipher {
    private int decryptionKey;
    public int decryptionKey() {
        return decryptionKey;
    }
    public void decryptionKey(int decryptionKey) {
        this.decryptionKey = decryptionKey;
    }
    public MultiplicationCipher() {
        this.decryptionKey = 0;
    }


    /**
     * Implements Both encryption and decryption of a string.
     * @param data The string needs to be encrypted / decrypted.
     * @return The data after encryption / decryption.
     */
    private String encDec(String data) {
        StringBuilder encDecData = new StringBuilder();
        char encDecChar;

        for (char ch : data.toCharArray()) {
            encDecChar = (char) ((ch * key()) % Constants.CHARACTERS_RANGE);
            encDecData.append(encDecChar);
        }

        decryptionKey(calcDecryptionKey(key()));

        return encDecData.toString();
    }
    @Override
    public String encrypt(String data) {
        return encDec(data);
    }
    @Override
    public String decrypt(String data) {
        return encDec(data);
    }


    @Override
    public int getRandomKey() {
        int key;
        do {
            key = (new Random()).nextInt(1, Constants.CHARACTERS_RANGE);
        } while ((key % 2) == 0);

        return key;
    }
    private int calcDecryptionKey(int key) {
        for (int i = 0; i < Constants.CHARACTERS_RANGE; i++) {
            if (((key * i) % Constants.CHARACTERS_RANGE) == 1) {
                return i;
            }
        }

        return -1;
    }


    @Override
    public String toString() {
        return """
                
                %s
                %s%b
                %s%d
                %s%d
                %s%s
                %s%s
                %s%b
                
                """.formatted(Constants.MULTIPLICATION_NAME,
                Constants.ENC_NAME, encryptionFlag(),
                Constants.KEY_MESSAGE, key(),
                Constants.DEC_KEY, decryptionKey(),
                Constants.INPUT_FILE, sourceFilePath(),
                Constants.OUTPUT_FILE, destFilePath(),
                Constants.IS_SUCCEEDED, isSucceeded());
    }
}
