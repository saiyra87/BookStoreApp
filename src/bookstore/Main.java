
package bookstore;

import javafx.application.Application;

import javafx.stage.Stage;
import gui.GUIController;
/**
 *
 * @author Saiyra
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        GUIController.init(primaryStage); 
        GUIController.getInstance().showLoginScreen(); 
    }

    public static void main(String[] args) {
        
        launch(args);
    }
}
