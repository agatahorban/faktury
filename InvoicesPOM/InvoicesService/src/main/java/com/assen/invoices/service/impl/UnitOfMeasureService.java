package com.assen.invoices.service.impl;

import com.assen.invoices.dao.api.IUnitOfMeasureDao;
import com.assen.invoices.entities.UnitOfMeasure;
import com.assen.invoices.service.api.IUnitOfMeasureService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Arek
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class UnitOfMeasureService implements IUnitOfMeasureService {
    
    private static final Logger logger = LoggerFactory.getLogger(CollectivePackageService.class);
    
    @EJB
    private IUnitOfMeasureDao unitOfMeasureDao;

    @Override
    public List<UnitOfMeasure> findAllUnitsOfMeasure() {
        return unitOfMeasureDao.selectAll();
    }

}
