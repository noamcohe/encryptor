package crypto.algorithms;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import static utils.Constants.*;
import static utils.Constants.MULTI_NAME;

public class Cipher implements Algorithm {
    private final byte key;
    private final String cipherName;
    private final Map<String, BiFunction<Byte, Byte, Byte>> cipherFormula;

    public Cipher(byte key, String cipherName) {
        this.key = key;
        this.cipherName = cipherName;
        this.cipherFormula = initFormula();
    }


    private Map<String, BiFunction<Byte, Byte, Byte>> initFormula() {
        Map<String, BiFunction<Byte, Byte, Byte>> cipherFormula = new HashMap<>();

        BiFunction<Byte, Byte, Byte> CAESAR_ENCRYPTION = (data, key) -> (byte) (data + key);
        cipherFormula.put(CAESAR_ENC, CAESAR_ENCRYPTION);

        BiFunction<Byte, Byte, Byte> CAESAR_DECRYPTION = (data, key) -> (byte) (data - key);
        cipherFormula.put(CAESAR_DEC, CAESAR_DECRYPTION);

        BiFunction<Byte, Byte, Byte> XOR = (data, key) -> (byte) (data ^ key);
        cipherFormula.put(XOR_NAME, XOR);

        BiFunction<Byte, Byte, Byte> MULTI = (data, key) -> (byte) ((data * key) % Byte.MAX_VALUE);
        cipherFormula.put(MULTI_NAME, MULTI);

        return cipherFormula;
    }

    public String key() {
        return String.valueOf(this.key);
    }

    protected byte[] execute(byte[] data, byte key, String formula) {
        for (int i = 0; i < data.length; i++) {
            data[i] = cipherFormula.get(formula).apply(data[i], key);
        }
        return data;
    }

    @Override
    public byte[] encrypt(byte[] data) {
        String formula = cipherName;
        if (cipherName.equals(CAESAR_NAME)) {
            formula = CAESAR_ENC;
        }

        return execute(data, key, formula);
    }

    @Override
    public byte[] decrypt(byte[] data) {
        String formula = cipherName;
        byte decryptionKey = key;
        if (cipherName.equals(MULTI_NAME)) {
            decryptionKey = calcDecryptionKey(key);
        } else if (cipherName.equals(CAESAR_NAME)) {
            formula = CAESAR_DEC;
        }
        return execute(data, decryptionKey, formula);
    }


    /**
     * Used for calculate the decryption key in Multiplicative.
     */
    private byte calcDecryptionKey(byte key) {
        key = (byte) (key % Byte.MAX_VALUE);
        for (byte i = 1; i < Byte.MAX_VALUE; i++) {
            if (((key * i) % Byte.MAX_VALUE) == 1) {
                return i;
            }
        }

        return -1;
    }
}
