/**
 * @author: Noam Cohen, 18.5
 */


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
                    // While the path is not valid, or something get wrong:
                    while (fileToEncrypt == null) {
                        // Then go over that process:
                        filePath = SafeInput.inputFilePath();
                        fileToEncrypt = Files.createFile(filePath);
                    }

                    // After we checked everything we need, we can encrypt our file:
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

