package com.assen.faktury.encje;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "purchase_invoice_goods")
public class PurchaseInvoiceGoods extends BasicEntity implements Serializable {  
    
    @ManyToOne
    @JoinColumn(name = "purchase_invoice_id")
    private PurchaseInvoice purchaseInvoice;
    
    @ManyToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;
    
    @Column
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