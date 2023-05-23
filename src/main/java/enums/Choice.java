package enums;

import consoleUI.Input;
import consoleUI.Utils;
import dataManagement.CryptoUtilities;
import java.io.File;
import java.time.temporal.ValueRange;

public enum Choice {
    ENCRYPTION {
        @Override
        public void performAction() {
            File fileToEncrypt = new File(Input.pathInput().toString());
        }
    },
    DECRYPTION {
        @Override
        public void performAction() {
            File fileToDecrypt = new File(Input.pathInput().toString());
        }
    },
    CLOSE_PROGRAM {
        @Override
        public void performAction() {
            Input.scanner.close();
            System.exit(0);
        }
    };

    static final CryptoUtilities cryptoOptions = new CryptoUtilities();
    public static boolean inRange(int num) {
        return ValueRange.of(0, Choice.values().length).isValidIntValue(num);
    }
    public abstract void performAction();
}
