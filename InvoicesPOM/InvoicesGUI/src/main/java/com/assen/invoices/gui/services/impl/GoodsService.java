package com.assen.invoices.gui.services.impl;

import com.assen.invoices.gui.services.api.IGoodsService;
import com.assen.invoices.dto.CollectivePackageListDto;
import com.assen.invoices.dto.ContractorListDto;
import com.assen.invoices.dto.GoodsListDto;
import com.assen.invoices.dto.GroupListDto;
import com.assen.invoices.dto.UnitOfMeasureListDto;
import com.assen.invoices.dto.VATRateListDto;
import com.assen.invoices.entities.CollectivePackage;
import com.assen.invoices.entities.Contractor;
import com.assen.invoices.entities.Goods;
import com.assen.invoices.entities.Group;
import com.assen.invoices.entities.UnitOfMeasure;
import com.assen.invoices.entities.VATRate;
import com.assen.invoices.gui.model.wrappers.GoodsWrapper;
import com.assen.invoices.gui.utils.ResettableCountDownLatch;
import com.assen.invoices.gui.utils.RestUtil;
import com.assen.invoices.gui.validators.GoodsValidator;
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
import javafx.scene.control.TreeItem;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Arek
 */
public class GoodsService implements IGoodsService {

    private static final Logger logger = LoggerFactory.getLogger(GoodsService.class);

    private final ResettableCountDownLatch latch;
    private static final int LATCH_COUNT = 6;
    public static final String CONTRACTORS_ROOT = "Kontrahenci";
    public static final String GROUP_ROOT = "Grupy";
    public static final String TREEVIEW_ROOT = "Wszyscy";

    private Map<String, CollectivePackage> collectivePackages;
    private Map<String, Group> groups;
    private Map<String, VATRate> vatRates;
    private Map<String, Contractor> contractors;
    private Map<String, UnitOfMeasure> unitsOfMeasure;

    @Inject
    private RestUtil restUtil;

    public GoodsService() {
        this.latch = new ResettableCountDownLatch(LATCH_COUNT);
    }

