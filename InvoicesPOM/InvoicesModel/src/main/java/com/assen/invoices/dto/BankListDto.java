/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assen.invoices.dto;

import com.assen.invoices.entities.Bank;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author horbana
 */
@XmlRootElement(name = "bankList")
@XmlAccessorType(XmlAccessType.FIELD)
public class BankListDto {

    @XmlElement(name = "bank")
    private List<Bank> list;

    public BankListDto() {
    }

    public BankListDto(List<Bank> list) {
        this.list = list;
    }

    public List<Bank> getList() {
        return list;
    }

    public void setList(List<Bank> list) {
        this.list = list;
    }
    
    
}
