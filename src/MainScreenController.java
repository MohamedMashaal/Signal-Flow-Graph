import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Mohamed Mashaal on 4/20/2018.
 */
public class MainScreenController implements Initializable{
    Drawer drawer;
    private static final Color canvasBackground = Color.web("#ffffff");

    @FXML
    AnchorPane mainPane;
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
    @FXML
    ScrollPane scrollPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        drawer = new Drawer(canvas, canvasBackground);
        canvas.setBackground(new Background(new BackgroundFill(canvasBackground , CornerRadii.EMPTY, Insets.EMPTY)));
        addNodes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                drawer.addVertices(Integer.parseInt(nodesInput.getText()));
            }
        });

        removeNodes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                drawer.deleteVertices(Integer.parseInt(nodesInput.getText()));
            }
        });

        addEdge.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                drawer.addEdge(Integer.parseInt(fromNodeInput.getText()), Integer.parseInt(toNodeInput.getText()), Integer.parseInt(edgeWeightInput.getText()));
            }
        });

        removeEdge.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                drawer.removeEdge(Integer.parseInt(fromNodeInput.getText()), Integer.parseInt(toNodeInput.getText()), Integer.parseInt(edgeWeightInput.getText()));
            }
        });

        listForwardPaths.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SFG sfg = new SFG(getConvertedVertices(), getConvertedEdges());
                resultArea.setText(sfg.getAllForwardPaths().toString());
            }
        });

        listIndLoops.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SFG sfg = new SFG(getConvertedVertices(), getConvertedEdges());
                resultArea.setText(sfg.getAllIndividualLoops().toString());
            }
        });

        listNonTouchingLoops.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SFG sfg = new SFG(getConvertedVertices(), getConvertedEdges());
                resultArea.setText(sfg.getAllNonTouchingLoops().toString());
            }
        });

        listDeltas.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SFG sfg = new SFG(getConvertedVertices(), getConvertedEdges());
                resultArea.setText(sfg.getDelta() + "\n" + sfg.getAllDeltasOfForwardPaths());
            }
        });

        listTransferFunction.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SFG sfg = new SFG(getConvertedVertices(), getConvertedEdges());
                resultArea.setText(String.valueOf(sfg.getTotalTransferFunction()));
            }
        });
    }

    private ArrayList<DirectedEdgeData> getConvertedEdges(){
        ArrayList<DirectedEdgeData> edges = new ArrayList<>();
        for(Edge edge : drawer.getEdges()){
            edges.add(new DirectedEdgeData(Integer.toString(edge.getEdgePair().x),
                    Integer.toString(edge.getEdgePair().y), edge.getWeight()));
        }
        System.out.println(edges);
        return edges;
    }

    private ArrayList<String> getConvertedVertices(){
        ArrayList<String> vertices = new ArrayList<>();
        for(Vertex vertex : drawer.getVertices()){
            vertices.add(Integer.toString(vertex.getNumber()));
        }
        System.out.println(vertices);
        return vertices;
    }


}
