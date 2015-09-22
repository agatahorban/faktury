package com.assen.invoices.service.impl;

import com.assen.invoices.dao.api.IWarehouseDao;
import com.assen.invoices.entities.Warehouse;
import com.assen.invoices.service.api.IWarehouseService;
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
public class WarehouseService implements IWarehouseService {

    private static final Logger logger = LoggerFactory.getLogger(WarehouseService.class);

    @EJB
    private IWarehouseDao warehouseDao;

    @Override
    public List<Warehouse> findAllWarehouses() {
        logger.info("Returning all warehouses from database.");
        return warehouseDao.selectAll();
    }

    @Override
    public Warehouse insertNewWarehouse(Warehouse warehouse) {
        logger.info("Inserting new warehouse to database.");
        return warehouseDao.insert(warehouse);
    }

    @Override
    public Warehouse updateWarehouse(Warehouse warehouse) {
        logger.info("Updating data of warehouse: " + warehouse.getName()
                + ", id: " + warehouse.getId());
        return warehouseDao.update(warehouse);
    }
}
