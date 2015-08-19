package com.assen.invoices.gui.model.wrappers;

import com.assen.invoices.entities.Goods;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Arek
 */
public class GoodsWrapper {

    private Goods goods;
    
    private StringProperty index1;
    private StringProperty index2;
    private StringProperty name;
    private BooleanProperty priceHigher;
    private DoubleProperty price;
    private GroupWrapper groupWrapper;
    private CollectivePackageWrapper collectivePackageWrapper;
    private VATRateWrapper vatRateWrapper;
    private ContractorWrapper supplierWrapper;
    private UnitOfMeasureWrapper unitOfMeasureWrapper;
    private IntegerProperty quantity;

    public GoodsWrapper(Goods goods) {
        this.goods = goods;
        setWrapperValues();
    }

    public Goods getGoods() {
        getWrapperValues();
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
        setWrapperValues();
    }

    public String getIndex1() {
        return index1.get();
    }

    public void setIndex1(String index1) {
        this.index1.set(index1);
    }

    public String getIndex2() {
        return index2.get();
    }

    public void setIndex2(String index2) {
        this.index2.set(index2);
    }
    
    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public Boolean isPriceHigher() {
        return priceHigher.get();
    }

    public void setPriceHigher(Boolean priceHigher) {
        this.priceHigher.set(priceHigher);
    }

    public Double getPrice() {
        return price.get();
    }

    public void setPrice(Double price) {
        this.price.set(price);
    }

    public GroupWrapper getGroupWrapper() {
        return groupWrapper;
    }

    public void setGroupWrapper(GroupWrapper groupWrapper) {
        this.groupWrapper = groupWrapper;
    }

    public CollectivePackageWrapper getCollectivePackageWrapper() {
        return collectivePackageWrapper;
    }

    public void setCollectivePackageWrapper(CollectivePackageWrapper collectivePackageWrapper) {
        this.collectivePackageWrapper = collectivePackageWrapper;
    }

    public VATRateWrapper getVatRateWrapper() {
        return vatRateWrapper;
    }

    public void setVatRateWrapper(VATRateWrapper vatRateWrapper) {
        this.vatRateWrapper = vatRateWrapper;
    }

    public ContractorWrapper getSupplierWrapper() {
        return supplierWrapper;
    }

    public void setSupplierWrapper(ContractorWrapper supplierWrapper) {
        this.supplierWrapper = supplierWrapper;
    }

    public UnitOfMeasureWrapper getUnitOfMeasureWrapper() {
        return unitOfMeasureWrapper;
    }

    public void setUnitOfMeasureWrapper(UnitOfMeasureWrapper unitOfMeasureWrapper) {
        this.unitOfMeasureWrapper = unitOfMeasureWrapper;
    }
    
    public Integer getQuantity() {
        return quantity.get();
    }

    public void setQuantity(Integer quantity) {
        this.quantity.set(quantity);
    }
    
    public StringProperty index1Property() {
        return index1;
    }
    
    public StringProperty index2Property() {
        return index2;
    }
    
    public StringProperty nameProperty() {
        return name;
    }
    
    public BooleanProperty priceHigherProperty() {
        return priceHigher;
    }
    
    public DoubleProperty priceProperty() {
        return price;
    }
    
    public IntegerProperty quantityProperty() {
        return quantity;
    }
    
    private void setWrapperValues() {
        index1 = new SimpleStringProperty(goods.getIndex1());
        index2 = new SimpleStringProperty(goods.getIndex2());
        priceHigher = new SimpleBooleanProperty(goods.isPriceHigher());
        price = new SimpleDoubleProperty(goods.getPrice());
        groupWrapper = new GroupWrapper(goods.getGroup());
        collectivePackageWrapper = new CollectivePackageWrapper(goods.getCollectivePackage());
        vatRateWrapper = new VATRateWrapper(goods.getRate());
        supplierWrapper = new ContractorWrapper(goods.getSupplier());
        quantity = new SimpleIntegerProperty(goods.getQuantity());
        unitOfMeasureWrapper = new UnitOfMeasureWrapper(goods.getUnit());
        name =  new SimpleStringProperty(goods.getName());
    }
    
    private void getWrapperValues() {
        goods.setIndex1(getIndex1());
        goods.setIndex2(getIndex2());
        goods.setCollectivePackage(getCollectivePackageWrapper().getCollectivePackage());
        goods.setGroup(getGroupWrapper().getGroup());
        goods.setPrice(getPrice());
        goods.setPriceHigher(isPriceHigher());
        goods.setQuantity(getQuantity());
        goods.setRate(getVatRateWrapper().getVatRate());
        goods.setSupplier(getSupplierWrapper().getContractor());
        goods.setUnit(getUnitOfMeasureWrapper().getUnitOfMeasure());
        goods.setName(getName());
    }    
}
