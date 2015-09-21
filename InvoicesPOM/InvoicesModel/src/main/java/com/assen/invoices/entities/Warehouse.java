package com.assen.invoices.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Arek
 */
@Entity
@Table(name = "warehouse")
@XmlRootElement(name = "warehouse")
@XmlAccessorType(XmlAccessType.FIELD)
public class Warehouse extends BasicEntity implements Serializable {

    @Column(name = "name")
    @Size(max = 25)
    @XmlElement
    private String name;

    @OneToMany(mappedBy = "warehouse", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @XmlElement
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

    public static Warehouse copyOf(Warehouse warehouse) {
        Warehouse newWarehouse = new Warehouse();
        newWarehouse.setName(warehouse.getName());
        newWarehouse.setVersion(warehouse.getVersion());
        newWarehouse.setId(warehouse.getId());
        newWarehouse.setWarehouseGoods(warehouse.getWarehouseGoods());
        return newWarehouse;
    }
}
