package DAL;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileObject extends File {

    public FileObject(String pathname) {
        super(pathname);
    }


    public EncryptedFile encryptFile() {
        try {
            System.out.println(Files.readString(this.toPath()));
        } catch (IOException e) {
            System.out.println("Cannot read this file.");
        }

        return new EncryptedFile(this.getAbsolutePath());
    }

    public DecryptedFile decryptFile() {
        try {
            System.out.println(Files.readString(this.toPath()));
        } catch (IOException e) {
            System.out.println("Cannot read this file.");
        }

        return new DecryptedFile(this.getAbsolutePath());
    }
}
