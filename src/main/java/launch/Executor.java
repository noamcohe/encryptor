package launch;
import com.google.inject.Guice;
import guice.EncryptorModule;

public class Executor {
    public static void main(String[] args) {
        Guice.createInjector(new EncryptorModule()).getInstance(EncryptorProgram.class).execute();
    }
}