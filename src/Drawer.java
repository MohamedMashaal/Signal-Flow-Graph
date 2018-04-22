import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by Mohamed Mashaal on 4/21/2018.
 */
public class Drawer {
    private static final int startPositionX = 30;
    private static final int startPositionY = 350;
    private static final int nodesRadius = 20;
    private static final int distanceBetweenNodes = 120;
    private static final Color drawingColor = Color.web("#fcc000");
    private ArrayList<Vertex> vertices;
    private ArrayList<Edge> edges;
    private AnchorPane canvas;
    private Color canvasBackground;
    private int currentStoppingXPosition;
    private int currentStoppingYPosition;
    private int currentVertexNumber;

    public Drawer(AnchorPane canvas , Color canvasBackground){
        this.canvas = canvas;
        this.canvasBackground = canvasBackground;
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        currentStoppingXPosition = startPositionX;
        currentStoppingYPosition = startPositionY;
        currentVertexNumber = 1;
    }

    public void addVertices(int numberOfVertices){
        for(int i = 0 ; i < numberOfVertices ; i++){
            Vertex vertex = new Vertex(currentStoppingXPosition, currentStoppingYPosition, nodesRadius, drawingColor, currentVertexNumber);
            vertices.add(vertex);
            vertex.addVertex(canvas);
            currentStoppingXPosition += distanceBetweenNodes;
            currentVertexNumber ++;
        }
    }

    public void deleteVertices(int numberOfVertices){
        for(int i = 0 ; i < numberOfVertices ; i ++){
            if(!(vertices.size() <= 0)){
                Vertex lastOne = vertices.get(vertices.size()-1);
                vertices.remove(vertices.size()-1);
                lastOne.removeVertex(canvas);
                currentStoppingXPosition -= distanceBetweenNodes;
                currentVertexNumber --;
            }
            else{
                break;
            }
        }
    }

    public void addEdge(int from , int to , int weight){
        if(!edgeExist(from, to)){
            Edge edge = new Edge(vertices.get(from-1), vertices.get(to-1), nodesRadius, weight, canvasBackground);
            edges.add(edge);
            edge.addEdge(canvas);
        }
        else{
            Edge edge = getEdge(from, to);
            edge.updateWeight(edge.getWeight() + weight);
        }
    }

    private boolean edgeExist(int from, int to) {
        for(Edge edge : edges){
            if(edge.getEdgePair().x == from && edge.getEdgePair().y == to)
                return true;
        }
        return false;
    }

    private Edge getEdge(int from, int to) {
        for(Edge edge : edges){
            if(edge.getEdgePair().x == from && edge.getEdgePair().y == to)
                return edge;
        }
        return null;
    }


    public void removeEdge(int from , int to , int weight){
        if(edgeExist(from, to)){
            Edge edge = getEdge(from, to);
            edges.remove(edge);
            edge.removeEdge(canvas);
        }
    }


}
