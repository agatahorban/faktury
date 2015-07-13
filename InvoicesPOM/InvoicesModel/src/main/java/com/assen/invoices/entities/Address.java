package com.assen.invoices.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Agata
 */
@Entity
@Table(name = "address")
public class Address extends BasicEntity implements Serializable{
    
    @Column
    @Size(max = 50)
    private String street;
    
    @Column(name = "house_number")
    private int houseNumber;
    
    @Column(name = "apartment_number")
    private int apartmentNumber;
    
    @Column(name = "postal_code")
    @Size(max = 6)
    private String postalCode;
    
    @Column
    @Size(max = 50)
    private String town;
    
    @Column
    @Size(max = 50)
    private String borough;
    
    @Column
    @Size(max = 50)
    private String county;
    
    @Column
    @Size(max = 50)
    private String province;
    
    @Column
    @Size(max = 50)
    private String country;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getBorough() {
        return borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    

}
