/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assen.invoices.dto;

import com.assen.invoices.entities.BasicEntity;
import com.assen.invoices.entities.Contractor;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Agatka
 */
@XmlRootElement(name = "contractorList")
@XmlAccessorType(XmlAccessType.FIELD)
public class ContractorListDto {

    @XmlElement(name = "contractor")
    private List<Contractor> list;

    public ContractorListDto() {
    }
    
    public ContractorListDto(List<Contractor> list) {
        this.list = list;
    }

    public List<Contractor> getList() {
        return list;
    }

    public void setList(List<Contractor> list) {
        this.list = list;
    }
}
