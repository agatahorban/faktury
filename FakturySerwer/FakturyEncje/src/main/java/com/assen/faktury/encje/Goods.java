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
 * @author Agata
 */
@Entity
@Table(name = "goods")
public class Goods extends BasicEntity implements Serializable {

    @Column
    private String index1;

    @Column
    private String index2;

    @Column(name = "is_price_higher")
    private boolean priceHigher;

    @Column
    private double price;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "collective_package_id")
    private CollectivePackage collectivePackage;

    @ManyToOne
    @JoinColumn(name = "vat_rate_id")
    private VATRate rate;

    @ManyToOne
    @JoinColumn(name = "unit_of_measure_id")
    private UnitOfMeasure unit;
    
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Contractor supplier;
    
    @Column
    private int quantity;

    public String getIndex1() {
        return index1;
    }

    public void setIndex1(String index1) {
        this.index1 = index1;
    }

    public String getIndex2() {
        return index2;
    }

    public void setIndex2(String index2) {
        this.index2 = index2;
    }

    public boolean isPriceHigher() {
        return priceHigher;
    }

    public void setPriceHigher(boolean priceHigher) {
        this.priceHigher = priceHigher;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public CollectivePackage getCollectivePackage() {
        return collectivePackage;
    }

    public void setCollectivePackage(CollectivePackage collectivePackage) {
        this.collectivePackage = collectivePackage;
    }

    public VATRate getRate() {
        return rate;
    }

    public void setRate(VATRate rate) {
        this.rate = rate;
    }

    public UnitOfMeasure getUnit() {
        return unit;
    }

    public void setUnit(UnitOfMeasure unit) {
        this.unit = unit;
    }

    public Contractor getSupplier() {
        return supplier;
    }

    public void setSupplier(Contractor supplier) {
        this.supplier = supplier;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    

}
