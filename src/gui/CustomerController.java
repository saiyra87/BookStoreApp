package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Book;
import models.BookStore;
import models.Customer;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Owner;

/**
 *
 * @author Saiyra
 */
public class CustomerController {
    @FXML
    private Label welcomeLabel;
    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Book, String> bookNameColumn;
    @FXML
    private TableColumn<Book, Double> bookPriceColumn;
    @FXML
    private TableColumn<Book, CheckBox> selectColumn;

    private Customer customer;
    private BookStore store;
    private ObservableList<Book> bookList;

    public void initialize() {
        store = BookStore.getInstance(new Owner("admin", "admin"));
        loadBooks(); 
    }

    public void setData(Customer customer) {
        this.customer = customer;
        updateWelcomeMessage();
    }

   
    private void updateWelcomeMessage() {
        String status = customer.getPoints() >= 1000 ? "Gold" : "Silver";
        welcomeLabel.setText("Welcome " + customer.getUsername() +
                ". You have " + customer.getPoints() + " points. Your status is " + status + ".");
    }

    private void loadBooks() {
        bookList = FXCollections.observableArrayList(store.getBooksList());
        bookTable.setItems(bookList);

  
        bookNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        bookPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

      
        selectColumn.setCellValueFactory(param -> {
            Book book = param.getValue();
            CheckBox checkBox = new CheckBox();
            checkBox.selectedProperty().set(book.isSelected()); 
            checkBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
                book.setSelected(isNowSelected);
            });
            return new javafx.beans.property.SimpleObjectProperty<>(checkBox);
        });
    }

    @FXML
    private void handleBuy() {
        List<Book> selectedBooks = getSelectedBooks();

        if (selectedBooks.isEmpty()) {
            showAlert("No Books Selected", "Please select at least one book to buy.");
            return;
        }

      
        double totalCost = selectedBooks.stream()
                .mapToDouble(Book::getPrice)
                .sum();

        
        customer.setPoints(customer.getPoints() + (int) (totalCost * 10));

        customer.updateStatus();

        store.saveData();

        proceedToCostScreen(totalCost);
    }

    @FXML
    private void handleRedeemAndBuy() {
        List<Book> selectedBooks = getSelectedBooks();
    
        if (selectedBooks.isEmpty()) {
            showAlert("No Books Selected", "Please select at least one book to buy.");
            return;
        }

 
        double totalCost = selectedBooks.stream()
            .mapToDouble(Book::getPrice)
            .sum();

     
        int redeemablePoints = customer.getPoints() / 100;  
        double redeemAmount = Math.min(redeemablePoints, totalCost); 

    
        double finalCost = totalCost - redeemAmount;
        finalCost = Math.max(finalCost, 0);

        int pointsUsed = (int) (redeemAmount * 100);
        customer.setPoints(customer.getPoints() - pointsUsed);

    
        if (finalCost > 0) {
            int newPoints = (int) (finalCost * 10);
            customer.setPoints(customer.getPoints() + newPoints);
        }

        customer.updateStatus();

        store.saveData();

        proceedToCostScreen(finalCost);
    }



    private List<Book> getSelectedBooks() {
        return bookList.stream()
                .filter(Book::isSelected)
                .collect(Collectors.toList());
    }

    private void proceedToCostScreen(double totalCost) {
        GUIController.getInstance().showCostScreen(customer, totalCost);
    }

    @FXML
    private void handleLogout() {
        GUIController.getInstance().showLoginScreen();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}