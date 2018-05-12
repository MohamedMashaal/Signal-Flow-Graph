/**
 * Created by Mohamed Mashaal on 4/20/2018.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainScreen extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainScreenFXML.fxml"));

        primaryStage.setTitle("Signal Flow Graph");
        primaryStage.setScene(new Scene(root, 1146, 767));
        primaryStage.show();
    }
}
