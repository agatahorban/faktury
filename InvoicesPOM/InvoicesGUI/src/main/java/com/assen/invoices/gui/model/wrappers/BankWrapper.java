package com.assen.invoices.gui.model.wrappers;

import com.assen.invoices.entities.Bank;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Arek
 */
public class BankWrapper {

    private Bank bank;
    
    private StringProperty bankName;
    private StringProperty currency;
    private StringProperty number;

    public BankWrapper(Bank bank) {
        this.bank = bank;
        setWrapperValues();
    }

    public Bank getBank() {
        getWrapperValues();
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
        setWrapperValues();
    }

    public String getBankName() {
        return bankName.get();
    }

    public void setBankName(String bankName) {
        this.bankName.set(bankName);
    }

    public String getCurrency() {
        return currency.get();
    }

    public void setCurrency(String currency) {
        this.currency.set(currency);
    }

    public String getNumber() {
        return number.get();
    }

    public void setNumber(String number) {
        this.number.set(number);
    }
    
    public StringProperty bankNameProperty() {
        return bankName;
    }
    
    public StringProperty currencyProperty() {
        return currency;
    }
    
    public StringProperty numberProperty() {
        return number;
    }
    
    private void setWrapperValues() {
        this.bankName = new SimpleStringProperty(bank.getBankName());
        this.currency = new SimpleStringProperty(bank.getCurrency());
        this.number = new SimpleStringProperty(bank.getNumber());
    }
    
    private void getWrapperValues() {
        bank.setBankName(getBankName());
        bank.setCurrency(getCurrency());
        bank.setNumber(getNumber());
    }
}
