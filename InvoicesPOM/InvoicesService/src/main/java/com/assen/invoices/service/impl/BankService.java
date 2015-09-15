/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assen.invoices.service.impl;

import com.assen.invoices.dao.api.IBankDao;
import com.assen.invoices.entities.Bank;
import com.assen.invoices.service.api.IBankService;
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
public class BankService implements IBankService {

    private static final Logger logger = LoggerFactory.getLogger(ContractorService.class);

    @EJB
    private IBankDao bankDao;

    @Override
    public List<Bank> findAllBanks() {
      return bankDao.selectAll();
    }

}
