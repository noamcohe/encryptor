package dataManagement;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import java.util.Random;

@Getter @Setter
public class CaesarCipher {
    private int key;
    private final int LETTERS_RANGE = 128;


    /**
     * Constructor for encryption case.
     * Randomise for the encryption a new key.
     */
    public CaesarCipher() {
        this.key = (new Random()).nextInt(LETTERS_RANGE);
    }


    /**
     * Get a string, and returns that string after encryption.
     * @param data The string we need to encrypt.
     * @param key The required key for encryption.
     * @return A new string that contains 'data' param after encryption.
     */
    public String stringEncryption(@NotNull String data, int key) {
        StringBuilder encryptedData = new StringBuilder();
        char encryptedCharacter;

        for (char ch : data.toCharArray()) {
            encryptedCharacter = (char) ((ch + key) % LETTERS_RANGE);
            encryptedData.append(encryptedCharacter);
        }

        return encryptedData.toString();
    }


    /**
     * Get a string, and returns that string after decryption.
     * @param data The string we need to decrypt.
     * @param key The required key for decryption.
     * @return A new string that contains 'data' param after decryption.
     */
    public String stringDecryption(@NotNull String data, int key) {
        StringBuilder decryptedData = new StringBuilder();
        char decryptedCharacter;

        for (char ch : data.toCharArray()) {
            decryptedCharacter = (char) ((ch - key + LETTERS_RANGE) % LETTERS_RANGE);
            decryptedData.append(decryptedCharacter);
        }

        return decryptedData.toString();
    }
}
