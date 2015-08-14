package com.assen.invoices.gui.model.wrappers;

import com.assen.invoices.entities.Address;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Arek
 */
public class AddressWrapper {

    private Address address;
    
    private StringProperty street;
    private IntegerProperty houseNumber;
    private IntegerProperty apartmentNumber;
    private StringProperty postalCode;
    private StringProperty town;
    private StringProperty borough;
    private StringProperty county;
    private StringProperty province;
    private StringProperty country;

    public AddressWrapper(Address address) {
        this.address = address;
        setWrapperValues();
    }

    public Address getAddress() {
        getWrapperValues();
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
        setWrapperValues();
    }

    public String getStreet() {
        return street.get();
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public Integer getHouseNumber() {
        return houseNumber.get();
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber.set(houseNumber);
    }

    public Integer getApartmentNumber() {
        return apartmentNumber.get();
    }

    public void setApartmentNumber(Integer apartmentNumber) {
        this.apartmentNumber.set(apartmentNumber);
    }

    public String getPostalCode() {
        return postalCode.get();
    }

    public void setPostalCode(String postalCode) {
        this.postalCode.set(postalCode);
    }

    public String getTown() {
        return town.get();
    }

    public void setTown(String town) {
        this.town.set(town);
    }

    public String getBorough() {
        return borough.get();
    }

    public void setBorough(String borough) {
        this.borough.set(borough);
    }

    public String getCounty() {
        return county.get();
    }

    public void setCounty(String county) {
        this.county.set(county);
    }

    public String getProvince() {
        return province.get();
    }

    public void setProvince(String province) {
        this.province.set(province);
    }

    public String getCountry() {
        return country.get();
    }

    public void setCountry(String country) {
        this.country.set(country);
    }
    
    public StringProperty streetProperty() {
        return street;
    }
    
    public IntegerProperty houseNumberProperty() {
        return houseNumber;
    }
    
    public IntegerProperty apartmentNumberProperty() {
        return apartmentNumber;
    }
    
    public StringProperty postalCodeProperty() {
        return postalCode;
    }
    
    public StringProperty townProperty() {
        return town;
    }
    
    public StringProperty boroughProperty() {
        return borough;
    }
    
    public StringProperty countyProperty() {
        return county;
    }
    
    public StringProperty provinceProperty() {
        return province;
    }
    
    public StringProperty countryProperty() {
        return country;
    }
    
    private void setWrapperValues() {
        this.street = new SimpleStringProperty(address.getStreet());
        this.houseNumber = new SimpleIntegerProperty(address.getHouseNumber());
        this.apartmentNumber = new SimpleIntegerProperty(address.getApartmentNumber());
        this.postalCode = new SimpleStringProperty(address.getPostalCode());
        this.town = new SimpleStringProperty(address.getTown());
        this.borough = new SimpleStringProperty(address.getBorough());
        this.county = new SimpleStringProperty(address.getCounty());
        this.province = new SimpleStringProperty(address.getProvince());
        this.country = new SimpleStringProperty(address.getCountry());
    }

    private void getWrapperValues() {
        address.setApartmentNumber(getApartmentNumber());
        address.setBorough(getBorough());
        address.setCountry(getCountry());
        address.setCounty(getCounty());
        address.setHouseNumber(getHouseNumber());
        address.setPostalCode(getPostalCode());
        address.setProvince(getProvince());
        address.setStreet(getStreet());
        address.setTown(getTown());
    }
}
