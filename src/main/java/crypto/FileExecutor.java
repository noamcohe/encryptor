package crypto;
import com.google.inject.Singleton;
import crypto.algorithms.Algorithm;
import utils.FileUtils;
import java.nio.file.Path;
import static utils.Constants.*;

@Singleton
public class FileExecutor implements CryptoExecutor {
    private void execute(Algorithm algo, boolean encFlag, Path srcPath, Path destPath) {
        FileUtils fileUtils = new FileUtils();

        byte[] srcFileData = fileUtils.read(srcPath);

        byte[] destFileData = encFlag ?
                algo.encrypt(srcFileData) :
                algo.decrypt(srcFileData);

        fileUtils.write(destPath, destFileData);
    }


    @Override
    public void encryption(Algorithm algo, Path srcPath, Path destPath) {
        execute(algo, ENC_FLAG, srcPath, destPath);
    }

    @Override
    public void decryption(Algorithm algo, Path srcPath, Path destPath) {execute(algo, DEC_FLAG, srcPath, destPath);}
}
