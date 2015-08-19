/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assen.invoices.service.impl;

import com.assen.invoices.dao.api.IContractorDao;
import com.assen.invoices.entities.Contractor;
import com.assen.invoices.service.api.IContractorService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Agata
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ContractorService implements IContractorService {

    @EJB
    private IContractorDao contractorDao;

    @Override
    public List<Contractor> findAllContractors() {
        return contractorDao.selectAll();
    }

}
