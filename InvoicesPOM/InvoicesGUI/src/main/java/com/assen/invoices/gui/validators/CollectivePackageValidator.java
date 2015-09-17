package com.assen.invoices.gui.validators;

import com.assen.invoices.gui.model.wrappers.CollectivePackageWrapper;

/**
 *
 * @author horbana
 */
public class CollectivePackageValidator {
    private boolean nullOrEmptyValue(String value) {
        return value == null || value.equals("");
    }
    
     public String validateData(CollectivePackageWrapper collectivePackage) {
        StringBuilder errors = new StringBuilder();

        if (nullOrEmptyValue(collectivePackage.getFullName())) {
            errors.append("Nazwa pełna nie może być pusta \n");
        }

        if (nullOrEmptyValue(collectivePackage.getCutName())) {
            errors.append("Nazwa skrócona nie może być pusty \n");
        }
        return errors.toString();
    }
}
