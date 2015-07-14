package com.assen.invoices.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Agata
 */
@Entity
@Table(name = "contractor")
@XmlRootElement(name = "contractor")
@XmlAccessorType(XmlAccessType.FIELD)
public class Contractor extends BasicEntity implements Serializable {

    @Column(name = "is_addressee")
    @XmlElement
    private boolean addressee;

    @Column(name = "is_supplier")
    @XmlElement
    private boolean supplier;

    @Column(name = "is_vat_payer")
    @XmlElement
    private boolean VATpayer;

    @Column(name = "cut_name")
    @Size(max = 30)
    @XmlElement
    private String cutName;

    @Column(name = "full_name")
    @XmlElement
    private String fullName;

    @Column(name = "nip")
    @Size(min = 10, max = 13)
    @XmlElement
    private String NIP;

    @ManyToOne
    @JoinColumn(name = "address_id")
    @XmlElement
    private Address address;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    @XmlElement
    private Bank bank;

    @ManyToOne
    @JoinColumn(name = "payment_date_id")
    @XmlElement
    private PaymentDate paymentDate;

    public boolean isAddressee() {
        return addressee;
    }

    public void setAddressee(boolean addressee) {
        this.addressee = addressee;
    }

    public boolean isSupplier() {
        return supplier;
    }

    public void setSupplier(boolean supplier) {
        this.supplier = supplier;
    }

    public boolean isVATpayer() {
        return VATpayer;
    }

    public void setVATpayer(boolean VATpayer) {
        this.VATpayer = VATpayer;
    }

    public String getCutName() {
        return cutName;
    }

    public void setCutName(String cutName) {
        this.cutName = cutName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public PaymentDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(PaymentDate paymentDate) {
        this.paymentDate = paymentDate;
    }
    
    

}
