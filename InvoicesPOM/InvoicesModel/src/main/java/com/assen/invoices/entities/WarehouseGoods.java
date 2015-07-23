package com.assen.invoices.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Arek
 */
@Entity
@Table(name = "warehouse_goods")
@XmlRootElement(name = "warehouseGoods")
@XmlAccessorType(XmlAccessType.FIELD)
public class WarehouseGoods extends BasicEntity implements Serializable {

    
    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    @XmlElement
    private Warehouse warehouse;
    
    @ManyToOne
    @JoinColumn(name = "goods_id")
    @XmlElement
    private Goods goods;
    
    @Column
    @XmlElement
    private int quantity;

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
   
   
}
