import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.QuadCurve;
import javafx.scene.text.Text;

import java.awt.*;

/**
 * Created by Mohamed Mashaal on 4/21/2018.
 */
public class Edge {
    private static final Color curvesColor = Color.BLACK;
    private static final Color weightColor = Color.RED;
    private static final int strokeWidth = 2;
    private QuadCurve firstSegment;
    private QuadCurve secondSegment;
    private Text weightText;
    private static final int textSpace = 30;
    private static final double curveSlopnessFactor = .4;
    private static final double controlFactor = .9;
    private int weight;
    private Vertex from;
    private Vertex to;

    public Edge(Vertex from , Vertex to , int vertexRadius , int weight){
        this.weight = weight;
        this.from = from;
        this.to=to;
        if(from.getNumber() != to.getNumber()){
            Vertex firstOne = getFirstVertex(from,to);
            Vertex secondOne = getSecondVertex(from,to);
            int up = firstOne == from ? 1 : -1;
            firstSegment = createFirstSegment(firstOne, secondOne, vertexRadius , up);
            secondSegment = createSecondSegment(firstOne,secondOne,vertexRadius , up);
            curvesStyle();
            createWeight(firstOne , secondOne,up);
        }
    }

    private void createWeight(Vertex firstOne, Vertex secondOne, int up) {
        int distance = secondOne.getPosition().x - firstOne.getPosition().x;
        weightText = new Text(firstOne.getPosition().x + distance/2 - 5 , firstOne.getPosition().getY() - curveSlopnessFactor*distance * up , Integer.toString(weight));
        weightText.setFill(weightColor);
    }

    private void curvesStyle() {
        firstSegment.setFill(Color.TRANSPARENT);
        firstSegment.setStroke(curvesColor);
        firstSegment.setStrokeWidth(strokeWidth);
        secondSegment.setFill(Color.TRANSPARENT);
        secondSegment.setStroke(curvesColor);
        secondSegment.setStrokeWidth(strokeWidth);
    }

    private QuadCurve createSecondSegment(Vertex firstOne, Vertex secondOne, int vertexRadius , int up) {
        int distance = secondOne.getPosition().x - firstOne.getPosition().x;
        int x2 = secondOne.getPosition().x;
        int y2 = secondOne.getPosition().y;
        QuadCurve curve = new QuadCurve(x2 - distance/2 + textSpace/2,y2 - curveSlopnessFactor*distance * up ,x2 - vertexRadius/Math.sqrt(2),y2 - curveSlopnessFactor*distance * up * controlFactor, x2- vertexRadius/Math.sqrt(2), y2 - vertexRadius /Math.sqrt(2) * up);
        return curve;
    }

    private QuadCurve createFirstSegment(Vertex firstOne , Vertex secondOne, int vertexRadius , int up){
        int distance = secondOne.getPosition().x - firstOne.getPosition().x;
        int x1 = firstOne.getPosition().x;
        int y1 = firstOne.getPosition().y;
        QuadCurve curve = new QuadCurve(x1 + vertexRadius/Math.sqrt(2),y1 - vertexRadius /Math.sqrt(2) * up,x1 + vertexRadius/Math.sqrt(2),y1 - curveSlopnessFactor*distance * up *controlFactor, x1 + (distance/2 - textSpace/2) ,y1 - curveSlopnessFactor*distance *up);
        return curve;
    }

    private Vertex getFirstVertex(Vertex from, Vertex to) {
         if(from.getPosition().getX() < to.getPosition().getX())
             return from;
         return to;
    }

    private Vertex getSecondVertex(Vertex from, Vertex to) {
        if(from.getPosition().getX() > to.getPosition().getX())
            return from;
        return to;
    }

    public void addEdge(AnchorPane canvas){
        canvas.getChildren().add(firstSegment);
        canvas.getChildren().add(secondSegment);
        canvas.getChildren().add(weightText);
    }

    public void removeEdge(AnchorPane canvas){
        canvas.getChildren().remove(firstSegment);
        canvas.getChildren().remove(secondSegment);
        canvas.getChildren().remove(weightText);
    }

    public void updateWeight(int newWeight){
        this.weight = newWeight;
        weightText.setText(Integer.toString(weight));
    }

    public Point getEdgePair(){
        return new Point(from.getNumber() , to.getNumber());
    }

    public int getWeight(){
        return weight;
    }

}
