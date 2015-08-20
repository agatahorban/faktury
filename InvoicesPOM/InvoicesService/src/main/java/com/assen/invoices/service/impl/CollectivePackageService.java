package com.assen.invoices.service.impl;

import com.assen.invoices.dao.api.ICollectivePackageDao;
import com.assen.invoices.entities.CollectivePackage;
import com.assen.invoices.service.api.ICollectivePackageService;
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
public class CollectivePackageService implements ICollectivePackageService{
    
    private static final Logger logger = LoggerFactory.getLogger(CollectivePackageService.class);
    
    @EJB
    private ICollectivePackageDao collectivePackageDao;

    @Override
    public List<CollectivePackage> findAllCollectivePackages() {
        return collectivePackageDao.selectAll();
    }

}
