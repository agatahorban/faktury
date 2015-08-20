package com.assen.invoices.dto;

import com.assen.invoices.entities.VATRate;
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
@XmlRootElement(name = "vatRateList")
@XmlAccessorType(XmlAccessType.FIELD)
public class VATRateListDto {

    @XmlElement(name = "vatRate")
    private List<VATRate> vatRates;

    public VATRateListDto() {
        this.vatRates = new ArrayList<>();
    }

    public List<VATRate> getVatRates() {
        return vatRates;
    }

    public void setVatRates(List<VATRate> vatRates) {
        this.vatRates = vatRates;
    }
}
