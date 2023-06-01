package launch;
import consoleUI.ConsoleInput;
import menus.MenuChoice;
import menus.StartMenu;
import utils.Constants;

public class EncryptorProgram {
    public static void execute() {
        Object shouldExit = false;
        MenuChoice<Boolean> startMenu = new StartMenu();


        while (shouldExit.equals(false)) {
            startMenu.display(Constants.START_MENU);
            shouldExit = StartMenu.getChoice().performAction();
        }

        ConsoleInput.scanner.close();
    }
}
