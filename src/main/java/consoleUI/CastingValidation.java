package consoleUI;

import enums.Choice;

public class CastingValidation {
    public static Choice fromIntToChoice(int num) {
        int numericChoice = num;

        while (!Choice.inRange(numericChoice)) {
            System.out.println(Utils.INVALID_RANGE);
            numericChoice = Input.numInput();
        }

        return Choice.values()[numericChoice - 1];
    }
}
