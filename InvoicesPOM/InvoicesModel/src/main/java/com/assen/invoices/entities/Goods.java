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
 * @author Agata
 */
@Entity
@Table(name = "goods")
@XmlRootElement(name = "goods")
@XmlAccessorType(XmlAccessType.FIELD)
public class Goods extends BasicEntity implements Serializable {

    @Column
    @XmlElement
    private String index1;

    @Column
    @XmlElement
    private String index2;
    
    @Column(length = 100)
    @XmlElement
    private String name;

    @Column(name = "is_price_higher")
    @XmlElement
    private boolean priceHigher;

    @Column
    @XmlElement
    private double price;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @XmlElement
    private Group group;

    @ManyToOne
    @JoinColumn(name = "collective_package_id")
    @XmlElement
    private CollectivePackage collectivePackage;

    @ManyToOne
    @JoinColumn(name = "vat_rate_id")
    @XmlElement
    private VATRate rate;

    @ManyToOne
    @JoinColumn(name = "unit_of_measure_id")
    @XmlElement
    private UnitOfMeasure unit;
    
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    @XmlElement
    private Contractor supplier;
    
    @Column
    @XmlElement
    private int quantity;

    public Goods() {
        rate = new VATRate();
        unit = new UnitOfMeasure();
        supplier = new Contractor();
        collectivePackage = new CollectivePackage();
        group = new Group();
    }
    
    public static Goods copyOf(Goods goods) {
        Goods newGoods = new Goods();
        newGoods.setCollectivePackage(goods.getCollectivePackage());
        newGoods.setGroup(goods.getGroup());
        newGoods.setId(goods.getId());
        newGoods.setIndex1(goods.getIndex1());
        newGoods.setIndex2(goods.getIndex2());
        newGoods.setName(goods.getName());
        newGoods.setPrice(goods.getPrice());
        newGoods.setPriceHigher(goods.isPriceHigher());
        newGoods.setQuantity(goods.getQuantity());
        newGoods.setRate(goods.getRate());
        newGoods.setSupplier(goods.getSupplier());
        newGoods.setUnit(goods.getUnit());
        newGoods.setVersion(goods.getVersion());
        return newGoods;
    }
    
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
