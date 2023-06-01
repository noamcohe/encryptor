package menus;
import consoleUI.ConsoleInput;
import consoleUI.GeneralInput;
import utils.Constants;

import java.util.HashMap;
import java.util.Map;


public class StartMenu implements MenuChoice<Boolean> {
    public static final Map<Integer, StartMenu> menuChoices = new HashMap<>();
    private static final MenuChoice<Void> ciphersMenu = new CiphersMenu();
    private static final GeneralInput consoleInput = new ConsoleInput();


    static {
        menuChoices.put(1, new StartMenu("(1) -- Encryption") {
            @Override
            public Boolean performAction() {
                ciphersMenu.display(Constants.ENCRYPTION_MENU);

                CiphersMenu.encryptFlag(Constants.ENC_FLAG);
                CiphersMenu.getChoice().performAction();

                return false;
            }
        });

        menuChoices.put(2, new StartMenu("(2) -- Decryption") {
            @Override
            public Boolean performAction() {
                ciphersMenu.display(Constants.DECRYPTION_MENU);

                CiphersMenu.encryptFlag(Constants.DEC_FLAG);
                CiphersMenu.getChoice().performAction();

                return false;
            }
        });

        menuChoices.put(3, new StartMenu("(3) -- Close program") {
            @Override
            public Boolean performAction() {
                return Constants.EXIT_PROGRAM;
            }
        });
    }

    public String description;

    public StartMenu() {}
    public StartMenu(String description) {
        this.description = description;
    }
    public String description() {
        return description;
    }

    public static StartMenu getChoice() {
        int choiceIndex = consoleInput.getNumByRange(menuChoices.size());
        return menuChoices.get(choiceIndex);
    }

    @Override
    public Boolean performAction() {
        return null;
    }

    @Override
    public void display(String message) {
        System.out.println(message);

        for (StartMenu option : menuChoices.values()) {
            System.out.println(option.description());
        }
    }
}
