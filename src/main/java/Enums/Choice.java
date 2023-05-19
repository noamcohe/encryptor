package Enums;

public enum Choice {
    ENCRYPTION(1), DECRYPTION(2), CLOSE_PROGRAM(3);

    private final int selection;

    Choice(int i) {
        this.selection = i;
    }

    public int getSelection() {
        return selection;
    }

    public static Choice castToEnum(int selection) {
        switch (selection) {
            case 1 -> {
                return ENCRYPTION;
            }

            case 2 -> {
                return DECRYPTION;
            }

            case 3 -> {
                return CLOSE_PROGRAM;
            }

        }

        return ENCRYPTION;
    }
}
