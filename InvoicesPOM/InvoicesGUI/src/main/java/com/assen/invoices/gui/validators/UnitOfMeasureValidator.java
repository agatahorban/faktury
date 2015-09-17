package com.assen.invoices.gui.validators;

import com.assen.invoices.gui.model.wrappers.UnitOfMeasureWrapper;

/**
 *
 * @author horbana
 */
public class UnitOfMeasureValidator {

    private boolean nullOrEmptyValue(String value) {
        return value == null || value.equals("");
    }

    public String validateData(UnitOfMeasureWrapper unit) {
        StringBuilder errors = new StringBuilder();

        if (nullOrEmptyValue(unit.getName())) {
            errors.append("Nazwa nie może być pusta \n");
        }

        if (nullOrEmptyValue(unit.getShortcut())) {
            errors.append("Skrót nie może być pusty \n");
        }
        return errors.toString();
    }
}
