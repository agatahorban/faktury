package com.assen.invoices.gui.services.impl;

import com.assen.invoices.dto.UnitOfMeasureListDto;
import com.assen.invoices.entities.UnitOfMeasure;
import com.assen.invoices.gui.model.wrappers.UnitOfMeasureWrapper;
import com.assen.invoices.gui.services.api.IUnitOfMeasureService;
import com.assen.invoices.gui.utils.RestUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author horbana
 */
public class UnitOfMeasureService implements IUnitOfMeasureService {

    private static final Logger logger = LoggerFactory.getLogger(UnitOfMeasureService.class);
    @Inject
    private RestUtil restUtil;

    @Override
    public ObservableList<UnitOfMeasureWrapper> populateAllUnits() {
        UnitOfMeasureListDto unitOfMeasureListDto = new UnitOfMeasureListDto();
        Client client = restUtil.getAuthorizedClient();

        ClientResponse response = RestUtil.generateRestGet(client, "unitsOfMeasure/all");

        if (RestUtil.responseHasErrors(response)) {
            logger.error("Error getting all units of measure from database. Error status: "
                    + response.getClientResponseStatus().getStatusCode());
        } else {
            logger.info("Populating all units of measure from database.");

            unitOfMeasureListDto = response.getEntity(UnitOfMeasureListDto.class);
        }

        ObservableList<UnitOfMeasureWrapper> obsUnits = FXCollections.observableArrayList();
        for (UnitOfMeasure unitOfMeasure : unitOfMeasureListDto.getUnits()) {
            UnitOfMeasureWrapper unitOfMeasureWrapper = new UnitOfMeasureWrapper(unitOfMeasure);
            obsUnits.add(unitOfMeasureWrapper);
        }

        return obsUnits;
    }

}
