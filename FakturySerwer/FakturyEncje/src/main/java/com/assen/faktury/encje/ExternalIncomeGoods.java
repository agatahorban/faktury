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

/**
 *
 * @author Arek
 */
@Entity
@Table(name = "external_income_goods")
public class ExternalIncomeGoods extends BasicEntity implements Serializable {
    
    @ManyToOne
    @JoinColumn(name = "external_income_id")
    private ExternalIncome externalIncome;
    
    @ManyToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;
    
    @Column
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
