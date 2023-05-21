package DalObject;
import DAL.DecryptedFile;
import DAL.EncryptedFile;
import DAL.FileObject;

public class DalObject {

    /**
     * Add a new FileObject to the list
     * @param file new fileObject that the user opened.
     */
    public void createFile(FileObject file) {
        if (!DataSource.filesList.contains(file)) {
            DataSource.filesList.add(file);
        }
    }

    /**
     * Add new encryptedFile to the list.
     * @param file the encrypted file.
     */
    public void createEncryptedFile(EncryptedFile file) {
        if (!DataSource.encryptedFilesList.contains(file)) {
            DataSource.encryptedFilesList.add(file);
        }
    }

    /**
     * Add new decryptedFile to the list.
     * @param file the decrypted file.
     */
    public void createDecryptedFile(DecryptedFile file) {
        if (!DataSource.decryptedFilesList.contains(file)) {
            DataSource.decryptedFilesList.add(file);
        }
    }
}
