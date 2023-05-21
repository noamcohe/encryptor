package DalObject;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import DAL.EncryptedFile;
import DAL.DecryptedFile;

public class DataSource {
    protected static List<File> filesList = new ArrayList<File>();
    protected static List<EncryptedFile> encryptedFilesList = new ArrayList<EncryptedFile>();
    protected static List<DecryptedFile> decryptedFilesList = new ArrayList<DecryptedFile>();
}
