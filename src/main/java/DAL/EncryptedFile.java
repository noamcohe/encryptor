package DAL;

import java.io.File;

public class EncryptedFile extends File {
    private String encryptionType;

    public EncryptedFile(String pathname) {
        super(pathname);
    }

    public void encryptFile(File fileToEncrypt) {

    }
}
