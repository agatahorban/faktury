package com.assen.invoices.gui;

import javafx.application.Application;
import static javafx.application.Application.launch;
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
}
