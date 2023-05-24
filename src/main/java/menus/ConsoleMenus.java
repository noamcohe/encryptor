package menus;
import enums.Choice;
import utils.Constants;

public class ConsoleMenus {

    /**
     * This function show to the user the first menu in the program, and input his choice
     */
    public static void printStartMenu() {
        System.out.println(Constants.START_MENU_MESSAGE);

        Choice[] choiceArray = Choice.values();
        for (int i = 0; i < choiceArray.length; i++) {
            System.out.println("(" + (i + 1) + ") -- " + choiceArray[i]);
        }
    }
}
