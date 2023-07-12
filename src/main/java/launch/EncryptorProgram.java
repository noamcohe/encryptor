package launch;
import com.google.inject.Injector;
import com.google.inject.Key;
import guice.EncryptorModule;
import menus.MenuChoice;
import com.google.inject.Guice;
import userInput.GeneralInput;
import utils.programLogger;
import java.util.function.Function;

public class EncryptorProgram {
    public void execute() {
        final String END_PROGRAM = "The program was ended.";

        boolean shouldExit = false;
        Injector injector = Guice.createInjector(new EncryptorModule());
        var startMenu = injector.getInstance(new Key<MenuChoice<Function<Void, Boolean>>>() {});

        while (!shouldExit) {
            startMenu.display();
            shouldExit = startMenu.getChoice().apply(null);
        }

        programLogger.info(END_PROGRAM);
        injector.getInstance(GeneralInput.class).close();
    }
}
