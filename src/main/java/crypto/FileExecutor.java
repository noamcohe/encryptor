package crypto;
import crypto.algorithms.Algorithm;
import utils.FileUtils;
import java.nio.file.Path;
import static utils.Constants.*;

public class FileExecutor implements CryptoExecutor {
    private final Path sourceFilePath;
    private final Path destFilePath;

    public FileExecutor(Path sourceFilePath, Path destFilePath) {
        this.sourceFilePath = sourceFilePath;
        this.destFilePath = destFilePath;
    }


    private void execute(Algorithm algo, boolean encFlag) {
        FileUtils fileUtils = new FileUtils();

        byte[] srcFileData = fileUtils.read(sourceFilePath);

        byte[] destFileData = encFlag ?
                algo.encrypt(srcFileData) :
                algo.decrypt(srcFileData);

        fileUtils.write(destFilePath, destFileData);
    }


    @Override
    public void encryption(Algorithm algo) {
        execute(algo, ENC_FLAG);
    }

    @Override
    public void decryption(Algorithm algo) {execute(algo, DEC_FLAG);}
}
