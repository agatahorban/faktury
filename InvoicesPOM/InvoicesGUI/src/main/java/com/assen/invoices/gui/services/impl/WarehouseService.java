package com.assen.invoices.gui.services.impl;

import com.assen.invoices.dto.WarehouseListDto;
import com.assen.invoices.entities.Warehouse;
import com.assen.invoices.gui.model.wrappers.WarehouseWrapper;
import com.assen.invoices.gui.services.api.IWarehouseService;
import com.assen.invoices.gui.utils.RestUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author chrobota
 */
public class WarehouseService implements IWarehouseService {

    private static final Logger logger = LoggerFactory.getLogger(WarehouseService.class);

    @Inject
    private RestUtil restUtil;

    @Override
    public ObservableList<WarehouseWrapper> getAllWarehouses() {
        WarehouseListDto warehouseListDto = new WarehouseListDto();

        Client client = restUtil.getAuthorizedClient();
        ClientResponse response = RestUtil.generateRestGet(client, "warehouses/all");

        if (RestUtil.responseHasErrors(response)) {
            logger.error("Error getting all warehouses from database. Error status: "
                    + response.getClientResponseStatus().getStatusCode());
        } else {
            logger.info("Populating all warehouses from database");
            warehouseListDto = response.getEntity(WarehouseListDto.class);
        }

        ObservableList<WarehouseWrapper> result = FXCollections.observableArrayList();

        for (Warehouse warehouse : warehouseListDto.getWarehouses()) {
            result.add(new WarehouseWrapper(warehouse));
        }

        return result;
    }

    @Override
    public boolean deleteData(List<WarehouseWrapper> warehousesToDelete) {
        logger.info("Deleting " + warehousesToDelete.size() + " warehouse records.");

        List<Warehouse> warehouseDelete = new ArrayList<>();
        warehousesToDelete.stream().parallel().forEach((warehouseToDelete) -> {
            warehouseDelete.add(warehouseToDelete.getWarehouse());
        });

        Client client = restUtil.getAuthorizedClient();

        WarehouseListDto warehouseDto = new WarehouseListDto();
        warehouseDto.setWarehouses(warehouseDelete);
        ClientResponse response = RestUtil.generateRestPost(client,
                "warehouses/delete", warehouseDto);

        if (RestUtil.responseHasErrors(response)) {
            logger.error("Error deleting warehosues from database.");
            return false;
        }
        return true;
    }

}
