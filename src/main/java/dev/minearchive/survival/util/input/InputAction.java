package dev.minearchive.survival.util.input;

public enum InputAction {
    PRESS, REPEAT, RELEASE, UNKNOWN;

    public static InputAction getByInt(int i) {
        switch (i) {
            case 0 -> {
                return RELEASE;
            }
            case 1 -> {
                return PRESS;
            }
            case 2 -> {
                return REPEAT;
            }
            default -> {
                return UNKNOWN;
            }
        }
    }
}
