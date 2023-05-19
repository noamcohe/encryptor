import Enums.Choice;

public class Menu {

    private static Choice choiceInput() {
        int choice = SafeInput.integerInput();

        while (!(choice >= 1 && choice <= 3)) {
            System.out.println("""
                You must choose an option from those numbers!
                Please try again:""");
            choice = SafeInput.integerInput();
        }

        return Choice.castToEnum(choice);
    }

    public static Choice startMenu() {
        System.out.println("""
                Welcome to encryptor program!
                Please enter the option number you would like to perform:

                (1) -- encryption
                (2) -- decryption
                (3) -- close program
                """);

        return choiceInput();
    }
}
