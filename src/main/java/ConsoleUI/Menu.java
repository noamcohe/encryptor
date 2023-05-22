package ConsoleUI;
import Enums.Choice;

public class Menu {

    /**
     * This function show to the user the first menu in the program, and input his choice
     * @return the user choice
     */
    public static void startMenu() {
        System.out.println("""
                Welcome to encryptor program!
                Please enter the option number you would like to perform:
                """);

        Choice[] choiceArray = Choice.values();
        for (int i = 0; i < choiceArray.length; i++) {
            System.out.println("(" + (i + 1) + ") -- " + choiceArray[i]);
        }
    }
}
