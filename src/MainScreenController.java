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
                drawer.removeEdge(Integer.parseInt(fromNodeInput.getText()), Integer.parseInt(toNodeInput.getText()));
            }
        });

        listForwardPaths.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SFG sfg = new SFG(getConvertedVertices(), getConvertedEdges());

                StringBuilder sb = new StringBuilder();
                sb.append("All forward Paths\n---------------------------\n");
                ArrayList<VerticesAndGain> forwardPaths = sfg.getAllForwardPaths();
                int i=1;
                for(VerticesAndGain forwardPath : forwardPaths){
                    sb.append("Forward path #");
                    if(i < 10)
                        sb.append("0");
                    sb.append(i);
                    i++;
                    sb.append("\n");

                    sb.append("Vertices: ");
                    sb.append(forwardPath.getVertices());
                    sb.append("\n");
                    sb.append("Gain: ");
                    sb.append(forwardPath.getGain());
                    sb.append("\n\n");
                }

                resultArea.setText(sb.toString());
            }
        });

        listIndLoops.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SFG sfg = new SFG(getConvertedVertices(), getConvertedEdges());

                StringBuilder sb = new StringBuilder();
                sb.append("All individual loops\n---------------------------\n");
                ArrayList<VerticesAndGain> individualLoops = sfg.getAllIndividualLoops();
                int i=1;
                for(VerticesAndGain individualLoop : individualLoops){
                    sb.append("Individual loop #");
                    if(i < 10)
                        sb.append("0");
                    sb.append(i);
                    i++;
                    sb.append("\n");

                    sb.append("Vertices: ");
                    individualLoop.getVertices().add(individualLoop.getVertices().get(0));
                    sb.append(individualLoop.getVertices());
                    sb.append("\n");
                    sb.append("Gain: ");
                    sb.append(individualLoop.getGain());
                    sb.append("\n\n");
                }

                resultArea.setText(sb.toString());
            }
        });

        listNonTouchingLoops.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SFG sfg = new SFG(getConvertedVertices(), getConvertedEdges());

                StringBuilder sb = new StringBuilder();
                ArrayList<ArrayList<ArrayList<VerticesAndGain>>> allNonTouchingLoops = sfg.getAllNonTouchingLoops();
                int j=2;
                for(ArrayList<ArrayList<VerticesAndGain>> allNonTouchingN : allNonTouchingLoops){
                    sb.append("All " + j + " non touching loops\n---------------------------\n");
                    j++;
                    int k=1;
                    for(ArrayList<VerticesAndGain> nonTouchingN : allNonTouchingN){
                        sb.append("#");
                        if(k < 10)
                            sb.append("0");
                        sb.append(k);
                        sb.append("\n");
                        k++;
                        int i=1;
                        for(VerticesAndGain loop : nonTouchingN){
                            sb.append("Vertices: ");
                            ArrayList<String> vertices = new ArrayList<>();
                            vertices.addAll(loop.getVertices());
                            vertices.add(vertices.get(0));
                            sb.append(vertices);
                            sb.append(" Gain: ");
                            sb.append(loop.getGain());
                            sb.append("\n");
                        }
                        sb.append("\n\n");
                    }
                }

                resultArea.setText(sb.toString());
            }
        });

        listDeltas.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SFG sfg = new SFG(getConvertedVertices(), getConvertedEdges());

                StringBuilder sb = new StringBuilder();
                sb.append("Delta = ");
                sb.append(sfg.getDelta());
                sb.append("\n\n");
                ArrayList<Double> allDeltasOfForwardPaths = sfg.getAllDeltasOfForwardPaths();
                int i=1;
                for(Double deltaI : allDeltasOfForwardPaths){
                    sb.append("Delta #");
                    if(i < 10)
                        sb.append("0");
                    sb.append(i);
                    i++;
                    sb.append(" = ");
                    sb.append(deltaI);
                    sb.append("\n");
                }

                resultArea.setText(sb.toString());
            }
        });

        listTransferFunction.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SFG sfg = new SFG(getConvertedVertices(), getConvertedEdges());

                StringBuilder sb = new StringBuilder();
                sb.append("T.F = ");
                sb.append(sfg.getTotalTransferFunction());
                sb.append("\n");

                resultArea.setText(sb.toString());
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
