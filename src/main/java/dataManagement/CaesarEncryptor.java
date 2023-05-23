package dataManagement;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

@Getter @Setter
public class CaesarEncryptor {
    private int key;
    private final int LETTERS_RANGE = 26;


    /**
     * Constructor for encryption case.
     * Randomise for the encryption a new key.
     */
    public CaesarEncryptor() {
        this.key = (new Random()).nextInt(LETTERS_RANGE);
    }


    /**
     * Constructor for decryption case.
     * @param key The key we need to decrypt the file.
     */
    public CaesarEncryptor(int key) {
        this.key = key;
    }


    /**
     * Encrypt / decrypt a given character with Caesar cipher algorithm.
     * @param charToChange The character needs to be processed.
     * @param key The required key for the encryption / decryption.
     * @param encryptFlag A flag mention if the user wants to encrypt or decrypt.
     * @return A new character after encryption / decryption.
     */
    private char processCharacter(char charToChange, int key, boolean encryptFlag) {
        if (Character.isLetter(charToChange)) {
            char base = Character.isUpperCase(charToChange) ? 'A' : 'a';
            int offset = encryptFlag ? key : -key;
            int processedChar = (charToChange - base + offset) % 26;
            if (processedChar < 0) {
                processedChar += 26;
            }
            return (char) (processedChar + base);
        }
        return charToChange;
    }


    /**
     * Get a string, and returns that string after encryption / decryption.
     * @param data The string we need to encrypt / decrypt.
     * @param key The required key for the encryption / decryption.
     * @param encryptFlag A flag mention if the user wants to encrypt or decrypt.
     * @return A new string that contains 'data' param after encryption / decryption.
     */
    public String processString(@NotNull String data, int key, boolean encryptFlag) {
        StringBuilder processedData = new StringBuilder();
        char processedChar;

        for (char ch : data.toCharArray()) {
            processedChar = processCharacter(ch, key, encryptFlag);
            processedData.append(processedChar);
        }

        return processedData.toString();
    }
}
