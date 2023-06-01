package crypto.cipherAlgorithms;
import menus.CiphersMenu;
import menus.MenuChoice;
import utils.Constants;

public class CipherFactory {

    public static Cipher create(String cipherType) {
        switch (cipherType) {
            case Constants.CAESAR_NAME -> {
                return new CaesarCipher();
            }
            case Constants.XOR_NAME -> {
                return new XorCipher();
            }
            case Constants.MULTIPLICATION_NAME -> {
                return new MultiplicationCipher();
            }
            case Constants.REVERSE_NAME -> {
                MenuChoice<Void> ciphersMenu = new CiphersMenu();
                ciphersMenu.display(Constants.CHOOSE_INNER_CIPHER);
                return new ReverseCipher(create(CiphersMenu.getChoice().toString()));
            }
        }

        return new CaesarCipher();
    }
}
