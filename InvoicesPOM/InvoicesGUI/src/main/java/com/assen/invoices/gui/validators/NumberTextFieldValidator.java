package com.assen.invoices.gui.validators;

import javafx.scene.input.KeyEvent;

/**
 *
 * @author Arek
 */
public class NumberTextFieldValidator {
    
    private static final String QUANTITY_REGEX = "1234567890";
    private static final String PRICE_REGEX = "1234567890,.";
    
    public static void checkQuantityField(KeyEvent event) {
        if (!QUANTITY_REGEX.contains(event.getCharacter())) {
            event.consume();
        }
    }
    
    public static void checkPriceField(KeyEvent event, String text) {
        if (text.length() < 1) {
            checkQuantityField(event);
        } else if (alreadyContainsDotOrComma(event.getCharacter(), text)) {
            event.consume();
        } else if (!PRICE_REGEX.contains(event.getCharacter())) {
            event.consume();
        }
    }
    
    private static boolean alreadyContainsDotOrComma(String character, String text) {
        return ((character.equals(",") || character.equals("."))
                && (text.contains(",") || text.contains(".")));
    }
}
