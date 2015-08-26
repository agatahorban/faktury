/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assen.invoices.gui.services.api;

import com.assen.invoices.gui.model.wrappers.GoodsWrapper;
import com.assen.invoices.gui.validators.GoodsValidator;
import javafx.collections.ObservableList;

/**
 *
 * @author chrobota
 */
public interface IGoodsService {

    ObservableList<String> getObservableData(DataType type);

    void populateDataFromServer();

    String validData(GoodsWrapper goods, GoodsValidator.GoodsValidationData validationData);
    
    public enum DataType {

        COLLECTIVE_PACKAGES,
        GROUPS,
        VAT_RATES,
        CONTRACTORS,
        UNITS_OF_MEASURE
    }
}