    @Override
    public void populateAddGoodsDataFromServer() {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        collectivePackages = new HashMap<>();
        groups = new HashMap<>();
        vatRates = new HashMap<>();
        contractors = new HashMap<>();
        unitsOfMeasure = new HashMap<>();

        Client client = restUtil.getAuthorizedClient();

        executor.execute(populateDataFromRest(client, "collectivePackages/all",
                DataType.COLLECTIVE_PACKAGES));
        executor.execute(populateDataFromRest(client, "contractors/all",
                DataType.CONTRACTORS));
        executor.execute(populateDataFromRest(client, "groups/all",
                DataType.GROUPS));
        executor.execute(populateDataFromRest(client, "unitsOfMeasure/all",
                DataType.UNITS_OF_MEASURE));
        executor.execute(populateDataFromRest(client, "vatRates/all",
                DataType.VAT_RATES));

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
    public String validData(GoodsWrapper goods,
            GoodsValidator.GoodsValidationData validationData) {
        GoodsValidator validator = new GoodsValidator();
        GoodsValidator.GoodsMapReferences mapReferences;
        mapReferences = new GoodsValidator.GoodsMapReferences(collectivePackages, groups,
                vatRates, contractors, unitsOfMeasure);

        return validator.validateData(goods, validationData, mapReferences);
    }

    @Override
    public ObservableList<String> getObservableData(DataType type) {
        ObservableList<String> obsList = FXCollections.observableArrayList();
        switch (type) {
            case COLLECTIVE_PACKAGES:
                collectivePackages.entrySet().stream().parallel().forEach((entrySet) -> {
                    obsList.add(entrySet.getKey());
                });
                break;
            case CONTRACTORS:
                contractors.entrySet().stream().parallel().forEach((entrySet) -> {
                    obsList.add(entrySet.getKey());
                });
                break;
            case GROUPS:
                groups.entrySet().stream().parallel().forEach((entrySet) -> {
                    obsList.add(entrySet.getKey());
                });
                break;
            case UNITS_OF_MEASURE:
                unitsOfMeasure.entrySet().stream().parallel().forEach((entrySet) -> {
                    obsList.add(entrySet.getKey());
                });
                break;
            case VAT_RATES:
                vatRates.entrySet().stream().parallel().forEach((entrySet) -> {
                    obsList.add(entrySet.getKey());
                });
                break;
        }
        return obsList;
    }

    private void getRestEntity(ClientResponse response, DataType type) {
        switch (type) {
            case COLLECTIVE_PACKAGES:
                CollectivePackageListDto packagesDto
                        = response.getEntity(CollectivePackageListDto.class);

                packagesDto.getPackages().stream().parallel().forEach((aPackage) -> {
                    collectivePackages.put(aPackage.getCutName(), aPackage);
                });
                break;
            case CONTRACTORS:
                ContractorListDto contractorsDto
                        = response.getEntity(ContractorListDto.class);

                contractorsDto.getList().stream().parallel().forEach((contractor) -> {
                    contractors.put(contractor.getCutName(), contractor);
                });
                break;
            case GROUPS:
                GroupListDto groupsDto = response.getEntity(GroupListDto.class);

                groupsDto.getGroups().stream().parallel().forEach((group) -> {
                    groups.put(group.getName(), group);
                });
                break;
            case UNITS_OF_MEASURE:
                UnitOfMeasureListDto unitsDto
                        = response.getEntity(UnitOfMeasureListDto.class);

                unitsDto.getUnits().stream().parallel().forEach((unit) -> {
                    unitsOfMeasure.put(unit.getName(), unit);
                });
                break;
            case VAT_RATES:
                VATRateListDto vatRatesDto = response.getEntity(VATRateListDto.class);

                vatRatesDto.getVatRates().stream().parallel().forEach((vatRate) -> {
                    vatRates.put(vatRate.getName(), vatRate);
                });
                break;
        }
    }

    @Override
    public boolean deleteData(List<GoodsWrapper> goodsToDelete) {
        logger.info("Deleting " + goodsToDelete.size() + " goods records.");
        List<Goods> goodsDelete = new ArrayList<>();
        goodsToDelete.stream().parallel().forEach((goodsToDelete1) -> {
            goodsDelete.add(goodsToDelete1.getGoods());
        });

        Client client = restUtil.getAuthorizedClient();

        GoodsListDto entitiesToDelete = new GoodsListDto(goodsDelete);
        ClientResponse response = RestUtil.generateRestPost(client,
                "goods/delete", entitiesToDelete);
        if (RestUtil.responseHasErrors(response)) {
            logger.error("Error deleting goods from database.");
            return false;
        }
        return true;
    }

    @Override
    public ObservableList<GoodsWrapper> populateAllGoods() {
        GoodsListDto goodsListDto = new GoodsListDto();
        Client client = restUtil.getAuthorizedClient();

        ClientResponse response = RestUtil.generateRestGet(client, "goods/all");

        if (RestUtil.responseHasErrors(response)) {
            logger.error("Error getting all goods from database. Error status: "
                    + response.getClientResponseStatus().getStatusCode());
        } else {
            logger.info("Populating all goods from database.");

            goodsListDto = response.getEntity(GoodsListDto.class);
        }

        ObservableList<GoodsWrapper> obsGoods = FXCollections.observableArrayList();
        for (Goods goods : goodsListDto.getList()) {
            GoodsWrapper goodsWrapper = new GoodsWrapper(goods);
            obsGoods.add(goodsWrapper);
        }

        return obsGoods;
    }

    @Override
    public ObservableList<GoodsWrapper> filterByIndex1(String index1) {
        ObservableList<GoodsWrapper> result = FXCollections.observableArrayList();
        Client client = restUtil.getAuthorizedClient();

        ClientResponse response = RestUtil.generateRestPost(client, "goods/findByIndex1", index1);

        if (RestUtil.responseHasErrors(response)) {
            logger.error("Index1 not found in database: " + index1);
        } else {
            Goods filteredRecord = response.getEntity(Goods.class);
            GoodsWrapper record = new GoodsWrapper(filteredRecord);
            result.add(record);
        }
        return result;
    }

    @Override
    public TreeItem<String> generateRootView() {
        TreeItem<String> root = new TreeItem<>(TREEVIEW_ROOT);
        TreeItem<String> contractorsRoot = new TreeItem<>(CONTRACTORS_ROOT);
        TreeItem<String> groupsRoot = new TreeItem<>(GROUP_ROOT);

        Client client = restUtil.getAuthorizedClient();

        ClientResponse contractorsResponse = RestUtil.generateRestGet(client, "contractors/all");
        ClientResponse groupsResponse = RestUtil.generateRestGet(client, "groups/all");

        if (RestUtil.responseHasErrors(contractorsResponse)) {
            logger.error("Error generating TreeView for contractors");
        } else {
            ContractorListDto contractorsDto = contractorsResponse
                    .getEntity(ContractorListDto.class);

            contractorsDto.getList().stream().parallel().forEach((contractor) -> {
                contractorsRoot.getChildren()
                        .add(new TreeItem<>(contractor.getCutName()));
            });
        }

        if (RestUtil.responseHasErrors(groupsResponse)) {
            logger.error("Error generating TreeView for groups");
        } else {
            GroupListDto groupsDto = groupsResponse.getEntity(GroupListDto.class);

            groupsDto.getGroups().stream().parallel().forEach((group) -> {
                groupsRoot.getChildren().add(new TreeItem<>(group.getName()));
            });
        }

        root.getChildren().addAll(contractorsRoot, groupsRoot);

        contractorsRoot.setExpanded(true);
        groupsRoot.setExpanded(true);
        root.setExpanded(true);
        return root;
    }

    @Override
    public ObservableList<GoodsWrapper> filterByGroupOrContractor(Object filterOption) {
        boolean contractorsOrGroups = false;
        ObservableList<GoodsWrapper> filteredGoods = FXCollections.observableArrayList();
        String parentValue = ((TreeItem<String>) filterOption).getParent().getValue();
        if (parentValue.equals(CONTRACTORS_ROOT)) {
            contractorsOrGroups = true;
        }
        String selectedValue = ((TreeItem<String>) filterOption).getValue();
        logger.info("Selected value: " + selectedValue);
        if (notEqualsToContractorsOrGroups(selectedValue)) {
            if (contractorsOrGroups) {
                filteredGoods = populateFilteredGoods(selectedValue, "goods/findByContractor");
            } else {
                filteredGoods = populateFilteredGoods(selectedValue, "goods/findByGroup");
            }
        }
        return filteredGoods;
    }

    private ObservableList<GoodsWrapper> populateFilteredGoods(String filterValue, String restUrl) {
        Client client = restUtil.getAuthorizedClient();
        ObservableList<GoodsWrapper> result = FXCollections.observableArrayList();
        ClientResponse response = RestUtil.generateRestPost(client, restUrl,
                filterValue);

        if (!RestUtil.responseHasErrors(response)) {
            GoodsListDto goodsDto = response.getEntity(GoodsListDto.class);
            goodsDto.getList().stream().parallel().forEach((goods) -> {
                result.add(new GoodsWrapper(goods));
            });
        }
        return result;
    }

    private boolean notEqualsToContractorsOrGroups(String selectedValue) {
        return (!selectedValue.equals(CONTRACTORS_ROOT) && !selectedValue.equals(GROUP_ROOT));
    }
}
