/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assen.invoices.gui.controllers.add;

import com.assen.invoices.entities.UnitOfMeasure;
import com.assen.invoices.gui.model.wrappers.UnitOfMeasureWrapper;
import com.assen.invoices.gui.utils.PropertiesUtil;
import com.assen.invoices.gui.utils.RestUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author horbana
 */
public class AddUnitController implements Initializable {

    private static final Logger logger = LoggerFactory.getLogger(AddUnitController.class);
    private final PropertiesUtil props = new PropertiesUtil("messages.properties");

    @FXML
    private TextField nameTF;
    @FXML
    private TextField shortcutTF;
    @FXML
    private Button addEditButton;
    @FXML
    private Button clearButton;
    @FXML
    private TextArea errorsTA;

    private Stage stage;

    private boolean isEdit;

    private ObservableList<UnitOfMeasureWrapper> obsUnits;
    private UnitOfMeasureWrapper unit;
    private UnitOfMeasure originalUnit;
    @Inject
    private RestUtil restUtil;

    @Inject
    private IUnitOfMeasureService unitOfMeasureService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public boolean isIsEdit() {
        return isEdit;
    }

    public void setIsEdit(boolean isEdit) {
        this.isEdit = isEdit;
    }

    public ObservableList<UnitOfMeasureWrapper> getObsUnits() {
        return obsUnits;
    }

    public void setObsUnits(ObservableList<UnitOfMeasureWrapper> obsUnits) {
        this.obsUnits = obsUnits;
    }

    public void setUnit(UnitOfMeasureWrapper unit) {
        this.unit = unit;
        originalUnit = UnitOfMeasure.copyOf(unit.getUnitOfMeasure());
        setBindings();
    }

    private void setBindings() {
        nameTF.textProperty().bindBidirectional(unit.nameProperty());
        shortcutTF.textProperty().bindBidirectional(unit.shortcutProperty());

    }
    
    @FXML
    private void addOrEdit() {
        errorsTA.setText("");
        Client client = restUtil.getAuthorizedClient();
        if (validData()) {
            if (isEdit) {
                ClientResponse response = RestUtil.generateRestPost(client, "unitsOfMeasure/update", unit.getUnitOfMeasure());
                if (RestUtil.responseHasErrors(response)) {
                    logger.error("Error updating unit of measure to database.");
                    errorsTA.appendText("Wystąpił błąd podczas aktualizowania nowego obiektu do bazy.\n");
                } else {
                    unit.setUnitOfMeasure(response.getEntity(UnitOfMeasure.class));
                }
            } else {
                ClientResponse response = RestUtil.generateRestPost(client, "unitsOfMeasure/add", unit.getUnitOfMeasure());
                if (RestUtil.responseHasErrors(response)) {
                    logger.error("Error adding unit of measure to database.");
                    errorsTA.appendText("Wystąpił błąd podczas dodawania nowego obiektu do bazy.\n");
                } else {
                    unit.setUnitOfMeasure(response.getEntity(UnitOfMeasure.class));
                    obsUnits.add(new UnitOfMeasureWrapper(unit.getUnitOfMeasure()));
                }
            }
        }
        if (errorsTA.getText().equals("")) {
            stage.close();
        }
    }

     private boolean validData() {
     
        String errors = goodsService.validData(goods, validationData);

        if (!errors.equals("")) {
            logger.info("Invalid Goods data: " + errors);
            errorsTA.appendText(errors);
            return false;
        }
        return true;
    }
}
