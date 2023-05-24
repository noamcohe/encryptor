package consoleUI;
import enums.Choice;

public class CastingValidation {
    public Choice fromIntToChoice(int num) {
        int numericChoice = num;
        Input inputOptions = new Input();

        while (!Choice.inRange(numericChoice)) {
            System.out.println(HelpConstants.INVALID_RANGE);
            numericChoice = inputOptions.integerInput();
        }

        return Choice.values()[numericChoice - 1];
    }
}
