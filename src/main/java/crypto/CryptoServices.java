package crypto;
import crypto.algorithms.Algorithm;
import utils.FileUtils;

import java.nio.file.Path;
import static utils.Constants.*;

public class CryptoServices {
    private final Path sourceFilePath;
    private final Path destFilePath;

    public CryptoServices(Path sourceFilePath, Path destFilePath) {
        this.sourceFilePath = sourceFilePath;
        this.destFilePath = destFilePath;
    }


    private void fileExecute(Algorithm algorithm, boolean encryptionFlag) {
        FileUtils fileUtils = new FileUtils();

        byte[] srcFileData = fileUtils.read(sourceFilePath);

        byte[] destFileData = encryptionFlag ?
                algorithm.encrypt(srcFileData) :
                algorithm.decrypt(srcFileData);

        fileUtils.write(destFilePath, destFileData);
    }


    public void fileEncryption(Algorithm algorithm) {
        fileExecute(algorithm, ENC_FLAG);
    }

    public void  fileDecryption(Algorithm algorithm) {
        fileExecute(algorithm, DEC_FLAG);
    }
}
