package com.assen.invoices.service.impl;

import com.assen.invoices.dao.api.IUnitOfMeasureDao;
import com.assen.invoices.dto.UnitOfMeasureListDto;
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

    @Override
    public boolean removeUnit(UnitOfMeasureListDto unitOfMeasureToDelete) {
         logger.info("Deleting goods from database: " + unitOfMeasureToDelete.getUnits().size());
        try {
            unitOfMeasureToDelete.getUnits().stream().parallel().forEach((unitOfMeasureToDeletee) -> {
                unitOfMeasureDao.remove(unitOfMeasureToDeletee);
            });
        } catch (Exception ex) {
            logger.error("Error deleting unit of measure from database. Error message: "
                    + ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public UnitOfMeasure insertNewUnit(UnitOfMeasure unit) {
        logger.info("Inserting new units to database: " + unit.getShortcut());
        try {
            unit = unitOfMeasureDao.insert(unit);
        } catch (Exception ex) {
            logger.error("Error adding new unit to database. Error message: " + ex.getMessage());
            return null;
        }
        return unit;
    }

    @Override
    public UnitOfMeasure updateUnit(UnitOfMeasure unit) {
         logger.info("Updating unit values: " + unit.getShortcut());
        try {
            unit = unitOfMeasureDao.update(unit);
        } catch (Exception ex) {
            logger.error("Error updating unit data. Unit: " + unit.getId()
                    + ", error message: " + ex.getMessage());
            return null;
        }
        return unit;
    }

}
