package DAL;

import java.io.File;

public class DecryptedFile extends File {
    private String decryptedType;

    public DecryptedFile(String pathname) {
        super(pathname);
    }
}
