package enums;

import consoleUI.SafeInput;
import dataManagement.Crypto;
import dataManagement.FileManagement;
import java.io.File;

public enum Choice {
    ENCRYPTION {
        @Override
        public void performAction() {
            File encryptedFile = data.encryptFile(FileManagement.openFileObject("encrypt"));
        }
    },
    DECRYPTION {
        @Override
        public void performAction() {
            File decryptedFile = data.decryptFile(FileManagement.openFileObject("decrypt"));
        }
    },
    CLOSE_PROGRAM {
        @Override
        public void performAction() {
            SafeInput.scanner.close();
            System.exit(0);
        }
    };

    static final Crypto data = new Crypto();

    public abstract void performAction();
}
