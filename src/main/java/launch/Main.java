package launch;
import menus.ConsoleMenus;
import consoleUI.ConsoleInput;
import consoleUI.GeneralInput;
import enums.Choice;

public class Main {
    public static void main(String[] args) {
        GeneralInput consoleInput = new ConsoleInput();
        Choice userChoice;
        boolean shouldExit = false;


        while (!shouldExit) {
            ConsoleMenus.printStartMenu();
            userChoice = consoleInput.getChoice();

            shouldExit = userChoice.performAction(consoleInput);
        }

        ConsoleInput.scanner.close();
    }

}

