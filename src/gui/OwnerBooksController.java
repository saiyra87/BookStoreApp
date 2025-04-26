
package gui;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Book;
import models.BookStore;
import models.Owner;
/**
 *
 * @author Saiyra
 */
public class OwnerBooksController {

    @FXML
    private TableView<Book> booksTable;
    @FXML
    private TableColumn<Book, String> nameColumn;
    @FXML
    private TableColumn<Book, Double> priceColumn;
    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;

    private BookStore bookStore;
    private Owner owner;

    private ObservableList<Book> bookData;

    public void initialize() {
        owner = new Owner("admin","admin");
        this.bookStore = BookStore.getInstance(owner);
        
        bookData = FXCollections.observableArrayList(bookStore.getBooksList());

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        booksTable.setItems(bookData);
    }

    @FXML
     private void handleAdd(ActionEvent event) {
        String name = nameField.getText().trim();
        String priceText = priceField.getText().trim();

        if (name.isEmpty() || priceText.isEmpty()) {
            showAlert("Error", "Please enter both book name and price.");
            return;
        }

        try {
            double price = Double.parseDouble(priceText);
            
            owner.addBook(bookStore, name, price);

            bookData.setAll(bookStore.getBooksList());

            nameField.clear();
            priceField.clear();
            showAlert("Success", "Book added successfully.");
        } catch (NumberFormatException e) {
            showAlert("Error", "Price must be a valid number.");
        }
    }

     @FXML
    private void handleDelete(ActionEvent event) {
        Book selectedBook = booksTable.getSelectionModel().getSelectedItem();
        if (selectedBook == null) {
            showAlert("Error", "Please select a book to delete.");
            return;
        }

        owner.deleteBook(bookStore, selectedBook.getName());
   
        bookData.setAll(bookStore.getBooksList());

        showAlert("Success", "Book deleted successfully.");
    }
    @FXML
    private void handleBack(ActionEvent event) {
       GUIController.getInstance().showOwnerScreen();
    }
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, content, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
