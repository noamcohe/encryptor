package crypto;
import userInput.*;
import crypto.algorithms.*;
import utils.programLogger;
import utils.FileUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import static utils.Constants.*;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AlgoManager {
    final String TAKE_PATH_FOR_ENC = "Please enter the path of the file / directory you want to encrypt:";
    final String TAKE_PATH_FOR_DEC = "Please enter the path of the file / directory you want to decrypt:";
    final String ENC_FILE_EXTENSION = ".encrypted";
    final String DEC_FILE_EXTENSION = "_decrypted.txt";
    final String KEY_FILE_NAME = "key.bin";
    final Path ENC_DIRECTORY_PATH = Path.of("encrypted");
    final Path DEC_DIRECTORY_PATH = Path.of("decrypted");
    final String ENC_START = "Encryption process is started.";
    final String DEC_START = "Decryption process is started.";
    final String END_ENC_MESSAGE = "Encryption process is over.";
    final String END_DEC_MESSAGE = "Decryption process is over.";
    final String ELAPSED_TIME = "Elapsed time: ";
    final String MILLISECONDS = " milliseconds";
    final String GET_KEY_FILE = "Please enter the key file path:";
    final boolean IS_NOT_KEYS_FILE = false;
    final boolean IS_KEYS_FILE = true;


    private static final GeneralInput consoleInput = new ConsoleInput();
    private static final FileUtils fileUtils = new FileUtils();
    private final boolean encryptionFlag;
    private final boolean isFile;
    private final Path sourcePath;
    private final Path destPath;
    private final String keysFilePath;


    public AlgoManager(boolean encryptionFlag) {
        this.encryptionFlag = encryptionFlag;

        if (encryptionFlag) {
            sourcePath = consoleInput.getPath(TAKE_PATH_FOR_ENC, IS_NOT_KEYS_FILE);
            isFile = Files.isRegularFile(sourcePath);

            if (isFile) {
                destPath = fileUtils.changeSuffix(sourcePath.toString(), ENC_FILE_EXTENSION);
                keysFilePath = sourcePath.getParent() + "\\" + KEY_FILE_NAME;
            } else {
                destPath = sourcePath.resolve(ENC_DIRECTORY_PATH);
                keysFilePath = sourcePath + "\\" + KEY_FILE_NAME;
            }
        }

        else {
            sourcePath = consoleInput.getPath(TAKE_PATH_FOR_DEC, IS_NOT_KEYS_FILE);
            isFile = Files.isRegularFile(sourcePath);
            keysFilePath = consoleInput.getPath(GET_KEY_FILE, IS_KEYS_FILE).toString();

            if (isFile) {
                destPath = fileUtils.changeSuffix(sourcePath.toString(), DEC_FILE_EXTENSION);
            } else {
                destPath = sourcePath.resolve(DEC_DIRECTORY_PATH);
            }
        }
    }


    private void file(Algorithm algo) {
        CryptoExecutor cryptoExecutor = new FileExecutor(sourcePath, destPath);

        Clock clock = Clock.systemDefaultZone();
        Instant start = clock.instant();

        if (encryptionFlag) {
            programLogger.info(ENC_START);
            cryptoExecutor.encryption(algo);
            fileUtils.serializeWriting(algo, keysFilePath);
            programLogger.info(END_ENC_MESSAGE);

        } else {
            programLogger.info(DEC_START);
            cryptoExecutor.decryption(algo);
            programLogger.info(END_DEC_MESSAGE);
        }

        Instant end = clock.instant();
        Duration duration = Duration.between(start, end);
        programLogger.info(ELAPSED_TIME + duration.toMillis() + MILLISECONDS);
    }


    private void directory(Algorithm algo) {
        FileUtils fileUtils = new FileUtils();
        fileUtils.createDirectory(destPath);
        final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();

        List<Path> filePaths = fileUtils.getFilePaths(sourcePath);

        if (encryptionFlag) {
            AlgoManager.fileUtils.serializeWriting(algo, keysFilePath);
        } else {
            AlgoManager.fileUtils.serializeReading(keysFilePath);
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
            startIndex = endIndex;
            endIndex += filesPerThread;

            executorService.execute(new DirectoryCrypto(algo, fileSubset, destPath, encryptionFlag));
        }

        executorService.shutdown();
    }


    public void execute(Algorithm algo) {
        if (isFile) {
            file(algo);
        } else {
            directory(algo);
        }
    }


    public byte getKey(String cipherName, int index) {
        if(encryptionFlag) {
            return getRandomKey(cipherName);
        } else {
            return fileUtils.serializeReading(keysFilePath)[index];
        }
    }


    public byte getRandomKey(String cipherName) {
        byte key;

        if(cipherName.equals(MULTI_NAME)) {
            do {
                key = (byte)(new Random()).nextInt(1, Byte.MAX_VALUE);
            } while ((key % 2) == 0 || !isCoprime(key));
        }

        else {
            key = (byte)(new Random()).nextInt(1, Byte.MAX_VALUE);
        }

        return key;
    }

    /**
     * Used for Multiplicative generated random key.
     */
    private boolean isCoprime(int num) {
        return gcd(num, Byte.MAX_VALUE) == 1;
    }

    /**
     * Used for Multiplicative generated random key.
     */
    private int gcd(int firstNum, int secNum) {
        if (secNum == 0) {
            return firstNum;
        }
        return gcd(secNum, firstNum % secNum);
    }
}
