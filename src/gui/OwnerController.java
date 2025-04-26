
package gui;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import gui.GUIController;

/**
 *
 * @author Saiyra
 */
public class OwnerController {
    @FXML
    private Label errorLabel;
    @FXML
    private void handleBooks(ActionEvent event) {
        GUIController.getInstance().showOwnerBooksScreen();
        
    }

    @FXML
    private void handleCustomers(ActionEvent event) {
        GUIController.getInstance().showOwnerCustomersScreen();
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        // Go back to login screen
        GUIController.getInstance().showLoginScreen();
    }

    
}
