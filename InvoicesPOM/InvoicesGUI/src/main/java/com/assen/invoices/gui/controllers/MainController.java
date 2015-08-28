package com.assen.invoices.gui.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Arek
 */
public class MainController implements Initializable {

    private Stage stage;

    @FXML
    private StackPane currentContentSP;

    @Inject
    private FXMLLoader contractorLoader;

    private Parent contractorRoot;
    private Stage contractorStage;
    private Scene contractorScene;

    private ContractorController contractorController;

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Inject
    private FXMLLoader goodsLoader;
    private Parent goodsRoot;
    private Scene goodsScene;
    private Stage goodsStage;
    private GoodsController goodsController;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initContractorsWindow();
        initGoodsWindow();
    }

    private void initContractorsWindow() {
        try (InputStream mainFXML = getClass().getResourceAsStream("/fxml/Contractors.fxml")) {
            contractorRoot = contractorLoader.load(mainFXML);

            contractorStage = new Stage();

            contractorScene = new Scene(contractorRoot);
            contractorStage.setScene(contractorScene);

            contractorController = contractorLoader.getController();
            contractorController.setStage(contractorStage);
        } catch (IOException ex) {
            logger.error("Error reading Contractors.fxml file.");
            logger.error(ex.getMessage());
        }
    }

    @FXML
    private void loadContractorScene() {
        currentContentSP.getChildren().clear();
        contractorController.generateData();
        currentContentSP.getChildren().add(contractorRoot);

    }

    private void initGoodsWindow() {
        try (InputStream goodsFXML = getClass().getResourceAsStream("/fxml/Goods.fxml")) {
            goodsRoot = goodsLoader.load(goodsFXML);

            goodsStage = new Stage();
            goodsStage.setTitle("Faktury");

            goodsScene = new Scene(goodsRoot);
            goodsStage.setScene(goodsScene);

            goodsController = goodsLoader.getController();
            goodsController.setStage(stage);
        } catch (IOException ex) {
            logger.error("Error reading Goods.fxml file.");
            logger.error(ex.getMessage());
        }

    }

    @FXML
    private void showGoodsScene() {
        goodsController.populateData();
        currentContentSP.getChildren().clear();
        currentContentSP.getChildren().add(goodsRoot);
    }

}
