
package gui;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Node;
import java.io.IOException;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.BookStore;
import models.Owner;
import models.Customer;
import gui.GUIController;
/**
 *
 * @author Saiyra
 */
public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    private BookStore bookStore;

    public void initialize() {
        Owner owner = new Owner("admin", "admin"); 
        bookStore = BookStore.getInstance(owner); 
    }
  
    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        int loginResult = bookStore.login(username, password);
        switch (loginResult) {
            case 0:
                errorLabel.setText("Login successful! (Owner)");
                GUIController.getInstance().showOwnerScreen(); 
                break;
            case 1:
                errorLabel.setText("Login successful! (Customer)");
                for(Customer c: bookStore.getCustomerList()){
                    if(c.getUsername().equals(username)&&c.getPassword().equals(password)){
                        GUIController.getInstance().showCustomerScreen(c);
                    }
                }
                
                break;
            default:
                errorLabel.setText("Invalid username or password.");
                break;
        }
    }

}