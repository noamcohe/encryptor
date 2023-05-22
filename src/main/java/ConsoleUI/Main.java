package ConsoleUI;
import DataManagement.Crypto;
import Enums.Choice;

public class Main {
    public static void main(String[] args) {
        Menu.startMenu();

        Choice startSelection = Choice.fromInteger(SafeInput.integerInput());
        while (startSelection == null) {
            System.out.println("You must choose an option from those numbers! Please try again:");
            startSelection = Choice.fromInteger(SafeInput.integerInput());
        }

        startSelection.performAction();

        SafeInput.scanner.close();
    }

}

