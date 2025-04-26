
module BookStoreApp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    opens models to javafx.base;
    exports models;

    opens gui to javafx.fxml;  // For FXML controllers
    exports gui;

    opens bookstore to javafx.graphics;  // Opens bookstore for JavaFX
    exports bookstore;
}
