package enums;
import consoleUI.Input;
import consoleUI.HelpConstants;
import dataManagement.CryptoUtils;
import java.time.temporal.ValueRange;

public enum Choice {
    ENCRYPTION {
        @Override
        public void performAction(Input inputOptions) {
            cryptoOptions.processFile(inputOptions.pathInput(), HelpConstants.ENCRYPTION_FLAG);
        }
    },
    DECRYPTION {
        @Override
        public void performAction(Input inputOptions) {
            cryptoOptions.processFile(inputOptions.pathInput(), HelpConstants.DECRYPTION_FLAG);
        }
    },
    CLOSE_PROGRAM {
        @Override
        public void performAction(Input inputOptions) {
            Input.scanner.close();
            System.exit(0);
        }
    };

    static final CryptoUtils cryptoOptions = new CryptoUtils();


    /**
     * get a number and check if it's in the range.
     * @param num the user choice number.
     * @return true if it's in the range, false otherwise.
     */
    public static boolean inRange(int num) {
        return ValueRange.of(0, Choice.values().length).isValidIntValue(num);
    }


    /**
     * An abstract method that can do everything, depending on the user choice in the menu.
     */
    public abstract void performAction(Input inputOptions);
}
