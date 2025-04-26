package gui;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import models.Customer;
import gui.CustomerController;
/**
 *
 * @author Priyanshi/Saiyra
 */
public class GUIController {
    private static GUIController instance; 
    private Stage primaryStage;
    CustomerController cc;

    private GUIController(Stage stage) { 
        this.primaryStage = stage;
    }

    public static void init(Stage stage) {
        if (instance == null) {
            instance = new GUIController(stage);
        }
    }

    public static GUIController getInstance() {
        if (instance == null) {
            throw new IllegalStateException("GUIController is not initialized. Call init(Stage) first.");
        }
        return instance;
    }

    public void showLoginScreen() {
        loadScene("/gui/LoginScreen.fxml", 600, 600);
    }

    public void showOwnerScreen() {
        
        loadScene("/gui/OwnerScreen.fxml", 600, 600);
    }
    public void showOwnerBooksScreen(){
        loadScene("/gui/OwnerBooksScreen.fxml",600,600);
    }   
    public void showOwnerCustomersScreen(){
        loadScene("/gui/OwnerCustomersScreen.fxml",600,600);
    }   

    public void showCustomerScreen(Customer customer) {
        FXMLLoader loader = SceneLoader.loadScene("/gui/CustomerScreen.fxml", primaryStage, 600, 600);
        cc = loader.getController();
        cc.setData(customer);
    }
    public void showCostScreen(Customer c, double tc){
        FXMLLoader loader = SceneLoader.loadScene("/gui/CustomerCostScreen.fxml", primaryStage, 600, 600);
        CustomerCostController controller = loader.getController();
        controller.setData(c, tc);
    }

    private void loadScene(String fxmlPath, double width, double height) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
            primaryStage.setWidth(width); 
            primaryStage.setHeight(height);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}