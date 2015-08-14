package com.assen.invoices.gui.model.wrappers;

import com.assen.invoices.entities.CollectivePackage;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Arek
 */
public class CollectivePackageWrapper {

    private CollectivePackage collectivePackage;
    
    private StringProperty cutName;
    private StringProperty fullName;
    private IntegerProperty capacity;
    private DoubleProperty weight;
    private DoubleProperty width;
    private DoubleProperty height;
    private DoubleProperty depth;

    public CollectivePackageWrapper(CollectivePackage collectivePackage) {
        this.collectivePackage = collectivePackage;
        setWrapperValues();
    }

    public CollectivePackage getCollectivePackage() {
        getWrapperValues();
        return collectivePackage;
    }

    public void setCollectivePackage(CollectivePackage collectivePackage) {
        this.collectivePackage = collectivePackage;
        setWrapperValues();
    }

    public String getCutName() {
        return cutName.get();
    }

    public void setCutName(String cutName) {
        this.cutName.set(cutName);
    }

    public String getFullName() {
        return fullName.get();
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    public Integer getCapacity() {
        return capacity.get();
    }

    public void setCapacity(Integer capacity) {
        this.capacity.set(capacity);
    }

    public Double getWeight() {
        return weight.get();
    }

    public void setWeight(Double weight) {
        this.weight.set(weight);
    }

    public Double getWidth() {
        return width.get();
    }

    public void setWidth(Double width) {
        this.width.set(width);
    }

    public Double getHeight() {
        return height.get();
    }

    public void setHeight(Double height) {
        this.height.set(height);
    }

    public Double getDepth() {
        return depth.get();
    }

    public void setDepth(Double depth) {
        this.depth.set(depth);
    }
    
    public IntegerProperty capacityProperty() {
        return capacity;
    }
    
    public StringProperty cutNameProperty() {
        return cutName;
    }
    
    public DoubleProperty depthProperty() {
        return depth;
    }
    
    public StringProperty fullNameProperty() {
        return fullName;
    }
    
    public DoubleProperty heightProperty() {
        return height;
    }
    
    public DoubleProperty weightProperty() {
        return weight;
    }
    
    public DoubleProperty widthProperty() {
        return width;
    }
    
    private void setWrapperValues() {
        this.capacity = new SimpleIntegerProperty(collectivePackage.getCapacity());
        this.cutName = new SimpleStringProperty(collectivePackage.getCutName());
        this.depth = new SimpleDoubleProperty(collectivePackage.getDepth());
        this.fullName = new SimpleStringProperty(collectivePackage.getFullName());
        this.height = new SimpleDoubleProperty(collectivePackage.getHeight());
        this.weight = new SimpleDoubleProperty(collectivePackage.getWeight());
        this.width = new SimpleDoubleProperty(collectivePackage.getWidth());
    }
    
    private void getWrapperValues() {
        collectivePackage.setCapacity(getCapacity());
        collectivePackage.setCutName(getCutName());
        collectivePackage.setDepth(getDepth());
        collectivePackage.setFullName(getFullName());
        collectivePackage.setHeight(getHeight());
        collectivePackage.setWeight(getWeight());
        collectivePackage.setWidth(getWidth());
    }
}
