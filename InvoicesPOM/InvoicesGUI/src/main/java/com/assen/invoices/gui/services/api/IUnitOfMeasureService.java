/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assen.invoices.gui.services.api;

import com.assen.invoices.gui.model.wrappers.UnitOfMeasureWrapper;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author horbana
 */
public interface IUnitOfMeasureService {

    ObservableList<UnitOfMeasureWrapper> populateAllUnits();
    boolean deleteData(List<UnitOfMeasureWrapper> unitOfMeasureToDelete);
    String validData(UnitOfMeasureWrapper unit);
    ObservableList<UnitOfMeasureWrapper> filterByShortcut(String shortcut);
}
