package com.assen.invoices.gui.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author Arek
 */
public class AlertUtil {

    public static Alert createWarningAlert(String title, String contextText) {
        return createAlert(title, contextText, AlertType.WARNING);
    }

    public static Alert createErrorAlert(String title, String contextText) {
        return createAlert(title, contextText, AlertType.ERROR);
    }
    
    public static Alert createConfirmationAlert(String title, String contextText) {
        return createAlert(title, contextText, AlertType.CONFIRMATION);
    } 

    private static Alert createAlert(String title, String contextText, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        
        return alert;
    }
}
