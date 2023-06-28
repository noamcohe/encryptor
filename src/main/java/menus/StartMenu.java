package menus;
import crypto.algorithms.Algorithm;
import crypto.algorithms.Cipher;
import crypto.algorithms.DoubleAlgo;
import crypto.algorithms.ReverseAlgo;
import crypto.AlgoManager;
import userInput.*;
import java.util.HashMap;
import java.util.Map;
import static utils.Constants.*;


public class StartMenu implements MenuChoice<Boolean> {
    final static String ENC_OPT_DESC = "(1) -- Encryption";
    final static String DEC_OPT_DESC = "(2) -- Decryption";
    final static String CLOSE_OPT_DESC = "(3) -- Close program";
    final static int ENC_OPT = 1;
    final static int DEC_OPT = 2;
    final static int CLOSE_OPT = 3;
    final static int XOR_KEY_INDEX = 0;
    final static int CAESAR_KEY_INDEX = 1;
    final static int MULTI_KEY_INDEX = 2;
    final static boolean EXIT_PROGRAM = true;
    final static boolean NOT_EXIT = false;


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
                initAlgo();
                return NOT_EXIT;
            }
        });

        menuChoices.put(DEC_OPT, new StartMenu(DEC_OPT_DESC) {
            @Override
            public Boolean performAction() {
                algoManager = new AlgoManager(DEC_FLAG);
                initAlgo();
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

    public static void initAlgo() {
        // Double algorithm of: (Xor) && (Reverse of (Double of (Caesar && Multiplicative)))

        // Caesar:
        Cipher caesarCipher = new Cipher(algoManager.getKey(CAESAR_NAME, CAESAR_KEY_INDEX), CAESAR_NAME);

        // Multiplicative:
        Cipher multiCipher = new Cipher(algoManager.getKey(MULTI_NAME, MULTI_KEY_INDEX), MULTI_NAME);

        // Double Algorithm:
        Algorithm secDoubleAlgo = new DoubleAlgo(caesarCipher, multiCipher);

        // Reverse Algorithm:
        Algorithm reverseAlgo = new ReverseAlgo(secDoubleAlgo);

        // Xor:
        Cipher xorCipher = new Cipher(algoManager.getKey(XOR_NAME, XOR_KEY_INDEX), XOR_NAME);


        // Double Algorithm:
        Algorithm doubleAlgo = new DoubleAlgo(xorCipher, reverseAlgo);


        algoManager.execute(doubleAlgo);
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
