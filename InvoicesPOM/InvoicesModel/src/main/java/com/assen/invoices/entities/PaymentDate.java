package com.assen.invoices.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Agata
 */
@Entity
@Table(name = "payment_date")
@XmlRootElement(name = "paymentDate")
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentDate extends BasicEntity implements Serializable {

    @Column(name = "description")
    @XmlElement
    private String description;

    @Column(name = "amountOfDays")
    @XmlElement
    private int amountOfDays;

    public PaymentDate(String description, int amountOfDays) {
        this.description = description;
        this.amountOfDays = amountOfDays;
    }
    
    public PaymentDate() {
        
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmountOfDays() {
        return amountOfDays;
    }

    public void setAmountOfDays(int amountOfDays) {
        this.amountOfDays = amountOfDays;
    }

   

}
