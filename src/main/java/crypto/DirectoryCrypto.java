package crypto;
import crypto.algorithms.Algorithm;
import utils.FileUtils;
import java.nio.file.Path;
import java.util.List;

public class DirectoryCrypto implements Runnable {
    private static final FileUtils fileUtils = new FileUtils();
    private final List<Path> srcFilePaths;
    private final Path dstDirect;
    private final boolean encFlag;
    private final Algorithm algo;

    public DirectoryCrypto(Algorithm algo, List<Path> srcFilePaths, Path dstDirect, boolean encFlag) {
        this.algo = algo;
        this.srcFilePaths = srcFilePaths;
        this.dstDirect = dstDirect;
        this.encFlag = encFlag;
    }

    @Override
    public void run() {
        List<Path> destFilePaths = fileUtils.updateFilePaths(srcFilePaths, dstDirect);

        for (int i = 0; i < srcFilePaths.size(); i++) {
            CryptoExecutor cryptoExecutor = new FileExecutor(srcFilePaths.get(i), destFilePaths.get(i));

            if (encFlag) {
                cryptoExecutor.encryption(algo);
            } else {
                cryptoExecutor.decryption(algo);
            }
        }

    }
}
