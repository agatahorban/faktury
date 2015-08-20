package com.assen.invoices.dto;

import com.assen.invoices.entities.CollectivePackage;
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
@XmlRootElement(name = "collectivePackageList")
@XmlAccessorType(XmlAccessType.FIELD)
public class CollectivePackageListDto {

    @XmlElement(name = "collectivePackage")
    private List<CollectivePackage> packages;

    public CollectivePackageListDto() {
        this.packages = new ArrayList<>();
    }

    public List<CollectivePackage> getPackages() {
        return packages;
    }

    public void setPackages(List<CollectivePackage> packages) {
        this.packages = packages;
    }
}
