package com.assen.invoices.gui.controllers;

import com.assen.invoices.dto.LoginCredentialsDto;
import com.assen.invoices.entities.User;
import com.assen.invoices.view.utils.RestUtil;
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

/**
 * FXML Controller class
 *
 * @author Arek
 */
public class LoginController implements Initializable {

    @FXML
    private TextField loginTF;
    @FXML
    private PasswordField passwordPF;
    @FXML
    private Label errors;

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
        Client client = RestUtil.getClient();

        WebResource webResource = client.resource(RestUtil.URL + "login");

        LoginCredentialsDto loginCredentialsDto = new LoginCredentialsDto();
        loginCredentialsDto.setLogin(loginTF.getText());
        loginCredentialsDto.setPassword(passwordPF.getText());

        ClientResponse response = webResource.accept(MediaType.APPLICATION_XML).post(ClientResponse.class, loginCredentialsDto);

        if (response.getClientResponseStatus().equals(ClientResponse.Status.INTERNAL_SERVER_ERROR)) {
            errors.setText("Podane dane są niewłaściwe. Spróbuj jeszcze raz.");
        } else {
            User user = response.getEntity(User.class);

            System.out.println(user.getLogin());
            System.out.println(user.getPassword());

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

            mainController = mainLoader.getController();
            mainController.setStage(mainStage);
        } catch (IOException ex) {
            //TODO implement slf4j
        }
    }
}
