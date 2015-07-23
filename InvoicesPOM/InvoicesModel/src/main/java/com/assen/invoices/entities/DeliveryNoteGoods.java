package com.assen.invoices.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "delivery_note_goods")
@XmlRootElement(name = "deliveryNoteGoods")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeliveryNoteGoods extends BasicEntity implements Serializable {
    
    @ManyToOne
    @JoinColumn(name = "delivery_note_id")
    @XmlElement
    private DeliveryNote deliveryNote;
    
    @ManyToOne
    @JoinColumn(name = "goods_id")
    @XmlElement
    private Goods goods;
    
    @Column
    @XmlElement
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
