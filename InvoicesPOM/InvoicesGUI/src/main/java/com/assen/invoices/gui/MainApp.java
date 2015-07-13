package com.assen.invoices.gui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;


public class MainApp extends Application {
    
    private Weld weld;
    private WeldContainer container;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        weld = new Weld();
        container = weld.initialize();
    }

    @Override
    public void start(Stage stage) throws Exception {
        container.instance().select(FxMain.class).get().start(stage, getParameters());
    }

    @Override
    public void stop() {
        weld.shutdown();
    }
//
//    @Override
//    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
//        
//        Scene scene = new Scene(root);
//        scene.getStylesheets().add("/styles/Styles.css");
//        
//        stage.setTitle("JavaFX and Maven");
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    /**
//     * The main() method is ignored in correctly deployed JavaFX application.
//     * main() serves only as fallback in case the application can not be
//     * launched through deployment artifacts, e.g., in IDEs with limited FX
//     * support. NetBeans ignores main().
//     *
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        launch(args);
//    }

}
