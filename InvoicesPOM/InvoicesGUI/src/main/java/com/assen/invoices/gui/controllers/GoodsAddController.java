package com.assen.invoices.gui.controllers;

import com.assen.invoices.dto.CollectivePackageListDto;
import com.assen.invoices.dto.GroupListDto;
import com.assen.invoices.dto.UnitOfMeasureListDto;
import com.assen.invoices.dto.VATRateListDto;
import com.assen.invoices.entities.CollectivePackage;
import com.assen.invoices.entities.Contractor;
import com.assen.invoices.entities.Group;
import com.assen.invoices.entities.UnitOfMeasure;
import com.assen.invoices.entities.VATRate;
import com.assen.invoices.gui.utils.RestUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class
 *
 * @author Arek
 */
public class GoodsAddController implements Initializable {

    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

    private Stage stage;

    private CountDownLatch latch;
    private static final int LATCH_COUNT = 6;

    private Map<String, CollectivePackage> collectivePackages;
    private Map<String, Group> groups;
    private Map<String, VATRate> vatRates;
    private Map<String, Contractor> contractors;
    private Map<String, UnitOfMeasure> unitsOfMeasure;

    @Inject
    private RestUtil restUtil;

    public GoodsAddController() {
        latch = new CountDownLatch(LATCH_COUNT);
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void populateReferencedData() {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        collectivePackages = new HashMap<>();
        groups = new HashMap<>();
        vatRates = new HashMap<>();
        contractors = new HashMap<>();
        unitsOfMeasure = new HashMap<>();

        Client client = restUtil.getAuthorizedClient();

        executor.execute(populateCollectivePackages(client));
        executor.execute(populateGroups(client));
        executor.execute(populateVatRates(client));
        executor.execute(populateUnitsOfMeasure(client));
        //TODO populate contractors
        executor.execute(populateContractors(client));
        
        latch.countDown();
        
        try {
            latch.await();
        } catch (InterruptedException ex) {
            logger.error("Thread interuppted while waiting for latch to count down.");
        }
        
        //TODO converting to observableCollections

        try {
            executor.shutdown();
            executor.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            logger.error("Termination interrupted");
        } finally {
            if (!executor.isTerminated()) {
                logger.error("Killing non-finished tasks");
            }
            executor.shutdownNow();
        }
    }

    private Runnable populateCollectivePackages(Client client) {
        return () -> {
            ClientResponse response = RestUtil.generateRestGetResponse(client, "collectivePackages/all");
            if (RestUtil.responseHasErrors(response)) {
                logger.error("Error getting all collectivePackages from database. Error status: "
                        + response.getClientResponseStatus().getStatusCode());
            } else {
                CollectivePackageListDto packagesDto = response.getEntity(CollectivePackageListDto.class);

                packagesDto.getPackages().stream().parallel().forEach((aPackage) -> {
                    collectivePackages.put(aPackage.getCutName(), aPackage);
                });
            }
            latch.countDown();
        };
    }
    
    private Runnable populateGroups(Client client) {
        return () -> {
            ClientResponse response = RestUtil.generateRestGetResponse(client, "groups/all");
            if (RestUtil.responseHasErrors(response)) {
                logger.error("Error getting all groups from database. Error status: "
                        + response.getClientResponseStatus().getStatusCode());
            } else {
                GroupListDto groupsDto = response.getEntity(GroupListDto.class);

                groupsDto.getGroups().stream().parallel().forEach((group) -> {
                    groups.put(group.getName(), group);
                });
            }
            latch.countDown();
        };
    }
    
    private Runnable populateVatRates(Client client) {
        return () -> {
            ClientResponse response = RestUtil.generateRestGetResponse(client, "vatRates/all");
            if (RestUtil.responseHasErrors(response)) {
                logger.error("Error getting all vatRates from database. Error status: "
                        + response.getClientResponseStatus().getStatusCode());
            } else {
                VATRateListDto vatRatesDto = response.getEntity(VATRateListDto.class);

                vatRatesDto.getVatRates().stream().parallel().forEach((vatRate) -> {
                    vatRates.put(vatRate.getName(), vatRate);
                });
            }
            latch.countDown();
        };
    }
    
    private Runnable populateUnitsOfMeasure(Client client) {
        return () -> {
            ClientResponse response = RestUtil.generateRestGetResponse(client, "unitsOfMeasure/all");
            if (RestUtil.responseHasErrors(response)) {
                logger.error("Error getting all unitsOfMeasure groups from database. Error status: "
                        + response.getClientResponseStatus().getStatusCode());
            } else {
                UnitOfMeasureListDto unitsDto = response.getEntity(UnitOfMeasureListDto.class);

                unitsDto.getUnits().stream().parallel().forEach((unit) -> {
                    unitsOfMeasure.put(unit.getName(), unit);
                });
            }
            latch.countDown();
        };
    }
    
    private Runnable populateContractors(Client client) {
        return () -> {
//            ClientResponse response = RestUtil.generateRestGetResponse(client, "unitsOfMeasure/all");
//            if (RestUtil.responseHasErrors(response)) {
//                logger.error("Error getting all unitsOfMeasure groups from database. Error status: "
//                        + response.getClientResponseStatus().getStatusCode());
//            } else {
//                UnitOfMeasureListDto unitsDto = response.getEntity(UnitOfMeasureListDto.class);
//
//                unitsDto.getUnits().stream().parallel().forEach((unit) -> {
//                    unitsOfMeasure.put(unit.getName(), unit);
//                });
//            }
            latch.countDown();
        };
    }
}
