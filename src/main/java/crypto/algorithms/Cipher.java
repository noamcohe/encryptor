package crypto.algorithms;
import com.google.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import static utils.Constants.*;


public class Cipher implements Algorithm {
    private final byte key;
    private final String name;
    private final Map<String, BiFunction<Byte, Byte, Byte>> formula;

    @Inject
    public Cipher(byte key, String name) {
        this.key = key;
        this.name = name;
        this.formula = initFormula();
    }

    public String key() {
        return String.valueOf(this.key);
    }

    private byte[] execute(byte[] data, byte key, String formula) {
        for (int i = 0; i < data.length; i++) {
            data[i] = this.formula.get(formula).apply(data[i], key);
        }
        return data;
    }

    @Override
    public byte[] encrypt(byte[] data) {
        String formula = name;
        if (name.equals(CAESAR_NAME)) {
            formula = CAESAR_ENC;
        }

        return execute(data, key, formula);
    }

    @Override
    public byte[] decrypt(byte[] data) {
        String formula = name;
        byte decryptionKey = key;
        if (name.equals(MULTI_NAME)) {
            decryptionKey = calcDecryptionKey(key);
        } else if (name.equals(CAESAR_NAME)) {
            formula = CAESAR_DEC;
        }
        return execute(data, decryptionKey, formula);
    }


    private byte calcDecryptionKey(byte key) {
        key = (byte) (key % Byte.MAX_VALUE);
        for (byte i = 1; i < Byte.MAX_VALUE; i++) {
            if (((key * i) % Byte.MAX_VALUE) == 1) {
                return i;
            }
        }

        return -1;
    }


    private Map<String, BiFunction<Byte, Byte, Byte>> initFormula() {
        var formula = new HashMap<String, BiFunction<Byte, Byte, Byte>>();

        formula.put(CAESAR_ENC, (data, key) -> (byte) (data + key));
        formula.put(CAESAR_DEC, (data, key) -> (byte) (data - key));
        formula.put(XOR_NAME, (data, key) -> (byte) (data ^ key));
        formula.put(MULTI_NAME, (data, key) -> (byte) ((data * key) % Byte.MAX_VALUE));

        return formula;
    }
}
