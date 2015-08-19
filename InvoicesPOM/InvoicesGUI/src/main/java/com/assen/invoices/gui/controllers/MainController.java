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

/**
 *
 * @author Arek
 */
public class MainController implements Initializable {

    private Stage stage;

    @FXML
    private StackPane currentContentSP;

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
        initGoodsWindow();
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
//            logger.error("Error reading Goods.fxml file.");
//            logger.error(ex.getMessage());
        }

    }

    @FXML
    private void showGoodsScene() {
        goodsController.populateData();
        currentContentSP.getChildren().clear();
        currentContentSP.getChildren().add(goodsRoot);
    }
}
