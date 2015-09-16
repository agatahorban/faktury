/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assen.invoices.service.impl;

import com.assen.invoices.dao.api.IPaymentDateDao;
import com.assen.invoices.entities.PaymentDate;
import com.assen.invoices.service.api.IPaymentDateService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author horbana
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class PaymentDateService implements IPaymentDateService {
    private static final Logger logger = LoggerFactory.getLogger(PaymentDateService.class);
    
    @EJB
    private IPaymentDateDao paymentDateDao;

    @Override
    public List<PaymentDate> findAllPaymentDates() {
        return paymentDateDao.selectAll();
    }
}
