package Enums;

import ConsoleUI.SafeInput;
import DataManagement.Crypto;
import DataManagement.FileManagement;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public enum Choice {
    ENCRYPTION(1) {
        @Override
        public void performAction() {
            File encryptedFile = data.encryptFile(FileManagement.openFileObject("encrypt"));
        }
    },
    DECRYPTION(2) {
        @Override
        public void performAction() {
            File decryptedFile = data.decryptFile(FileManagement.openFileObject("decrypt"));
        }
    },
    CLOSE_PROGRAM(3) {
        @Override
        public void performAction() {
            SafeInput.scanner.close();
            System.exit(0);
        }
    };

    private final int numericValue;
    static final Crypto data = new Crypto();

    private static final Map<Integer, Choice> intToEnum = new HashMap<Integer, Choice>();
    static {
        for (Choice choice : values()) {
            intToEnum.put(choice.numericValue(), choice);
        }
    }

    Choice(int i) {
        this.numericValue = i;
    }

    public int numericValue() {
        return numericValue;
    }

    public static Choice fromInteger(int numericValue) {
        return intToEnum.get(numericValue);
    }

    public abstract void performAction();
}
