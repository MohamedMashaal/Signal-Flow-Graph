import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Mohamed Mashaal on 4/20/2018.
 */
public class MainScreenController implements Initializable{
    int positionX = 10;
    int positionY = 10;

    @FXML
    AnchorPane canvas;
    @FXML
    TextField fromNodeInput;
    @FXML
    TextField toNodeInput;
    @FXML
    TextField edgeWeightInput;
    @FXML
    Button addEdge;
    @FXML
    Button removeEdge;
    @FXML
    TextField nodesInput;
    @FXML
    Button addNodes;
    @FXML
    Button removeNodes;
    @FXML
    Button listForwardPaths;
    @FXML
    Button listIndLoops;
    @FXML
    Button listNonTouchingLoops;
    @FXML
    Button listDeltas;
    @FXML
    Button listTransferFunction;
    @FXML
    TextArea resultArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addEdge.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int radius = 50;
                Circle circle = new Circle(positionX,positionY , radius);
                canvas.getChildren().add(circle);
                positionX += radius;
                positionY += radius;
            }
        });
    }
}
