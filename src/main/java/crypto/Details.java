package crypto;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import userInput.GeneralInput;
import utils.FileUtils;
import java.nio.file.Files;
import java.nio.file.Path;
import static utils.Constants.*;

@Singleton
public class Details {
    final String GET_PATH_FOR_ENC = "Please enter the path of the file / directory you want to encrypt:";
    final String GET_PATH_FOR_DEC = "Please enter the path of the file / directory you want to decrypt:";
    final String GET_KEY_FILE = "Please enter the key file path:";
    final String ENC_FILE_EXTENSION = ".encrypted";
    final String DEC_FILE_EXTENSION = "_decrypted.txt";
    final String KEY_FILE_NAME = "key.bin";
    final Path ENC_DIRECTORY_PATH = Path.of("encrypted");
    final Path DEC_DIRECTORY_PATH = Path.of("decrypted");
    final boolean IS_NOT_KEYS_FILE = false;
    final boolean IS_KEYS_FILE = true;

    private Path sourcePath;
    private Path destPath;
    private String keysFilePath;
    private boolean isFile;
    private boolean encFlag;


    private final GeneralInput generalInput;
    private final FileUtils fileUtils;

    @Inject
    public Details(GeneralInput generalInput, FileUtils fileUtils) {
        this.generalInput = generalInput;
        this.fileUtils = fileUtils;
    }


    private void setupEnc() {
        this.encFlag = ENC_FLAG;
        this.sourcePath = generalInput.getPath(GET_PATH_FOR_ENC, IS_NOT_KEYS_FILE);
        this.isFile = Files.isRegularFile(sourcePath);

        if (isFile) {
            this.destPath = fileUtils.changeSuffix(sourcePath.toString(), ENC_FILE_EXTENSION);
            this.keysFilePath = sourcePath.getParent() + "\\" + KEY_FILE_NAME;
        } else {
            this.destPath = sourcePath.resolve(ENC_DIRECTORY_PATH);
            this.keysFilePath = sourcePath + "\\" + KEY_FILE_NAME;
        }
    }


    private void setupDec() {
        this.encFlag = DEC_FLAG;
        this.sourcePath = generalInput.getPath(GET_PATH_FOR_DEC, IS_NOT_KEYS_FILE);
        this.isFile = Files.isRegularFile(sourcePath);
        this.keysFilePath = generalInput.getPath(GET_KEY_FILE, IS_KEYS_FILE).toString();

        if (isFile) {
            this.destPath = fileUtils.changeSuffix(sourcePath.toString(), DEC_FILE_EXTENSION);
        } else {
            this.destPath = sourcePath.resolve(DEC_DIRECTORY_PATH);
        }
    }


    public void setup(boolean encFlag) {
        if (encFlag) {
            setupEnc();
        } else {
            setupDec();
        }
    }


    public Path sourcePath() {
        return sourcePath;
    }
    public Path destPath() {
        return destPath;
    }
    public String keysFilePath() {
        return keysFilePath;
    }
    public boolean isFile() {
        return isFile;
    }
    public boolean encFlag() {return encFlag;}
}
