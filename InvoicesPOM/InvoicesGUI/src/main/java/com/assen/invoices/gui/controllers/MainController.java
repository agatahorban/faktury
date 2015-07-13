package com.assen.invoices.gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Arek
 */
public class MainController implements Initializable {

    private Stage stage;
    
    @FXML
    private StackPane currentContentSP;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
