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
@Table(name = "vat_invoice_goods")
@XmlRootElement(name = "VATInvoiceGoods")
@XmlAccessorType(XmlAccessType.FIELD)
public class VATInvoiceGoods extends BasicEntity implements Serializable {


    @ManyToOne
    @JoinColumn(name = "vat_invoice_id")
    @XmlElement
    private VATInvoice VATInvoice;
    
    @ManyToOne
    @JoinColumn(name = "goods_id")
    @XmlElement
    private Goods goods;
    
    @Column
    @XmlElement
    private int quantity;

    public VATInvoice getVATInvoice() {
        return VATInvoice;
    }

    public void setVATInvoice(VATInvoice VATInvoice) {
        this.VATInvoice = VATInvoice;
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
