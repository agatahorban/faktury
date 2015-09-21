package com.assen.invoices.dto;

import com.assen.invoices.entities.Warehouse;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Arek
 */
@XmlRootElement(name = "warehouseList")
@XmlAccessorType(XmlAccessType.FIELD)
public class WarehouseListDto {

    @XmlElement(name = "warehouse")
    private List<Warehouse> warehouses;

    public WarehouseListDto() {
        warehouses = new ArrayList<>();
    }
    
    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }
}
