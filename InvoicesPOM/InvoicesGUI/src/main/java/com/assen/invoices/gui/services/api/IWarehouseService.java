package com.assen.invoices.gui.services.api;

import com.assen.invoices.gui.model.wrappers.WarehouseWrapper;
import javafx.collections.ObservableList;

/**
 *
 * @author Arek
 */
public interface IWarehouseService {

    ObservableList<WarehouseWrapper> getAllWarehouses();
}
