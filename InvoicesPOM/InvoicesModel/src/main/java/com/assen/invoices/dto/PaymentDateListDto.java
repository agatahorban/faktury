/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assen.invoices.dto;

import com.assen.invoices.entities.PaymentDate;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author horbana
 */
@XmlRootElement(name = "paymentDateList")
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentDateListDto {
    @XmlElement(name = "paymentDate")
    private List<PaymentDate> list;

    public PaymentDateListDto() {
    }

    public PaymentDateListDto(List<PaymentDate> list) {
        this.list = list;
    }

    public List<PaymentDate> getList() {
        return list;
    }

    public void setList(List<PaymentDate> list) {
        this.list = list;
    }
    
    
}
