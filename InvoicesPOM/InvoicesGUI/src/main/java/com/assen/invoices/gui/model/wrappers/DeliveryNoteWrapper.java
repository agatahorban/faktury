package com.assen.invoices.gui.model.wrappers;

import com.assen.invoices.entities.DeliveryNote;
import com.assen.invoices.entities.DeliveryNoteGoods;
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
public class DeliveryNoteWrapper {

    private DeliveryNote deliveryNote;
    
    private StringProperty mask;
    private IntegerProperty number;
    private StringProperty currency;
    private ObjectProperty<LocalDate> dateOfIssue;
    private ContractorWrapper addresseeWrapper;
    private UserWrapper vendorWrapper;
    private ObservableList<DeliveryNoteGoodsWrapper> listOfGoods;

    public DeliveryNoteWrapper(DeliveryNote deliveryNote) {
        this.deliveryNote = deliveryNote;
        setWrapperValues();
    }

    public DeliveryNote getDeliveryNote() {
        getWrapperValues();
        return deliveryNote;
    }

    public void setDeliveryNote(DeliveryNote deliveryNote) {
        this.deliveryNote = deliveryNote;
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

    public String getCurrency() {
        return currency.get();
    }

    public void setCurrency(String currency) {
        this.currency.set(currency);
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue.get();
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue.set(dateOfIssue);
    }

    public ContractorWrapper getAddresseeWrapper() {
        return addresseeWrapper;
    }

    public void setAddresseeWrapper(ContractorWrapper addresseeWrapper) {
        this.addresseeWrapper = addresseeWrapper;
    }

    public UserWrapper getVendorWrapper() {
        return vendorWrapper;
    }

    public void setVendorWrapper(UserWrapper vendorWrapper) {
        this.vendorWrapper = vendorWrapper;
    }

    public ObservableList<DeliveryNoteGoodsWrapper> getListOfGoods() {
        return listOfGoods;
    }

    public void setListOfGoods(ObservableList<DeliveryNoteGoodsWrapper> listOfGoods) {
        this.listOfGoods = listOfGoods;
    }
    
    public StringProperty maskProperty() {
        return mask;
    }
    
    public IntegerProperty numberProperty() {
        return number;
    }
    
    public StringProperty currencyProperty() {
        return currency;
    }
    
    public ObjectProperty<LocalDate> dateOfIssueProperty() {
        return dateOfIssue;
    }
    
    private void setWrapperValues() {
        mask = new SimpleStringProperty(deliveryNote.getMask());
        number = new SimpleIntegerProperty(deliveryNote.getNumber());
        currency = new SimpleStringProperty(deliveryNote.getCurrency());
        dateOfIssue = new SimpleObjectProperty<>(DateUtil.toLocalDate(deliveryNote.getDateOfIssue()));
        addresseeWrapper = new ContractorWrapper(deliveryNote.getAddressee());
        vendorWrapper = new UserWrapper(deliveryNote.getVendor());
        listOfGoods = FXCollections.observableArrayList(convertToWrapperList());
    }
    
    private void getWrapperValues() {
        deliveryNote.setAddressee(getAddresseeWrapper().getContractor());
        deliveryNote.setCurrency(getCurrency());
        deliveryNote.setDateOfIssue(DateUtil.toDate(getDateOfIssue()));
        deliveryNote.setMask(getMask());
        deliveryNote.setNumber(getNumber());
        deliveryNote.setVendor(getVendorWrapper().getUser());
        deliveryNote.setListOfGoods(convertToList());
    }
    
    private List<DeliveryNoteGoodsWrapper> convertToWrapperList() {
        List<DeliveryNoteGoodsWrapper> listOfWrappers = new ArrayList<>();
        deliveryNote.getListOfGoods().stream().forEach((listOfGood) -> {
            listOfWrappers.add(new DeliveryNoteGoodsWrapper(listOfGood));
        });
        return listOfWrappers;
    }
    
    private List<DeliveryNoteGoods> convertToList() {
        List<DeliveryNoteGoods> listOfUnwrappedGoods = new ArrayList<>();
        listOfGoods.stream().forEach((listOfGood) -> {
            listOfUnwrappedGoods.add(listOfGood.getDeliveryNoteGoods());
        });
        return listOfUnwrappedGoods;
    }
}
