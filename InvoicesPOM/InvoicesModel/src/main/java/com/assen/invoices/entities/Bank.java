package com.assen.invoices.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Agata
 */
@Entity
@Table(name = "bank")
public class Bank extends BasicEntity implements Serializable {

   

    @Column(name = "bank_name")
//    @Length(max = 30)
    @Size(max = 30)
    private String bankName;

    @Column
//    @Length(max = 5)
    @Size(max = 5)
    private String currency;

    @Column(unique = true)
    @Size(min = 26, max = 26)
    private String number;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    
  }
