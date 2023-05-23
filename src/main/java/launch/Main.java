package launch;
import consoleUI.CastingValidation;
import consoleUI.Menu;
import consoleUI.Input;

public class Main {
    public static void main(String[] args) {
        Menu.startMenu();
        CastingValidation.fromIntToChoice(Input.integerInput()).performAction();
        Input.scanner.close();
    }

}

