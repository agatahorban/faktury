package com.assen.invoices.gui.model.wrappers;

import com.assen.invoices.entities.PurchaseInvoiceGoods;
import com.assen.invoices.entities.VATInvoice;
import com.assen.invoices.entities.VATInvoiceGoods;
import com.assen.invoices.gui.utils.DateUtil;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Arek
 */
public class VATInvoiceWrapper {

    private VATInvoice vatInvoice;

    private StringProperty mask;
    private IntegerProperty number;
    private ObjectProperty<LocalDate> dateOfIssue;
    private ObjectProperty<LocalDate> dateOfPayment;
    private StringProperty signature;
    private ContractorWrapper addresseWrapper;
    private UserWrapper vendorWrapper;
    private ObservableList<VATInvoiceGoodsWrapper> listOfGoods;

    public VATInvoiceWrapper(VATInvoice vatInvoice) {
        this.vatInvoice = vatInvoice;
        setWrapperValues();
    }

    public VATInvoice getVatInvoice() {
        getWrapperValues();
        return vatInvoice;
    }

    public void setVatInvoice(VATInvoice vatInvoice) {
        this.vatInvoice = vatInvoice;
        setWrapperValues();
    }

    public String getMask() {
        return mask.get();
    }

    public void setMask(String mask) {
        this.mask.set(mask);
    }

    public Integer getNumber() {
        return number.get();
    }

    public void setNumber(Integer number) {
        this.number.set(number);
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue.get();
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue.set(dateOfIssue);
    }

    public LocalDate getDateOfPayment() {
        return dateOfPayment.get();
    }

    public void setDateOfPayment(LocalDate dateOfPayment) {
        this.dateOfPayment.set(dateOfPayment);
    }
    
    public String getSignature() {
        return signature.get();
    }

    public void setSignature(String signature) {
        this.signature.set(signature);
    }

    public ContractorWrapper getAddresseWrapper() {
        return addresseWrapper;
    }

    public void setAddresseWrapper(ContractorWrapper addresseWrapper) {
        this.addresseWrapper = addresseWrapper;
    }

    public UserWrapper getVendorWrapper() {
        return vendorWrapper;
    }

    public void setVendorWrapper(UserWrapper vendorWrapper) {
        this.vendorWrapper = vendorWrapper;
    }

    public ObservableList<VATInvoiceGoodsWrapper> getListOfGoods() {
        return listOfGoods;
    }

    public void setListOfGoods(ObservableList<VATInvoiceGoodsWrapper> listOfGoods) {
        this.listOfGoods = listOfGoods;
    }

    public StringProperty maskProperty() {
        return mask;
    }

    public IntegerProperty numberProperty() {
        return number;
    }

    public ObjectProperty<LocalDate> dateOfIssueProperty() {
        return dateOfIssue;
    }
    
    public ObjectProperty<LocalDate> dateOfPaymentProperty() {
        return dateOfPayment;
    }

    public StringProperty signatureProperty() {
        return signature;
    }
    
    private void setWrapperValues() {
        this.addresseWrapper = new ContractorWrapper(vatInvoice.getAddresse());
        this.dateOfIssue = new SimpleObjectProperty<>(DateUtil.toLocalDate(vatInvoice.getDateOfIssue()));
        this.dateOfPayment = new SimpleObjectProperty<>(DateUtil.toLocalDate(vatInvoice.getDateOfPayment()));
        this.listOfGoods = FXCollections.observableArrayList(convertToWrapperList());
        this.mask = new SimpleStringProperty(vatInvoice.getMask());
        this.number = new SimpleIntegerProperty(vatInvoice.getNumber());
        this.signature = new SimpleStringProperty(vatInvoice.getSignature());
        this.vendorWrapper = new UserWrapper(vatInvoice.getVendor());
    }
    
    private void getWrapperValues() {
        vatInvoice.setAddresse(getAddresseWrapper().getContractor());
        vatInvoice.setDateOfIssue(DateUtil.toDate(getDateOfIssue()));
        vatInvoice.setDateOfPayment(DateUtil.toDate(getDateOfPayment()));
        vatInvoice.setListOfGoods(convertToList());
        vatInvoice.setMask(getMask());
        vatInvoice.setNumber(getNumber());
        vatInvoice.setSignature(getSignature());
        vatInvoice.setVendor(getVendorWrapper().getUser());
    }
    
    private List<VATInvoiceGoodsWrapper> convertToWrapperList() {
        List<VATInvoiceGoodsWrapper> listOfWrappers = new ArrayList<>();
        vatInvoice.getListOfGoods().stream().forEach((listOfGood) -> {
            listOfWrappers.add(new VATInvoiceGoodsWrapper(listOfGood));
        });
        return listOfWrappers;
    }
    
    private List<VATInvoiceGoods> convertToList() {
        List<VATInvoiceGoods> listOfUnwrappedGoods = new ArrayList<>();
        listOfGoods.stream().forEach((listOfGood) -> {
            listOfUnwrappedGoods.add(listOfGood.getVatInvoiceGoods());
        });
        return listOfUnwrappedGoods;
    }
}
