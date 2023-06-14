package menus;
import crypto.algorithms.Algorithm;
import crypto.algorithms.Cipher;
import crypto.algorithms.DoubleAlgo;
import crypto.algorithms.ReverseAlgo;
import manager.AlgoManager;
import userInput.*;
import java.util.HashMap;
import java.util.Map;
import static utils.Constants.*;


public class StartMenu implements MenuChoice<Boolean> {
    public static final Map<Integer, StartMenu> menuChoices = new HashMap<>();
    private static final GeneralInput consoleInput = new ConsoleInput();
    private static AlgoManager algoManager;
    public String description;

    public StartMenu() {}
    public StartMenu(String description) {
        this.description = description;
    }
    public String description() {
        return description;
    }


    static {
        menuChoices.put(ENC_OPT, new StartMenu(ENC_OPT_DESC) {
            @Override
            public Boolean performAction() {
                algoManager = new AlgoManager(ENC_FLAG);
                callAlgorithm(ENC_FLAG);
                return NOT_EXIT;
            }
        });

        menuChoices.put(DEC_OPT, new StartMenu(DEC_OPT_DESC) {
            @Override
            public Boolean performAction() {
                algoManager = new AlgoManager(DEC_FLAG);
                callAlgorithm(DEC_FLAG);
                return NOT_EXIT;
            }
        });

        menuChoices.put(CLOSE_OPT, new StartMenu(CLOSE_OPT_DESC) {
            @Override
            public Boolean performAction() {
                return EXIT_PROGRAM;
            }
        });
    }

    public static StartMenu getChoice() {
        int choiceIndex = consoleInput.getNumByRange(menuChoices.size());
        return menuChoices.get(choiceIndex);
    }

    public static void callAlgorithm(boolean encryptionFlag) {
        // Double algorithm of: (Xor) && (Reverse of (Double of (Caesar && Multiplicative)))

        // Caesar:
        String caesarName = encryptionFlag ? CAESAR_ENC : CAESAR_DEC;
        Cipher caesarCipher = new Cipher(algoManager.getKey(caesarName, CAESAR_KEY_INDEX), caesarName);

        // Multiplicative:
        Cipher multiCipher = new Cipher(algoManager.getKey(MULTI_NAME, MULTI_KEY_INDEX), MULTI_NAME);

        // Double Algorithm:
        Algorithm secDoubleAlgo = new DoubleAlgo(caesarCipher, multiCipher);

        // Reverse Algorithm:
        Algorithm reverseAlgo = new ReverseAlgo(secDoubleAlgo);

        // Xor:
        Cipher xorCipher = new Cipher(algoManager.getKey(XOR_NAME, XOR_KEY_INDEX), XOR_NAME);


        // Double Algorithm:
        Algorithm doubleAlgorithm = new DoubleAlgo(xorCipher, reverseAlgo);


        algoManager.performAlgorithm(doubleAlgorithm);
    }

    @Override
    public Boolean performAction() {
        return false;
    }

    @Override
    public void display(String message) {
        System.out.println(message);

        for (StartMenu option : menuChoices.values()) {
            System.out.println(option.description());
        }
    }
}
