package com.assen.invoices.gui;

import com.assen.invoices.gui.controllers.LoginController;
import java.io.IOException;
import java.io.InputStream;
import javafx.application.Application.Parameters;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.inject.Inject;

/**
 *
 * @author Arek
 */
public class FxMain {

    @Inject
    private FXMLLoader loader;

    public void start(Stage stage, Parameters parameters) throws IOException {
        try (InputStream fxml = getClass().getResourceAsStream("/fxml/Login.fxml")) {
            Parent root = (Parent) loader.load(fxml);
            LoginController loginController = loader.getController();
            
            Scene scene = new Scene(root);
            scene.setOnKeyPressed(keyEvent -> loginController.performLogin(keyEvent));
            stage.setScene(scene);
            stage.setTitle("Login");
            
            loginController.setStage(stage);
            
            stage.show();
        } 
    }
}
