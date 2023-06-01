package crypto.cipherAlgorithms;
import utils.Constants;


public class CaesarCipher extends Cipher {
    @Override
    public String encrypt(String data) {
        StringBuilder encryptedData = new StringBuilder();
        char encryptedCharacter;

        for (char ch : data.toCharArray()) {
            encryptedCharacter = (char) ((ch + key()) % Constants.CHARACTERS_RANGE);
            encryptedData.append(encryptedCharacter);
        }

        return encryptedData.toString();
    }

    @Override
    public String decrypt(String data) {
        StringBuilder decryptedData = new StringBuilder();
        char decryptedCharacter;

        for (char ch : data.toCharArray()) {
            decryptedCharacter = (char) ((ch - key() + Constants.CHARACTERS_RANGE) % Constants.CHARACTERS_RANGE);
            decryptedData.append(decryptedCharacter);
        }

        return decryptedData.toString();
    }


    @Override
    public String toString() {
        return """
                
                %s
                %s%b
                %s%d
                %s%s
                %s%s
                %s%b
                
                """.formatted(Constants.CAESAR_NAME,
                Constants.ENC_NAME, encryptionFlag(),
                Constants.KEY_MESSAGE, key(),
                Constants.INPUT_FILE, sourceFilePath(),
                Constants.OUTPUT_FILE, destFilePath(),
                Constants.IS_SUCCEEDED, isSucceeded());
    }
}
