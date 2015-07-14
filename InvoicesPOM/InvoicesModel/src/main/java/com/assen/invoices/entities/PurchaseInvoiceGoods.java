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

@Entity
@Table(name = "purchase_invoice_goods")
@XmlRootElement(name = "purchaseInvoiceGoods")
@XmlAccessorType(XmlAccessType.FIELD)
public class PurchaseInvoiceGoods extends BasicEntity implements Serializable {  
    
    @ManyToOne
    @JoinColumn(name = "purchase_invoice_id")
    @XmlElement
    private PurchaseInvoice purchaseInvoice;
    
    @ManyToOne
    @JoinColumn(name = "goods_id")
    @XmlElement
    private Goods goods;
    
    @Column
    @XmlElement
    private int quantity;

    public PurchaseInvoice getPurchaseInvoice() {
        return purchaseInvoice;
    }

    public void setPurchaseInvoice(PurchaseInvoice purchaseInvoice) {
        this.purchaseInvoice = purchaseInvoice;
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