package launch;
import consoleUI.CastingValidation;
import consoleUI.Menu;
import consoleUI.Input;

public class Main {
    public static void main(String[] args) {
        CastingValidation castOptions = new CastingValidation();
        Input inputOptions = new Input();

        Menu.startMenu();
        castOptions.fromIntToChoice(inputOptions.integerInput()).performAction(inputOptions);
        Input.scanner.close();
    }

}

