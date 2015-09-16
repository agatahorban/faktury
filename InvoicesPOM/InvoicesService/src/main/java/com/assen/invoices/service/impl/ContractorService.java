/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assen.invoices.service.impl;

import com.assen.invoices.dao.api.IAddressDao;
import com.assen.invoices.dao.api.IContractorDao;
import com.assen.invoices.dto.ContractorListDto;
import com.assen.invoices.entities.Contractor;
import com.assen.invoices.service.api.IContractorService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Agata
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ContractorService implements IContractorService {

    private static final Logger logger = LoggerFactory.getLogger(ContractorService.class);
    @EJB
    private IContractorDao contractorDao;

    @EJB
    private IAddressDao addressDao;

    @Override
    public List<Contractor> findAllContractors() {
        return contractorDao.selectAll();
    }

    @Override
    public Contractor insertNewContractors(Contractor contractor) {
        try {
            addressDao.insert(contractor.getAddress());
            contractor = contractorDao.insert(contractor);
        } catch (Exception ex) {
            logger.error("Error adding new contractor to database. Error message: " + ex.getMessage());
            return null;
        }
        return contractor;
    }

    @Override
    public Contractor updateContractors(Contractor contractor) {
        try {
            addressDao.update(contractor.getAddress());
            contractor = contractorDao.update(contractor);
        } catch (Exception ex) {
            logger.error("Error updating contractor data. Contractor: " + contractor.getId()
                    + ", error message: " + ex.getMessage());
            return null;
        }
        return contractor;
    }

    @Override
    public boolean removeContractors(ContractorListDto contractorList) {
        try {
            contractorList.getList().stream().parallel().forEach((contractorsToDelete) -> {
                contractorDao.remove(contractorsToDelete);
            });
        } catch (Exception ex) {
            logger.error("Error deleting contractor from database. Error message: "
                    + ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Contractor findByCutName(String cutName) {
        List<Contractor> filteredContractors = contractorDao.findByCutName(cutName);
        if (filteredContractors.isEmpty()) {
            return null;
        }
        return filteredContractors.get(0);
    }

}
