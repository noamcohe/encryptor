package crypto.algorithms;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import static utils.Constants.*;
import static utils.Constants.MULTI_NAME;

public class Cipher implements Algorithm {
    private final byte key;
    private final String cipherName;

    private Map<String, BiFunction<Byte, Byte, Byte>> cipherFormula = new HashMap<>();
    private final BiFunction<Byte, Byte, Byte> CAESAR_ENCRYPTION = (data, key) -> (byte) (data + key);
    private final BiFunction<Byte, Byte, Byte> CAESAR_DECRYPTION = (data, key) -> (byte) (data - key);
    private final BiFunction<Byte, Byte, Byte> XOR = (data, key) -> (byte) (data ^ key);
    private final BiFunction<Byte, Byte, Byte> MULTI = (data, key) -> (byte) ((data * key) % Byte.MAX_VALUE);

    public Cipher(byte key, String cipherName) {
        this.key = key;
        this.cipherName = cipherName;
        cipherFormula.put(CAESAR_ENC, CAESAR_ENCRYPTION);
        cipherFormula.put(CAESAR_DEC, CAESAR_DECRYPTION);
        cipherFormula.put(XOR_NAME, XOR);
        cipherFormula.put(MULTI_NAME, MULTI);
    }

    public String key() {
        return String.valueOf(this.key);
    }

    protected byte[] execute(byte[] data, byte key) {
        for (int i = 0; i < data.length; i++) {
            data[i] = cipherFormula.get(this.cipherName).apply(data[i], key);
        }
        return data;
    }

    @Override
    public byte[] encrypt(byte[] data) {
        return execute(data, this.key);
    }

    @Override
    public byte[] decrypt(byte[] data) {
        byte decryptionKey = key;
        if (this.cipherName.equals(MULTI_NAME)) {
            decryptionKey = calcDecryptionKey(key);
        }
        return execute(data, decryptionKey);
    }


    /**
     * Used for calculate the decryption key in Multiplicative.
     */
    private byte calcDecryptionKey(byte key) {
        key = (byte) (key % CHARACTERS_RANGE);
        for (byte i = 1; i < CHARACTERS_RANGE; i++) {
            if (((key * i) % CHARACTERS_RANGE) == 1) {
                return i;
            }
        }

        return -1;
    }
}
