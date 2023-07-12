package crypto.algorithms;
import com.google.inject.Inject;

public record ReverseAlgo(@Inject Algorithm inner) implements Algorithm {
    @Override
    public byte[] encrypt(byte[] data) {
        return inner.decrypt(data);
    }

    @Override
    public byte[] decrypt(byte[] data) {
        return inner.encrypt(data);
    }

    @Override
    public String key() {
        return inner.key();
    }
}
