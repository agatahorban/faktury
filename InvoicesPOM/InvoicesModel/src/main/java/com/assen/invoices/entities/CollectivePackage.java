package com.assen.invoices.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Agata
 */
@Entity
@Table(name = "collective_package")
public class CollectivePackage extends BasicEntity implements Serializable {

    @Column(name = "cut_name")
    @Size(max = 10)
    private String cutName;

    @Column(name = "full_name")
    private String fullName;

    @Column
    private int capacity;

    @Column
    private double weight;

    @Column
    private double width;

    @Column
    private double height;

    @Column
    private double depth;

    public String getCutName() {
        return cutName;
    }

    public void setCutName(String cutName) {
        this.cutName = cutName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    
  
}
