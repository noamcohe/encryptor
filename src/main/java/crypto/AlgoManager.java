package crypto;
import com.google.inject.Inject;
import crypto.algorithms.Algorithm;
import utils.FileUtils;
import utils.programLogger;
import java.nio.file.Path;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AlgoManager {
    final String ENC_START = "Encryption process is started.";
    final String DEC_START = "Decryption process is started.";
    final String END_ENC_MESSAGE = "Encryption process is over.";
    final String END_DEC_MESSAGE = "Decryption process is over.";
    final String ELAPSED_TIME = "Elapsed time: ";
    final String MILLISECONDS = " milliseconds";


    private final FileUtils fileUtils;
    private final CryptoExecutor cryptoExecutor;

    @Inject
    public AlgoManager(FileUtils fileUtils, CryptoExecutor cryptoExecutor) {
        this.fileUtils = fileUtils;
        this.cryptoExecutor = cryptoExecutor;
    }


    private void file(Algorithm algo, Details details) {
        Clock clock = Clock.systemDefaultZone();
        Instant start = clock.instant();

        if (details.encFlag()) {
            programLogger.info(ENC_START);
            cryptoExecutor.encryption(algo, details.sourcePath(), details.destPath());
            fileUtils.serializeWriting(algo, details.keysFilePath());
            programLogger.info(END_ENC_MESSAGE);

        } else {
            programLogger.info(DEC_START);
            cryptoExecutor.decryption(algo, details.sourcePath(), details.destPath());
            programLogger.info(END_DEC_MESSAGE);
        }

        Instant end = clock.instant();
        Duration duration = Duration.between(start, end);
        programLogger.info(ELAPSED_TIME + duration.toMillis() + MILLISECONDS);
    }


    private void directory(Algorithm algo, Details details) {
        fileUtils.createDirectory(details.destPath());
        final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();

        List<Path> filePaths = fileUtils.getFilePaths(details.sourcePath());

        if (details.encFlag()) {
            fileUtils.serializeWriting(algo, details.keysFilePath());
        } else {
            fileUtils.serializeReading(details.keysFilePath());
        }

        int filesPerThread = filePaths.size() / THREAD_COUNT;

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

        int startIndex = 0;
        int endIndex = filesPerThread;

        for (int i = 0; i < THREAD_COUNT; i++) {
            if (i == THREAD_COUNT - 1) {
                endIndex = filePaths.size();
            }

            List<Path> fileSubset = filePaths.subList(startIndex, endIndex);

            executorService.execute(new DirectoryCrypto(fileUtils, cryptoExecutor, fileSubset, details.destPath(), details.encFlag(), algo));

            startIndex = endIndex;
            endIndex += filesPerThread;
        }

        executorService.shutdown();
    }


    public void execute(Algorithm algo, Details details) {
        if (details.isFile()) {
            file(algo, details);
        } else {
            directory(algo, details);
        }
    }
}