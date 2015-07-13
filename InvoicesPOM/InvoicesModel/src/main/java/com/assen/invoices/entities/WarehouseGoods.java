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

/**
 *
 * @author Arek
 */
@Entity
@Table(name = "warehouse_goods")
public class WarehouseGoods extends BasicEntity implements Serializable {

    
    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;
    
    @ManyToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;
    
    @Column
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
