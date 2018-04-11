package com.greysonparrelli.gitlabciandroid;


/**
 * Validates inputs.
 *
 * @author Greyson Parrelli (keybase.io/greyson)
 */
public class InputValidator {

    private static final InputValidator INSTANCE = new InputValidator();

    public static InputValidator getInstance() {
        return INSTANCE;
    }

    public boolean isInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
