package com.assen.invoices.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Agata
 */
@Entity
@Table(name = "payment_date")
public class PaymentDate extends BasicEntity implements Serializable {

    @Column(name = "description")
    private String description;

    @Column(name = "amountOfDays")
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
