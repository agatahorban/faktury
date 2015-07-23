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
@Table(name = "external_income_goods")
@XmlRootElement(name = "externalIncomeGoods")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExternalIncomeGoods extends BasicEntity implements Serializable {
    
    @ManyToOne
    @JoinColumn(name = "external_income_id")
    @XmlElement
    private ExternalIncome externalIncome;
    
    @ManyToOne
    @JoinColumn(name = "goods_id")
    @XmlElement
    private Goods goods;
    
    @Column
    @XmlElement
    private int quantity;

    public ExternalIncome getExternalIncome() {
        return externalIncome;
    }

    public void setExternalIncome(ExternalIncome externalIncome) {
        this.externalIncome = externalIncome;
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
