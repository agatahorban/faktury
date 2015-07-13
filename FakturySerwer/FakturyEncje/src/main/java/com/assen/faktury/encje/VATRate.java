package com.assen.faktury.encje;

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
@Table(name = "vat_rate")
public class VATRate extends BasicEntity implements Serializable {

    @Column
    @Size(max = 40)
    private String name;

    @Column
    private String description;

    @Column(name="_value")
    private int value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    

    

}
