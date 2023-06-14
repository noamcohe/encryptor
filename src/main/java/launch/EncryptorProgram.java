package launch;
import userInput.ConsoleInput;
import menus.MenuChoice;
import menus.StartMenu;
import utils.programLogger;
import static utils.Constants.*;

public class EncryptorProgram {
    public static void execute() {
        Object shouldExit = false;
        MenuChoice<Boolean> startMenu = new StartMenu();


        while (shouldExit.equals(false)) {
            startMenu.display(START_MENU);
            shouldExit = StartMenu.getChoice().performAction();
        }

        programLogger.info(END_PROGRAM);
        ConsoleInput.scanner.close();
    }
}
