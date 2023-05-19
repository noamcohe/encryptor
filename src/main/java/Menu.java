import Enums.Choice;

public class Menu {

    /**
     * This function take the user choice and check if it's valid choice
     * @return the user choice, converted to Enum type called 'Choice'
     */
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

    /**
     * This function show to the user the first menu in the program, and input his choice
     * @return the user choice
     */
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
