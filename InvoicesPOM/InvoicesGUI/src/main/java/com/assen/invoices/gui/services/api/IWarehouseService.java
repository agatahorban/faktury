package com.assen.invoices.gui.services.api;

import com.assen.invoices.gui.model.wrappers.WarehouseWrapper;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Arek
 */
public interface IWarehouseService {

    ObservableList<WarehouseWrapper> getAllWarehouses();

    boolean deleteData(List<WarehouseWrapper> warehousesToDelete);
}
