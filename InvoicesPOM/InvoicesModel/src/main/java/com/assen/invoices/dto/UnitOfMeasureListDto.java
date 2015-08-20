package com.assen.invoices.dto;

import com.assen.invoices.entities.UnitOfMeasure;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Arek
 */
@XmlRootElement(name = "unitOfMeasureList")
@XmlAccessorType(XmlAccessType.FIELD)
public class UnitOfMeasureListDto {

    @XmlElement(name = "unit")
    private List<UnitOfMeasure> units;

    public UnitOfMeasureListDto() {
        this.units = new ArrayList<>();
    }

    public List<UnitOfMeasure> getUnits() {
        return units;
    }

    public void setUnits(List<UnitOfMeasure> units) {
        this.units = units;
    }
}
