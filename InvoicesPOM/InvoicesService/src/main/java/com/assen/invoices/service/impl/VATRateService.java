package com.assen.invoices.service.impl;

import com.assen.invoices.dao.api.IVATRateDao;
import com.assen.invoices.entities.VATRate;
import com.assen.invoices.service.api.IVATRateService;
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
public class VATRateService implements IVATRateService {

    private static final Logger logger = LoggerFactory.getLogger(GroupService.class);
    
    @EJB
    private IVATRateDao vatRateDao;

    @Override
    public List<VATRate> findAllVATRates() {
        return vatRateDao.selectAll();
    }
}
