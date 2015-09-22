package com.assen.invoices.service.api;

import com.assen.invoices.entities.Warehouse;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Arek
 */
@Local
public interface IWarehouseService {
    
    List<Warehouse> findAllWarehouses();
    Warehouse insertNewWarehouse(Warehouse warehouse);
    Warehouse updateWarehouse(Warehouse warehouse);
}
