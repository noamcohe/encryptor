package crypto.algorithms;
import static utils.Constants.*;

public record ReverseAlgo(Algorithm innerAlgo) implements Algorithm {
    @Override
    public byte[] encrypt(byte[] data) {
        return innerAlgo().decrypt(data);
    }
    @Override
    public byte[] decrypt(byte[] data) {
        return innerAlgo().encrypt(data);
    }

    @Override
    public String key() {
        return innerAlgo.key();
    }

    @Override
    public String toString() {
        return """
                                
                %s
                Inner cipher:
                %s""".formatted(REVERSE_NAME,
                innerAlgo());
    }
}
