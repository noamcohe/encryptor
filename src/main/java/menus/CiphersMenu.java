package menus;
import consoleUI.ConsoleInput;
import consoleUI.GeneralInput;
import crypto.CryptoServices;
import crypto.cipherAlgorithms.*;
import utils.Constants;
import utils.FileUtils;

import java.util.HashMap;
import java.util.Map;


public class CiphersMenu implements MenuChoice<Void> {
    private static final Map<Integer, CiphersMenu> menuChoices = new HashMap<>();
    private static final GeneralInput consoleInput = new ConsoleInput();
    private static boolean encryptionFlag;

    static {
        menuChoices.put(1, new CiphersMenu(Constants.CAESAR_DESC) {
            @Override
            public Void performAction() {
                performCipher(CipherFactory.create(Constants.CAESAR_NAME));
                return null;
            }

            @Override
            public String toString() {
                return Constants.CAESAR_NAME;
            }
        });

        menuChoices.put(2, new CiphersMenu(Constants.XOR_DESC) {
            @Override
            public Void performAction() {
                performCipher(CipherFactory.create(Constants.XOR_NAME));
                return null;
            }

            @Override
            public String toString() {
                return Constants.XOR_NAME;
            }
        });

        menuChoices.put(3, new CiphersMenu(Constants.MULTIPLICATION_DESC) {
            @Override
            public Void performAction() {
                performCipher(CipherFactory.create(Constants.MULTIPLICATION_NAME));
                return null;
            }

            @Override
            public String toString() {
                return Constants.MULTIPLICATION_NAME;
            }
        });

        menuChoices.put(4, new CiphersMenu(Constants.REVERSE_DESC) {
            @Override
            public Void performAction() {
                performCipher(CipherFactory.create(Constants.REVERSE_NAME));
                return null;
            }

            @Override
            public String toString() {
                return Constants.REVERSE_NAME;
            }
        });
    }

    private static void performCipher(Cipher cipher) {
        cipher.encryptionFlag(encryptionFlag);
        CryptoServices cryptoServices = new CryptoServices();
        FileUtils fileUtils = new FileUtils();
        String cipherDetails;

        if (encryptionFlag) {
            cipher.sourceFilePath(consoleInput.getPath(Constants.TAKE_PATH_FOR_ENCRYPTION));
            cipher.key(cipher.getRandomKey());
            cipher.destFilePath(fileUtils.getNewPath(cipher.sourceFilePath().toString(), Constants.ENCRYPTED_FILE));

            System.out.println(Constants.START_ENC);
            cipherDetails = cryptoServices.fileEncryption(cipher);

            if (!cipher.isSucceeded()) {
                System.out.println(Constants.WRITING_ERROR);
            } else {
                System.out.println(Constants.END_ENC);
            }

        } else {
            cipher.sourceFilePath(consoleInput.getPath(Constants.TAKE_PATH_FOR_DECRYPTION));
            cipher.key(consoleInput.getKey());
            cipher.destFilePath(fileUtils.getNewPath(cipher.sourceFilePath().toString(), Constants.DECRYPTED_FILE));

            System.out.println(Constants.START_DEC);
            cipherDetails = cryptoServices.fileDecryption(cipher);

            if (!cipher.isSucceeded()) {
                System.out.println(Constants.WRITING_ERROR);
            } else {
                System.out.println(Constants.END_DEC);
            }
        }

        if (cipher.isSucceeded()) {
            System.out.println(cipherDetails);
        }
    }

    public static void encryptFlag(boolean flag) {
        encryptionFlag = flag;
    }

    public static CiphersMenu getChoice() {
        int cipherIndex = consoleInput.getNumByRange(menuChoices.size());
        return menuChoices.get(cipherIndex);
    }

    public String description;

    public CiphersMenu() {}

    public CiphersMenu(String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }

    @Override
    public void display(String message) {
        System.out.println(message);

        for (CiphersMenu option : menuChoices.values()) {
            System.out.println(option.description());
        }
    }

    @Override
    public Void performAction() {return null;}
}

