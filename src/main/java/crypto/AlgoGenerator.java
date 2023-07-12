package crypto;
import com.google.inject.Inject;
import crypto.algorithms.Algorithm;
import crypto.algorithms.AlgoFactory;
import utils.FileUtils;
import java.util.Random;
import static utils.Constants.*;

public class AlgoGenerator {
    final static int XOR_KEY_INDEX = 0;
    final static int CAESAR_KEY_INDEX = 1;
    final static int MULTI_KEY_INDEX = 2;

    private final FileUtils fileUtils;
    private final AlgoFactory algoFactory;

    @Inject
    public AlgoGenerator(FileUtils fileUtils, AlgoFactory algoFactory) {
        this.fileUtils = fileUtils;
        this.algoFactory = algoFactory;
    }


    public Algorithm generate(Details details) {
        var xor = algoFactory.create(generateKey(XOR_NAME, XOR_KEY_INDEX, details), XOR_NAME);
        var caesar = algoFactory.create(generateKey(CAESAR_NAME, CAESAR_KEY_INDEX, details), CAESAR_NAME);
        var multi = algoFactory.create(generateKey(MULTI_NAME, MULTI_KEY_INDEX, details), MULTI_NAME);

        // Double of: (Xor) && (Reverse of (Double of (Caesar && Multiplicative)))
        return algoFactory.create(xor, algoFactory.create(algoFactory.create(caesar, multi)));
    }


    private byte generateKey(String cipherName, int index, Details details) {
        if(details.encFlag()) {
            return generateRandomKey(cipherName);
        } else {
            return fileUtils.serializeReading(details.keysFilePath())[index];
        }
    }


    private byte generateRandomKey(String cipherName) {
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


    private boolean isCoprime(int num) {
        return gcd(num, Byte.MAX_VALUE) == 1;
    }


    private int gcd(int firstNum, int secNum) {
        if (secNum == 0) {
            return firstNum;
        }
        return gcd(secNum, firstNum % secNum);
    }
}
