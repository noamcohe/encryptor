package menus;
import crypto.Details;
import userInput.*;
import java.util.Map;
import java.util.HashMap;
import crypto.AlgoManager;
import crypto.AlgoGenerator;
import static utils.Constants.*;
import com.google.inject.Inject;
import java.util.function.Function;


public class StartMenu implements MenuChoice<Function<Void, Boolean>> {
    final String START_MENU_DESC = """
                Welcome to encryptor program!
                Please enter the option number you would like to perform:
                
                (1) -- Encryption
                (2) -- Decryption
                (3) -- Close program""";
    final int ENC_OPT = 1;
    final int DEC_OPT = 2;
    final int CLOSE_OPT = 3;
    final boolean EXIT_PROGRAM = true;
    final boolean NOT_EXIT = false;

    private final Map<Integer, Function<Void, Boolean>> menu;
    private final AlgoManager algoManager;
    private final AlgoGenerator algoGenerator;
    private final GeneralInput generalInput;
    private final Details details;

    @Inject
    public StartMenu(AlgoGenerator algoGenerator, AlgoManager algoManager, GeneralInput generalInput, Details details) {
        this.details = details;
        this.menu = initMenu();
        this.algoManager = algoManager;
        this.algoGenerator = algoGenerator;
        this.generalInput = generalInput;
    }

    private Map<Integer, Function<Void, Boolean>> initMenu() {
        var menu = new HashMap<Integer, Function<Void, Boolean>>();

        menu.put(ENC_OPT, (Void) -> {
            details.setup(ENC_FLAG);
            algoManager.execute(algoGenerator.generate(details), details);
            return NOT_EXIT;
        });

        menu.put(DEC_OPT, (Void) -> {
            details.setup(DEC_FLAG);
            algoManager.execute(algoGenerator.generate(details), details);
            return NOT_EXIT;
        });

        menu.put(CLOSE_OPT, (Void) -> EXIT_PROGRAM);

        return menu;
    }

    @Override
    public Function<Void, Boolean> getChoice() {
        return menu.get(generalInput.getNumByRange(menu.size()));
    }

    @Override
    public void display() {System.out.println(START_MENU_DESC);}
}