import java.util.Scanner;

public class SafeInput {

    static Scanner scanner = new Scanner(System.in);

    public static int integerInput() {
        String selection = scanner.next();

        while (!Validation.isNumeric(selection)) {
            System.out.println("""
                        You must enter a number!
                        Let's try again:""");
            selection = scanner.next();
        }

        return Integer.parseInt(selection);
    }

    public static String inputFilePath() {
        String filePath;

        System.out.println("Please enter the path of the file you want to encrypt / decrypt:");
        filePath = scanner.next();

        return filePath;
    }

}
