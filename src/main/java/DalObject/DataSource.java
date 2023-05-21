package DalObject;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import DAL.EncryptedFile;
import DAL.DecryptedFile;
import DAL.FileObject;

public class DataSource {
    protected static List<FileObject> filesList = new ArrayList<FileObject>();
    protected static List<EncryptedFile> encryptedFilesList = new ArrayList<EncryptedFile>();
    protected static List<DecryptedFile> decryptedFilesList = new ArrayList<DecryptedFile>();
}
