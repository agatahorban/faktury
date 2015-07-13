package com.assen.invoices.entities;

import java.io.Serializable;
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
@Table(name = "delivery_note_goods")
public class DeliveryNoteGoods extends BasicEntity implements Serializable {
    
    @ManyToOne
    @JoinColumn(name = "delivery_note_id")
    private DeliveryNote deliveryNote;
    
    @ManyToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;
    
    @Column
    private int quantity;

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

    public DeliveryNote getDeliveryNote() {
        return deliveryNote;
    }

    public void setDeliveryNote(DeliveryNote deliveryNote) {
        this.deliveryNote = deliveryNote;
    }
    
    
   
}
