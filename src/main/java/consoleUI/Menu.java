package consoleUI;
import enums.Choice;

public class Menu {

    /**
     * This function show to the user the first menu in the program, and input his choice
     * @return the user choice
     */
    public static void startMenu() {
        System.out.println(Utils.START_MENU_MESSAGE);

        Choice[] choiceArray = Choice.values();
        for (int i = 0; i < choiceArray.length; i++) {
            System.out.println("(" + (i + 1) + ") -- " + choiceArray[i]);
        }
    }
}
