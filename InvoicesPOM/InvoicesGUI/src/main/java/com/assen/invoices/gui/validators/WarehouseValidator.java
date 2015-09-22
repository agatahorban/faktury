package com.assen.invoices.gui.validators;

import com.assen.invoices.gui.model.wrappers.WarehouseWrapper;

/**
 *
 * @author Arek
 */
public class WarehouseValidator {

    public String validateData(WarehouseWrapper warehouse) {
        StringBuilder errors = new StringBuilder();
        
        if(nullOrEmptyValue(warehouse.getName())) {
            errors.append("Nazwa nie może być pusta. \n");
        }
        
        return errors.toString();
    }
    
    private boolean nullOrEmptyValue(String value) {
        return value == null || value.equals("");
    }
}
