package com.assen.invoices.gui.model.wrappers;

import com.assen.invoices.entities.PaymentDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Arek
 */
public class PaymentDateWrapper {

    private PaymentDate paymentDate;
    
    private StringProperty description;
    private IntegerProperty amountOfDays;

    public PaymentDateWrapper(PaymentDate paymentDate) {
        this.paymentDate = paymentDate;
        setWrapperValues();
    }

    public PaymentDate getPaymentDate() {
        getWrapperValues();
        return paymentDate;
    }

    public void setPaymentDate(PaymentDate paymentDate) {
        this.paymentDate = paymentDate;
        setWrapperValues();
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public Integer getAmountOfDays() {
        return amountOfDays.get();
    }

    public void setAmountOfDays(Integer amountOfDays) {
        this.amountOfDays.set(amountOfDays);
    }
    
    public StringProperty descriptionProperty() {
        return description;
    }
    
    public IntegerProperty amountOfDaysProperty() {
        return amountOfDays;
    }
    
    private void setWrapperValues() {
        description = new SimpleStringProperty(paymentDate.getDescription());
        amountOfDays = new SimpleIntegerProperty(paymentDate.getAmountOfDays());
    }
    
    private void getWrapperValues() {
        paymentDate.setAmountOfDays(getAmountOfDays());
        paymentDate.setDescription(getDescription());
    }
}
