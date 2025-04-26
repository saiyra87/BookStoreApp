
package gui;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
/**
 *
 * @author Saiyra
 */
public class SceneLoader {

    public static FXMLLoader loadScene(String fxmlPath, Stage stage, double width, double height) {
        FXMLLoader loader = new FXMLLoader(SceneLoader.class.getResource(fxmlPath));
        try {
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setWidth(width);
            stage.setHeight(height);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loader;
    }
}