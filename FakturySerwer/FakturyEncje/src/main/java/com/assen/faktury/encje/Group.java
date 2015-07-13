package com.assen.faktury.encje;

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
@Table(name = "group")
public class Group extends BasicEntity implements Serializable {
    
    
    @Column (unique = true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
  
}
