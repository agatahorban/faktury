package com.assen.invoices.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "unit_of_measure")
@XmlRootElement(name = "unitOfMeasure")
@XmlAccessorType(XmlAccessType.FIELD)
public class UnitOfMeasure extends BasicEntity implements Serializable {
    
    @Column (unique = true)
    @XmlElement
    private String shortcut;
    
    @Column
    @Size(max = 50)
    @XmlElement
    private String name;

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   
    
    
}
