import Enums.Choice;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Choice startSelection;
        boolean flag = true;

        while (flag) {

            startSelection = Menu.startMenu();

            switch (startSelection) {
                case ENCRYPTION -> {
                    String filePath = SafeInput.inputFilePath();
                    File fileToEncrypt = Files.createFile(filePath);
                    while (fileToEncrypt == null) {
                        filePath = SafeInput.inputFilePath();
                        fileToEncrypt = Files.createFile(filePath);
                    }
                    Encrypt.encryptFile(fileToEncrypt);
                }
                case DECRYPTION -> {
                    String filePath = SafeInput.inputFilePath();
                    File fileToDecrypt = Files.createFile(filePath);
                    while (fileToDecrypt == null) {
                        filePath = SafeInput.inputFilePath();
                        fileToDecrypt = Files.createFile(filePath);
                    }
                    Decrypt.decryptFile(fileToDecrypt);
                }
                case CLOSE_PROGRAM -> flag = false;
            }
        }
    }

}

