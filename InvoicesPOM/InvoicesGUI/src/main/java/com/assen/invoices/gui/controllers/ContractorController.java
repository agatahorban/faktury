/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assen.invoices.gui.controllers;

import com.assen.invoices.entities.Contractor;
import com.assen.invoices.gui.model.wrappers.ContractorWrapper;
import com.assen.invoices.gui.utils.RestUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Agata
 */
public class ContractorController implements Initializable {

    @Inject
    private RestUtil restUtil;
    
    @FXML
    private TableView<ContractorWrapper> contractorsTV;

    @FXML
    private TableColumn<ContractorWrapper, String> cutnameTC;
    
    @FXML
    private TableColumn<ContractorWrapper, String> nipTC;
    
    @FXML
    private TableColumn<ContractorWrapper, String> townTC;
    
    @FXML
    private TableColumn<ContractorWrapper, String> postalCodeTC;
    
    @FXML
    private TableColumn<ContractorWrapper, String> streetTC;
    
    @FXML
    private TableColumn<ContractorWrapper, Number> houseNumberTC;
    
    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cutnameTC.setCellValueFactory(cellData -> cellData.getValue().cutNameProperty());
        nipTC.setCellValueFactory(cellData -> cellData.getValue().NIPProperty());
        townTC.setCellValueFactory(cellData -> cellData.getValue().getAddressWrapper().townProperty());
        postalCodeTC.setCellValueFactory(cellData -> cellData.getValue().getAddressWrapper().postalCodeProperty());
        streetTC.setCellValueFactory(cellData -> cellData.getValue().getAddressWrapper().streetProperty());
        houseNumberTC.setCellValueFactory(cellData -> cellData.getValue().getAddressWrapper().houseNumberProperty());
        
    }
    
    public void generateData(){

        Client client = restUtil.getAuthorizedClient();
        WebResource webResource = client.resource(RestUtil.URL + "contractors/all");
        ClientResponse response = webResource.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);
        List<Contractor> contractors = response.getEntity(new GenericType<List<Contractor>>() {
        });
        ObservableList<ContractorWrapper> obsContractors = FXCollections.observableArrayList();
        for (Contractor contractor : contractors) {
            ContractorWrapper contractorWrapper = new ContractorWrapper(contractor);
            obsContractors.add(contractorWrapper);
        }
        contractorsTV.setItems(obsContractors);
        
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    
}
