package DataManagement;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Crypto {
    public File encryptFile(File fileToEncrypt) {
        try {
            System.out.println(Files.readString(fileToEncrypt.toPath()));
        } catch (IOException e) {
            System.out.println("Cannot read this file.");
        }

        return new File(fileToEncrypt.getAbsolutePath());
    }

    public File decryptFile(File fileToDecrypt) {
        try {
            System.out.println(Files.readString(fileToDecrypt.toPath()));
        } catch (IOException e) {
            System.out.println("Cannot read this file.");
        }

        return new File(fileToDecrypt.getAbsolutePath());
    }
}
