package com.assen.invoices.gui.model.wrappers;

import com.assen.invoices.entities.VATRate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Arek
 */
public class VATRateWrapper {

    private VATRate vatRate;
    
    private StringProperty name;
    private StringProperty description;
    private IntegerProperty value;

    public VATRateWrapper(VATRate vatRate) {
        this.vatRate = vatRate;
        setWrapperValues();
    }

    public VATRate getVatRate() {
        getWrapperValues();
        return vatRate;
    }

    public void setVatRate(VATRate vatRate) {
        this.vatRate = vatRate;
        setWrapperValues();
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public Integer getValue() {
        return value.get();
    }

    public void setValue(Integer value) {
        this.value.set(value);
    }
    
    public StringProperty nameProperty() {
        return name;
    }
    
    public StringProperty descriptionProperty() {
        return description;
    }
    
    public IntegerProperty valueProperty() {
        return value;
    }
    
    private void setWrapperValues() {
        name = new SimpleStringProperty(vatRate.getName());
        description = new SimpleStringProperty(vatRate.getDescription());
        value = new SimpleIntegerProperty(vatRate.getValue());
    }
    
    private void getWrapperValues() {
        vatRate.setDescription(getDescription());
        vatRate.setName(getName());
        vatRate.setValue(getValue());
    }
}
