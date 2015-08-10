package com.assen.invoices.gui.model.wrappers;

import com.assen.invoices.entities.ExternalIncome;
import com.assen.invoices.entities.ExternalIncomeGoods;
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
public class ExternalIncomeWrapper {

    private ExternalIncome externalIncome;
    
    private ContractorWrapper supplierWrapper;
    private UserWrapper addresseWrapper;
    private StringProperty mask;
    private IntegerProperty number;
    private ObjectProperty<LocalDate> dateOfIssue;
    private IntegerProperty deliveryNoteNumberOfSupplier;
    private ObservableList<ExternalIncomeGoodsWrapper> listOfGoods;

    public ExternalIncomeWrapper(ExternalIncome externalIncome) {
        this.externalIncome = externalIncome;
        setWrapperValues();
    }

    public ExternalIncome getExternalIncome() {
        getWrapperValues();
        return externalIncome;
    }

    public void setExternalIncome(ExternalIncome externalIncome) {
        this.externalIncome = externalIncome;
        setWrapperValues();
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

    public Integer getDeliveryNoteNumberOfSupplier() {
        return deliveryNoteNumberOfSupplier.get();
    }

    public void setDeliveryNoteNumberOfSupplier(Integer deliveryNoteNumberOfSupplier) {
        this.deliveryNoteNumberOfSupplier.set(deliveryNoteNumberOfSupplier);
    }

    public ObservableList<ExternalIncomeGoodsWrapper> getListOfGoods() {
        return listOfGoods;
    }

    public void setListOfGoods(ObservableList<ExternalIncomeGoodsWrapper> listOfGoods) {
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
    
    public IntegerProperty deliveryNoteNumberOfSupplierProperty() {
        return deliveryNoteNumberOfSupplier;
    }
    
    private void setWrapperValues() {
        this.addresseWrapper = new UserWrapper(externalIncome.getAddresse());
        this.dateOfIssue = new SimpleObjectProperty<>(DateUtil.toLocalDate(externalIncome.getDateOfIssue()));
        this.deliveryNoteNumberOfSupplier = new SimpleIntegerProperty(externalIncome.getDeliveryNoteNumberOfSupplier());
        this.listOfGoods = FXCollections.observableArrayList(convertToWrapperList());
        this.mask = new SimpleStringProperty(externalIncome.getMask());
        this.number = new SimpleIntegerProperty(externalIncome.getNumber());
        this.supplierWrapper = new ContractorWrapper(externalIncome.getSupplier());
    }
    
    private void getWrapperValues() {
        externalIncome.setAddresse(getAddresseWrapper().getUser());
        externalIncome.setDateOfIssue(DateUtil.toDate(getDateOfIssue()));
        externalIncome.setDeliveryNoteNumberOfSupplier(getDeliveryNoteNumberOfSupplier());
        externalIncome.setListOfGoods(convertToList());
        externalIncome.setMask(getMask());
        externalIncome.setNumber(getNumber());
        externalIncome.setSupplier(getSupplierWrapper().getContractor());
    }
    
    private List<ExternalIncomeGoodsWrapper> convertToWrapperList() {
        List<ExternalIncomeGoodsWrapper> listOfWrappers = new ArrayList<>();
        externalIncome.getListOfGoods().stream().forEach((listOfGood) -> {
            listOfWrappers.add(new ExternalIncomeGoodsWrapper(listOfGood));
        });
        return listOfWrappers;
    }
    
    private List<ExternalIncomeGoods> convertToList() {
        List<ExternalIncomeGoods> listOfUnwrappedGoods = new ArrayList<>();
        listOfGoods.stream().forEach((listOfGood) -> {
            listOfUnwrappedGoods.add(listOfGood.getExternalIncomeGoods());
        });
        return listOfUnwrappedGoods;
    }
}
