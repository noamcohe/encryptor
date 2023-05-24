package enums;
import consoleUI.GeneralInput;
import dataManagement.CryptoServices;
import java.time.temporal.ValueRange;

public enum Choice {
    ENCRYPTION {
        @Override
        public boolean performAction(GeneralInput consoleInput) {
            cryptoServices.fileEncryption(consoleInput.getPath());
            return false;
        }
    },
    DECRYPTION {
        @Override
        public boolean performAction(GeneralInput consoleInput) {
            cryptoServices.fileDecryption(consoleInput.getPath());
            return false;
        }
    },
    CLOSE_PROGRAM {
        @Override
        public boolean performAction(GeneralInput consoleInput) {
            return true;
        }
    };

    static final CryptoServices cryptoServices = new CryptoServices();


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
     * @return A flag mention if the CLOSE_PROGRAM option was chosen or not.
     */
    public abstract boolean performAction(GeneralInput consoleInput);
}
