package com.assen.invoices.gui.controllers.add;

import com.assen.invoices.entities.CollectivePackage;
import com.assen.invoices.gui.model.wrappers.CollectivePackageWrapper;
import com.assen.invoices.gui.utils.PropertiesUtil;
import com.assen.invoices.gui.utils.RestUtil;
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
public class AddPackageController implements Initializable {
  private static final Logger logger = LoggerFactory.getLogger(AddPackageController.class);
    private final PropertiesUtil props = new PropertiesUtil("messages.properties");

    @FXML
    private TextField fullnameTF;
    @FXML
    private TextField cutnameTF;
    @FXML
    private TextField capacityTF;
    @FXML
    private TextField depthTF;
    @FXML
    private TextField heightTF;
    @FXML
    private TextField widthTF;
    
    @FXML
    private Button addEditButton;
    @FXML
    private Button clearButton;
    @FXML
    private TextArea errorsTA;

    private Stage stage;

    private boolean isEdit;
    
    private ObservableList<CollectivePackageWrapper> obsPackages;
    private CollectivePackageWrapper unit;
    private CollectivePackage originalUnit;
    
    @Inject
    private RestUtil restUtil;

//    @Inject
//    private IUnitOfMeasureService unitOfMeasureService;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
    }
    
}
