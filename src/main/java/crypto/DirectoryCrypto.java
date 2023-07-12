package crypto;
import com.google.inject.Inject;
import crypto.algorithms.Algorithm;
import utils.FileUtils;
import java.nio.file.Path;
import java.util.List;

public class DirectoryCrypto implements Runnable {
    private final FileUtils fileUtils;
    private final CryptoExecutor cryptoExecutor;

    private final List<Path> sourceFilesList;
    private final Path destPath;
    private final boolean encFlag;
    private final Algorithm algo;

    @Inject
    public DirectoryCrypto(FileUtils fileUtils, CryptoExecutor cryptoExecutor, List<Path> sourceFilesList, Path destPath, boolean encFlag, Algorithm algo) {
        this.fileUtils = fileUtils;
        this.cryptoExecutor = cryptoExecutor;
        this.sourceFilesList = sourceFilesList;
        this.destPath = destPath;
        this.encFlag = encFlag;
        this.algo = algo;
    }

    @Override
    public void run() {
        List<Path> destFilesList = fileUtils.updateFilePaths(sourceFilesList, destPath);

        for (int i = 0; i < sourceFilesList.size(); i++) {
            if (encFlag) {
                cryptoExecutor.encryption(algo, sourceFilesList.get(i), destFilesList.get(i));
            } else {
                cryptoExecutor.decryption(algo, sourceFilesList.get(i), destFilesList.get(i));
            }
        }
    }
}
