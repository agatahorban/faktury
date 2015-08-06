package com.assen.invoices.gui.controllers;

import com.assen.invoices.dto.LoginCredentialsDto;
import com.assen.invoices.entities.User;
import com.assen.invoices.gui.session.LoggedUser;
import com.assen.invoices.gui.utils.RestUtil;
import com.assen.invoices.gui.utils.ShaUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class
 *
 * @author Arek
 */
public class LoginController implements Initializable {
    
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @FXML
    private TextField loginTF;
    @FXML
    private PasswordField passwordPF;
    @FXML
    private Label errors;
    
    @Inject
    private LoggedUser loggedUser;

    @Inject
    private FXMLLoader mainLoader;

    private Parent mainRoot;
    private Stage mainStage;
    private Scene mainScene;
    private MainController mainController;

    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void logIn() {
        Client client = RestUtil.getUnauthorizedClient();
        
        WebResource webResource = client.resource(RestUtil.URL + "login");

        LoginCredentialsDto loginCredentialsDto = new LoginCredentialsDto();
        loginCredentialsDto.setLogin(loginTF.getText());
        loginCredentialsDto.setPassword(ShaUtil.sha256(passwordPF.getText()));

        ClientResponse response = webResource.accept(MediaType.APPLICATION_XML).post(ClientResponse.class, loginCredentialsDto);

        if (response.getClientResponseStatus().equals(ClientResponse.Status.INTERNAL_SERVER_ERROR)) {
            logger.warn("Wrong credentials for user: " + loginTF.getText());
            errors.setText("Podane dane są niewłaściwe. Spróbuj jeszcze raz.");
            loginTF.setText("");
            passwordPF.setText("");
        } else {
            logger.info("Successfully logged user: " + loginTF.getText());
            User user = response.getEntity(User.class);

            loggedUser.setDbUser(user);
            loggedUser.setPassword(passwordPF.getText());

            mainStage.show();
            stage.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initMainWindow();
    }

    private void initMainWindow() {
        try (InputStream mainFXML = getClass().getResourceAsStream("/fxml/Main.fxml")) {
            mainRoot = mainLoader.load(mainFXML);

            mainStage = new Stage();
            mainStage.setTitle("Faktury");

            mainScene = new Scene(mainRoot);
            mainStage.setScene(mainScene);
            
            //Maximizing window
            mainStage.setMaximized(true);

            mainController = mainLoader.getController();
            mainController.setStage(mainStage);
        } catch (IOException ex) {
            logger.error("Error reading Main.fxml file.");
            logger.error(ex.getMessage());
        }
    }
}
