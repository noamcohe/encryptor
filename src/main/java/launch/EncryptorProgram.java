package launch;
import userInput.ConsoleInput;
import menus.MenuChoice;
import menus.StartMenu;
import utils.programLogger;

public class EncryptorProgram {
    public static void execute() {
        final String START_MENU = """
                Welcome to encryptor program!
                Please enter the option number you would like to perform:
                """;
        final String END_PROGRAM = "The program was ended.";


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
