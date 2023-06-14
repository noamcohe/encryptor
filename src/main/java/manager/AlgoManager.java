package manager;
import userInput.*;
import crypto.CryptoServices;
import crypto.algorithms.*;
import utils.programLogger;
import utils.FileUtils;
import java.io.*;
import java.nio.file.Path;
import static utils.Constants.*;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class AlgoManager {
    private static final GeneralInput consoleInput = new ConsoleInput();
    private static final FileUtils fileUtils = new FileUtils();
    private final boolean encryptionFlag;
    private final Path sourceFilePath;
    private final Path destFilePath;
    private String keysFilePath = null;


    public AlgoManager(boolean encryptionFlag) {
        this.encryptionFlag = encryptionFlag;

        if (this.encryptionFlag) {
            this.sourceFilePath = consoleInput.getPath(TAKE_PATH_FOR_ENC);
            this.destFilePath = fileUtils.getNewPath(sourceFilePath.toString(), ENC_FILE_EXTENSION);
        } else {
            this.sourceFilePath = consoleInput.getPath(TAKE_PATH_FOR_DEC);
            this.keysFilePath = consoleInput.getPath(GET_KEY_FILE).toString();
            this.destFilePath = fileUtils.getNewPath(sourceFilePath.toString(), DEC_FILE_EXTENSION);
        }
    }


    public void performAlgorithm(Algorithm algo) {
        CryptoServices cryptoServices = new CryptoServices(sourceFilePath, destFilePath);

        Clock clock = Clock.systemDefaultZone();
        Instant start = clock.instant();

        if (encryptionFlag) {
            programLogger.info(ENC_START);
            cryptoServices.fileEncryption(algo);
            serializeAlgo(algo);
            programLogger.info(END_ENC_MESSAGE);

        } else {
            programLogger.info(DEC_START);
            cryptoServices.fileDecryption(algo);
            programLogger.info(END_DEC_MESSAGE);
        }

        Instant end = clock.instant();
        Duration duration = Duration.between(start, end);
        programLogger.info(ELAPSED_TIME + duration.toMillis() + MILLISECONDS);
    }

    public byte getKey(String cipherName, int index) {
        if(encryptionFlag) {
            return getRandomKey(cipherName);
        } else {
            return readKeysFile(keysFilePath)[index];
        }
    }


    public void serializeAlgo(Algorithm algo) {
        programLogger.info(START_WRITING_KEY);
        String destPath = sourceFilePath.getParent() + "\\" + KEY_FILE_NAME;

        try {
            FileOutputStream fileOut = new FileOutputStream(destPath);
            ObjectOutputStream keys = new ObjectOutputStream(fileOut);

            keys.writeObject(algo.key());

            keys.close();
            fileOut.close();
        } catch (IOException e) {
            programLogger.error(KEYS_WRITING_ERROR + e);
        }

        programLogger.info(END_WRITING_KEY);
    }


    public byte[] readKeysFile(String keyFilePath) {
        programLogger.info(START_READING_KEY);
        byte[] keysArray = new byte[0];

        try {
            FileInputStream fileIn = new FileInputStream(keyFilePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            String[] keysList = ((String) in.readObject()).split("\\|");

            keysArray = new byte[keysList.length];
            for (int i = 0; i < keysArray.length; i++) {
                keysArray[i] = Byte.parseByte(keysList[i]);
            }

            in.close();
            fileIn.close();

            programLogger.info(END_READING_KEY);

        } catch (IOException e) {
            programLogger.error(KEYS_READING_ERROR + e);
        } catch (ClassNotFoundException e) {
            programLogger.error(CLASS_NOT_FOUND + e);
        }

        return keysArray;
    }


    public byte getRandomKey(String cipherName) {
        byte key;

        if(cipherName.equals(MULTI_NAME)) {
            do {
                key = (byte)(new Random()).nextInt(1, CHARACTERS_RANGE);
            } while ((key % 2) == 0 || !isCoprime(key));
        }

        else {
            key = (byte)(new Random()).nextInt(1, CHARACTERS_RANGE);
        }

        return key;
    }

    /**
     * Used for Multiplicative generated random key.
     */
    private boolean isCoprime(int num) {
        return gcd(num, CHARACTERS_RANGE) == 1;
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
