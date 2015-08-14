package com.assen.invoices.gui.model.wrappers;

import com.assen.invoices.entities.Contractor;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Arek
 */
public class ContractorWrapper {

    private Contractor contractor;
    
    private BooleanProperty addressee;
    private BooleanProperty supplier;
    private BooleanProperty VATpayer;
    private StringProperty cutName;
    private StringProperty fullName;
    private StringProperty NIP;
    private AddressWrapper addressWrapper;
    private BankWrapper bankWrapper;
    private PaymentDateWrapper paymentDateWrapper;

    public ContractorWrapper(Contractor contractor) {
        this.contractor = contractor;
        setWrapperValues();
    }

    public Contractor getContractor() {
        getWrapperValues();
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
        setWrapperValues();
    }

    public Boolean isAddressee() {
        return addressee.get();
    }

    public void setAddressee(Boolean addressee) {
        this.addressee.set(addressee);
    }

    public Boolean isSupplier() {
        return supplier.get();
    }

    public void setSupplier(Boolean supplier) {
        this.supplier.set(supplier);
    }

    public Boolean isVATpayer() {
        return VATpayer.get();
    }

    public void setVATpayer(Boolean VATpayer) {
        this.VATpayer.set(VATpayer);
    }

    public String getCutName() {
        return cutName.get();
    }

    public void setCutName(String cutName) {
        this.cutName.set(cutName);
    }

    public String getFullName() {
        return fullName.get();
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    public String getNIP() {
        return NIP.get();
    }

    public void setNIP(String NIP) {
        this.NIP.set(NIP);
    }

    public AddressWrapper getAddressWrapper() {
        return addressWrapper;
    }

    public void setAddressWrapper(AddressWrapper addressWrapper) {
        this.addressWrapper = addressWrapper;
    }

    public BankWrapper getBankWrapper() {
        return bankWrapper;
    }

    public void setBankWrapper(BankWrapper bankWrapper) {
        this.bankWrapper = bankWrapper;
    }

    public PaymentDateWrapper getPaymentDateWrapper() {
        return paymentDateWrapper;
    }

    public void setPaymentDateWrapper(PaymentDateWrapper paymentDateWrapper) {
        this.paymentDateWrapper = paymentDateWrapper;
    }
    
    public BooleanProperty addresseeProperty() {
        return addressee;
    }
    
    public BooleanProperty supplierProperty() {
        return supplier;
    }
    
    public BooleanProperty VATPayerProperty() {
        return VATpayer;
    }
    
    public StringProperty cutNameProperty() {
        return cutName;
    }
    
    public StringProperty fullNameProperty() {
        return fullName;
    }
    
    public StringProperty NIPProperty() {
        return NIP;
    }
    
    private void setWrapperValues() {
        addressee = new SimpleBooleanProperty(contractor.isAddressee());
        supplier = new SimpleBooleanProperty(contractor.isSupplier());
        VATpayer = new SimpleBooleanProperty(contractor.isVATpayer());
        cutName = new SimpleStringProperty(contractor.getCutName());
        fullName = new SimpleStringProperty(contractor.getFullName());
        NIP = new SimpleStringProperty(contractor.getNIP());
        addressWrapper = new AddressWrapper(contractor.getAddress());
        bankWrapper = new BankWrapper(contractor.getBank());
        paymentDateWrapper = new PaymentDateWrapper(contractor.getPaymentDate());
    }
    
    private void getWrapperValues() {
        contractor.setAddress(getAddressWrapper().getAddress());
        contractor.setAddressee(isAddressee());
        contractor.setBank(getBankWrapper().getBank());
        contractor.setCutName(getCutName());
        contractor.setFullName(getFullName());
        contractor.setNIP(getNIP());
        contractor.setPaymentDate(getPaymentDateWrapper().getPaymentDate());
        contractor.setSupplier(isSupplier());
        contractor.setVATpayer(isVATpayer());
    }
}
