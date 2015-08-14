package com.assen.invoices.gui.model.wrappers;

import com.assen.invoices.entities.UnitOfMeasure;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Arek
 */
public class UnitOfMeasureWrapper {

    private UnitOfMeasure unitOfMeasure;
    
    private StringProperty shortcut;
    private StringProperty name;

    public UnitOfMeasureWrapper(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
        setWrapperValues();
    }

    public UnitOfMeasure getUnitOfMeasure() {
        getWrapperValues();
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
        setWrapperValues();
    }

    public String getShortcut() {
        return shortcut.get();
    }

    public void setShortcut(String shortcut) {
        this.shortcut.set(shortcut);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }
    
    public StringProperty shortcutProperty() {
        return shortcut;
    }
    
    public StringProperty nameProperty() {
        return name;
    }
    
    private void setWrapperValues() {
        name = new SimpleStringProperty(unitOfMeasure.getName());
        shortcut = new SimpleStringProperty(unitOfMeasure.getName());
    }
    
    private void getWrapperValues() {
        unitOfMeasure.setName(getName());
        unitOfMeasure.setShortcut(getShortcut());
    }
}
