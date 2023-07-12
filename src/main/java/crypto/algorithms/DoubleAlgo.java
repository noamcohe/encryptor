package crypto.algorithms;
import com.google.inject.Inject;

public record DoubleAlgo(@Inject Algorithm firstInner, @Inject Algorithm secondInner) implements Algorithm {
    @Override
    public byte[] encrypt(byte[] data) {return (secondInner.encrypt(firstInner.encrypt(data)));}

    @Override
    public byte[] decrypt(byte[] data) {return (firstInner.decrypt(secondInner.decrypt(data)));}

    @Override
    public String key() {return "%s|%s".formatted(firstInner.key(), secondInner.key());}
}
