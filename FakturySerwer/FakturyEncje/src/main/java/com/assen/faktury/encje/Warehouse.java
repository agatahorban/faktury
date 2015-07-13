package com.assen.faktury.encje;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Arek
 */
@Entity
@Table(name = "warehouse")
public class Warehouse extends BasicEntity implements Serializable {
    
    @Column(name = "name")
    @Size(max = 25)
    private String name;
    
    @OneToMany(mappedBy = "warehouse", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<WarehouseGoods> warehouseGoods;
    
    public Warehouse() {
        warehouseGoods = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WarehouseGoods> getWarehouseGoods() {
        return warehouseGoods;
    }

    public void setWarehouseGoods(List<WarehouseGoods> warehouseGoods) {
        this.warehouseGoods = warehouseGoods;
    }

    
}
