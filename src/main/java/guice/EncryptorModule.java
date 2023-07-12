package guice;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import crypto.CryptoExecutor;
import crypto.FileExecutor;
import menus.MenuChoice;
import menus.StartMenu;
import userInput.ConsoleInput;
import userInput.GeneralInput;
import java.util.function.Function;

public class EncryptorModule extends AbstractModule {
    @Override
    public void configure() {
        bind(GeneralInput.class).to(ConsoleInput.class).in(Singleton.class);
        bind(new TypeLiteral<MenuChoice<Function<Void, Boolean>>>() {}).to(StartMenu.class);
        bind(CryptoExecutor.class).to(FileExecutor.class).in(Singleton.class);
    }

    @Provides
    public Byte provideByteParam(byte byteParam) {
        return byteParam;
    }
}
