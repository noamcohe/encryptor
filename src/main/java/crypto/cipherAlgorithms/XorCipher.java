package crypto.cipherAlgorithms;
import utils.Constants;


public class XorCipher extends Cipher {
    /**
     * Get a data needed to be encrypted / decrypted (encryption and decryption in that cipher are same).
     * Return the data after encryption / decryption.
     * @param data The data needed to be encrypted / decrypted.
     * @return The encrypted / decrypted data.
     */
    private String encDec(String data) {
        StringBuilder encDecData = new StringBuilder();
        char endDecChar;

        for (char ch : data.toCharArray()) {
            endDecChar = (char) (ch ^ key());
            encDecData.append(endDecChar);
        }

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
    public String toString() {
        return """
                
                %s
                %s%b
                %s%d
                %s%s
                %s%s
                """.formatted(Constants.XOR_NAME,
                Constants.ENC_NAME, encryptionFlag(),
                Constants.KEY_MESSAGE, key(),
                Constants.INPUT_FILE, sourceFilePath(),
                Constants.OUTPUT_FILE, destFilePath());
    }
}
