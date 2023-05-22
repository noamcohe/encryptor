package launch;
import consoleUI.Menu;
import consoleUI.SafeInput;
import consoleUI.Utils;
import enums.Choice;

public class Main {
    public static void main(String[] args) {
        while (true) {
            Menu.startMenu();
            Choice
                    .values()[SafeInput.integerInput(Utils.createRangePattern(Choice.values().length)) - 1]
                    .performAction();
        }
    }

}

