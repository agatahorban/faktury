package com.assen.faktury.encje.base;

import java.io.Serializable;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 *
 * @author Arek
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable{

    @Transient public static final long serialVersionUID = 196919661993L;
    
    public BaseEntity() {
        
    }
}
