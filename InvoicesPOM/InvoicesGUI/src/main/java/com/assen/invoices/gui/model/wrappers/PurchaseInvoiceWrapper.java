package com.assen.invoices.gui.model.wrappers;

import com.assen.invoices.entities.PurchaseInvoice;
import com.assen.invoices.entities.PurchaseInvoiceGoods;
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
public class PurchaseInvoiceWrapper {

    private PurchaseInvoice purchaseInvoice;
    
    private StringProperty mask;
    private IntegerProperty number;
    private ObjectProperty<LocalDate> dateOfIssue;
    private StringProperty signature;
    private IntegerProperty VATInvoiceOfSupplierNumber;
    private ContractorWrapper supplierWrapper;
    private UserWrapper addresseWrapper;
    private ObservableList<PurchaseInvoiceGoodsWrapper> listOfGoods;

    public PurchaseInvoiceWrapper(PurchaseInvoice purchaseInvoice) {
        this.purchaseInvoice = purchaseInvoice;
        setWrapperValues();
    }

    public PurchaseInvoice getPurchaseInvoice() {
        getWrapperValues();
        return purchaseInvoice;
    }

    public void setPurchaseInvoice(PurchaseInvoice purchaseInvoice) {
        this.purchaseInvoice = purchaseInvoice;
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

    public String getSignature() {
        return signature.get();
    }

    public void setSignature(String signature) {
        this.signature.set(signature);
    }

    public Integer getVATInvoiceOfSupplierNumber() {
        return VATInvoiceOfSupplierNumber.get();
    }

    public void setVATInvoiceOfSupplierNumber(Integer VATInvoiceOfSupplierNumber) {
        this.VATInvoiceOfSupplierNumber.set(VATInvoiceOfSupplierNumber);
    }

    public ContractorWrapper getSupplierWrapper() {
        return supplierWrapper;
    }

    public void setSupplierWrapper(ContractorWrapper supplierWrapper) {
        this.supplierWrapper = supplierWrapper;
    }

    public UserWrapper getAddresseWrapper() {
        return addresseWrapper;
    }

    public void setAddresseWrapper(UserWrapper addresseWrapper) {
        this.addresseWrapper = addresseWrapper;
    }

    public ObservableList<PurchaseInvoiceGoodsWrapper> getListOfGoods() {
        return listOfGoods;
    }

    public void setListOfGoods(ObservableList<PurchaseInvoiceGoodsWrapper> listOfGoods) {
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
    
    public StringProperty signatureProperty() {
        return signature;
    }
    
    public IntegerProperty vatInvoiceOfSupplierNumberProperty() {
        return VATInvoiceOfSupplierNumber;
    }
    
    private void setWrapperValues() {
        this.VATInvoiceOfSupplierNumber = new SimpleIntegerProperty(purchaseInvoice.getVATInvoiceOfSupplierNumber());
        this.addresseWrapper = new UserWrapper(purchaseInvoice.getAddressee());
        this.dateOfIssue = new SimpleObjectProperty<>(DateUtil.toLocalDate(purchaseInvoice.getDateOfIssue()));
        this.listOfGoods = FXCollections.observableArrayList(convertToWrapperList());
        this.mask = new SimpleStringProperty(purchaseInvoice.getMask());
        this.number = new SimpleIntegerProperty(purchaseInvoice.getNumber());
        this.signature = new SimpleStringProperty(purchaseInvoice.getSignature());
        this.supplierWrapper = new ContractorWrapper(purchaseInvoice.getSupplier());
    }
    
    private void getWrapperValues() {
        purchaseInvoice.setAddressee(getAddresseWrapper().getUser());
        purchaseInvoice.setDateOfIssue(DateUtil.toDate(getDateOfIssue()));
        purchaseInvoice.setListOfGoods(convertToList());
        purchaseInvoice.setMask(getMask());
        purchaseInvoice.setNumber(getNumber());
        purchaseInvoice.setSignature(getSignature());
        purchaseInvoice.setSupplier(getSupplierWrapper().getContractor());
        purchaseInvoice.setVATInvoiceOfSupplierNumber(getVATInvoiceOfSupplierNumber());
    }
    
    private List<PurchaseInvoiceGoodsWrapper> convertToWrapperList() {
        List<PurchaseInvoiceGoodsWrapper> listOfWrappers = new ArrayList<>();
        purchaseInvoice.getListOfGoods().stream().forEach((listOfGood) -> {
            listOfWrappers.add(new PurchaseInvoiceGoodsWrapper(listOfGood));
        });
        return listOfWrappers;
    }
    
    private List<PurchaseInvoiceGoods> convertToList() {
        List<PurchaseInvoiceGoods> listOfUnwrappedGoods = new ArrayList<>();
        listOfGoods.stream().forEach((listOfGood) -> {
            listOfUnwrappedGoods.add(listOfGood.getPurchaseInvoiceGoods());
        });
        return listOfUnwrappedGoods;
    }
}
