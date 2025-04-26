
package gui;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.BookStore;
import models.Customer;
import models.Owner;
/**
 *
 * @author Saiyra
 */
public class OwnerCustomersController {

    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, String> usernameColumn;
    @FXML
    private TableColumn<Customer, String> passwordColumn;
    @FXML
    private TableColumn<Customer, Integer> pointsColumn;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    private Owner owner;
    private BookStore bookStore;
    private ObservableList<Customer> customerList;

    public void initialize() {
        owner = new Owner("admin","admin");
        this.bookStore = BookStore.getInstance(owner);
        loadCustomers();
    
    }

    private void loadCustomers() {
        customerList = FXCollections.observableArrayList(bookStore.getCustomerList());
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));

        customerTable.setItems(customerList);
    }

    @FXML
    private void handleAdd() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Username and Password cannot be empty.");
            return;
        }

        owner.addCustomer(bookStore, username, password);
        loadCustomers();
        clearFields();
    }

    @FXML
    private void handleDelete() {
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();

        if (selectedCustomer == null) {
            showAlert("Error", "Please select a customer to delete.");
            return;
        }

        owner.deleteCustomer(bookStore, selectedCustomer.getUsername());
        loadCustomers();
    }

    @FXML
    private void handleBack(ActionEvent event) {
       GUIController.getInstance().showOwnerScreen();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void clearFields() {
        usernameField.clear();
        passwordField.clear();
    }
}

