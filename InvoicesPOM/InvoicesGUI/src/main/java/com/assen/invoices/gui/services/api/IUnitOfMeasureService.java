/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assen.invoices.gui.services.api;

import com.assen.invoices.gui.model.wrappers.UnitOfMeasureWrapper;
import javafx.collections.ObservableList;

/**
 *
 * @author horbana
 */
public interface IUnitOfMeasureService {

    public ObservableList<UnitOfMeasureWrapper> populateAllUnits();
    
}
