package dataManagement;
import lombok.Getter;
import lombok.Setter;
import java.util.Random;

@Getter @Setter
public class CaesarCipherEncryptor {
    private int key;
    private final int LETTERS_RANGE = 26;

    public CaesarCipherEncryptor() {
        this.key = (new Random()).nextInt(LETTERS_RANGE);;
    }

    private char encryptChar(char charToEncrypt, int key) {
        if (Character.isLetter(charToEncrypt)) {
            char base = Character.isUpperCase(charToEncrypt) ? 'A' : 'a';
            return (char) ((charToEncrypt - base + key) % 26 + base);
        }

        return charToEncrypt;
    }
}
