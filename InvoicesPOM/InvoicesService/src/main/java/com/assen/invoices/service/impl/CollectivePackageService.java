package com.assen.invoices.service.impl;

import com.assen.invoices.dao.api.ICollectivePackageDao;
import com.assen.invoices.dto.CollectivePackageListDto;
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

    @Override
    public boolean removeCollectivePackage(CollectivePackageListDto collectivePackageToDelete) {
           logger.info("Deleting collective packages from database: " + collectivePackageToDelete.getPackages().size());
        try {
            collectivePackageToDelete.getPackages().stream().parallel().forEach((collectivePackageToDeletee) -> {
                collectivePackageDao.remove(collectivePackageToDeletee);
            });
        } catch (Exception ex) {
            logger.error("Error deleting unit of measure from database. Error message: "
                    + ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public CollectivePackage insertNewCollectivePackage(CollectivePackage collectivePackage) {
        logger.info("Inserting new collective packages to database: " + collectivePackage.getFullName());
        try {
            collectivePackage = collectivePackageDao.insert(collectivePackage);
        } catch (Exception ex) {
            logger.error("Error adding new collective package to database. Error message: " + ex.getMessage());
            return null;
        }
        return collectivePackage;
    }

    @Override
    public CollectivePackage updateCollectivePackage(CollectivePackage collectivePackage) {
             logger.info("Updating collective package values: " + collectivePackage.getFullName());
        try {
            collectivePackage = collectivePackageDao.update(collectivePackage);
        } catch (Exception ex) {
            logger.error("Error updating collective package data. Collective package: " + collectivePackage.getFullName()
                    + ", error message: " + ex.getMessage());
            return null;
        }
        return collectivePackage;
    }

}
