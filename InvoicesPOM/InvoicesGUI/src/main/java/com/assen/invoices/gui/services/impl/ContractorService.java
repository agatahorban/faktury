/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assen.invoices.gui.services.impl;

import com.assen.invoices.dto.BankListDto;
import com.assen.invoices.dto.ContractorListDto;
import com.assen.invoices.dto.PaymentDateListDto;
import com.assen.invoices.entities.Bank;
import com.assen.invoices.entities.Contractor;
import com.assen.invoices.entities.PaymentDate;
import com.assen.invoices.gui.model.wrappers.ContractorWrapper;
import com.assen.invoices.gui.services.api.IContractorService;
import com.assen.invoices.gui.utils.ResettableCountDownLatch;
import com.assen.invoices.gui.utils.RestUtil;
import com.assen.invoices.gui.validators.ContractorValidator;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Agata
 */
public class ContractorService implements IContractorService {

    private static final Logger logger = LoggerFactory.getLogger(ContractorService.class);

    private final ResettableCountDownLatch latch;
    private static final int LATCH_COUNT = 3;

    private Map<String, Bank> banks;
    private Map<String, PaymentDate> paymentDates;
    private Map<String, String> provinces;

    @Inject
    private RestUtil restUtil;

    public ContractorService() {
        this.latch = new ResettableCountDownLatch(LATCH_COUNT);
    }

    @Override
    public boolean deleteData(List<ContractorWrapper> contractorsToDelete) {
        logger.info("Deleting " + contractorsToDelete.size() + " contractors records.");
        List<Contractor> contractorsDelete = new ArrayList<>();
        contractorsToDelete.stream().parallel().forEach((contractorsToDelete1) -> {
            contractorsDelete.add(contractorsToDelete1.getContractor());
        });

        Client client = restUtil.getAuthorizedClient();

        ContractorListDto entitiesToDelete = new ContractorListDto(contractorsDelete);
        ClientResponse response = RestUtil.generateRestPost(client,
                "contractors/delete", entitiesToDelete);
        if (RestUtil.responseHasErrors(response)) {
            logger.error("Error deleting contractors from database.");
            return false;
        }
        return true;
    }

    @Override
    public void populateAddContractorDataFromServer() {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        banks = new HashMap<>();
        paymentDates = new HashMap<>();
        provinces = new HashMap<>();

        Client client = restUtil.getAuthorizedClient();

        executor.execute(populateDataFromRest(client, "banks/all",
                DataType.BANKS));
        executor.execute(populateDataFromRest(client, "paymentDates/all",
                DataType.PAYMENT_DATES));

        latch.countDown();
        try {
            latch.await();
        } catch (InterruptedException ex) {
            logger.error("Thread interuppted while waiting for latch to count down.");
        }

        latch.reset();
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

    private Runnable populateDataFromRest(Client client, String url, DataType type) {
        return () -> {
            ClientResponse response = RestUtil.generateRestGet(client, url);
            if (RestUtil.responseHasErrors(response)) {
                logger.error("Error executing " + url + " from server. Error status: "
                        + response.getClientResponseStatus().getStatusCode());
            } else {
                getRestEntity(response, type);
            }
            latch.countDown();
        };
    }

    @Override
    public String validData(ContractorWrapper contractor, ContractorValidator.ContractorValidationData validationData) {
        ContractorValidator validator = new ContractorValidator();
        ContractorValidator.ContractorMapReferences mapReferences;
        mapReferences = new ContractorValidator.ContractorMapReferences(banks, paymentDates);
        return validator.validateData(contractor, validationData, mapReferences);
    }

    @Override
    public ObservableList<String> getObservableData(DataType type) {
        ObservableList<String> obsList = FXCollections.observableArrayList();
        switch (type) {
            case BANKS:
                banks.entrySet().stream().parallel().forEach((entrySet) -> {
                    obsList.add(entrySet.getKey());
                });
                break;
            case PAYMENT_DATES:
                paymentDates.entrySet().stream().parallel().forEach((entrySet) -> {
                    obsList.add(entrySet.getKey());
                });
                break;
//            case PROVINCES:
//                provinces.entrySet().stream().parallel().forEach((entrySet) -> {
//                    obsList.add(entrySet.getKey());
//                });
        }
        return obsList;
    }

    private void getRestEntity(ClientResponse response, DataType type) {
        switch (type) {
            case BANKS:
                BankListDto bankDto
                        = response.getEntity(BankListDto.class);

                bankDto.getList().stream().parallel().forEach((aBank) -> {
                    banks.put(aBank.getBankName(), aBank);
                });
                break;
            case PAYMENT_DATES:
                PaymentDateListDto dateDto
                        = response.getEntity(PaymentDateListDto.class);

                dateDto.getList().stream().parallel().forEach((pDate) -> {
                    paymentDates.put(pDate.getDescription(), pDate);
                });
                break;
//            case PROVINCES:
//                List<String> provinceList = ContractorValidator.provinceList;
//                provinceList.stream().parallel().forEach((prov) -> {
//                    provinces.put(prov, prov);
//                });

        }
    }

    @Override
    public ObservableList<ContractorWrapper> populateAllContractors() {
        ContractorListDto contractorListDto = new ContractorListDto();
        Client client = restUtil.getAuthorizedClient();

        ClientResponse response = RestUtil.generateRestGet(client, "contractors/all");
        if (RestUtil.responseHasErrors(response)) {
            logger.error("Error getting all contractors from database. Error status: "
                    + response.getClientResponseStatus().getStatusCode());
        } else {
            logger.info("Populating all contractors from database.");

            contractorListDto = response.getEntity(ContractorListDto.class);
        }
        ObservableList<ContractorWrapper> obsContractors = FXCollections.observableArrayList();
        for (Contractor contractor : contractorListDto.getList()) {
            ContractorWrapper contractorWrapper = new ContractorWrapper(contractor);
            obsContractors.add(contractorWrapper);
        }
        return obsContractors;
    }

    @Override
    public ObservableList<ContractorWrapper> filterByCutName(String cutName) {
        ObservableList<ContractorWrapper> result = FXCollections.observableArrayList();
        Client client = restUtil.getAuthorizedClient();

        ClientResponse response = RestUtil.generateRestPost(client, "contractors/findByCutName", cutName);

        if (RestUtil.responseHasErrors(response)) {
            logger.error("Cutname not found in database: " + cutName);
        } else {
            Contractor filteredRecord = response.getEntity(Contractor.class);
            ContractorWrapper record = new ContractorWrapper(filteredRecord);
            result.add(record);
        }
        return result;
    }
}
