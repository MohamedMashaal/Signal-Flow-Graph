import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;

import java.awt.*;

/**
 * Created by Mohamed Mashaal on 4/21/2018.
 */
public class Edge {
    private static final Color curvesColor = Color.web("#3D3B35");
    private static final Color weightColor = Color.BLACK;
    private static final int strokeWidth = 2;
    private static final int selfLoopRadius = 13;
    private Color canvasBackground;
    private QuadCurve curve;
    private CubicCurve selfLoop;
    private Text weightText;
    private StackPane stackPane;
    private static final int textSpace = 30;
    private static final double curveSlopnessFactor = .4;
    private static final double controlFactor = .9;
    private int weight;
    private Vertex from;
    private Vertex to;
    private Line arrowSegment1;
    private Line arrowSegment2;
    private static final int arrowSegmentLength = 8;

    public Edge(Vertex from , Vertex to , int vertexRadius , int weight , Color canvasBackground){
        this.weight = weight;
        this.from = from;
        this.to=to;
        this.canvasBackground = canvasBackground;
        if(from.getNumber() != to.getNumber()){
            Vertex firstOne = getFirstVertex(from,to);
            Vertex secondOne = getSecondVertex(from,to);
            int up = firstOne == from ? 1 : -1;
            curve = createCurve(firstOne, secondOne, vertexRadius , up);
            curveStyle();
            createWeight(firstOne , secondOne,up);
            createArrow(up , false);
            arrowStyle();
        }
        else{
            selfLoop = createSelfLoop(from, vertexRadius);
            createWeight(from);
            createArrow(-1 , true);
            arrowStyle();
        }
    }

    private void arrowStyle() {
        arrowSegment1.setStroke(curvesColor);
        arrowSegment1.setStrokeWidth(strokeWidth);
        arrowSegment2.setStroke(curvesColor);
        arrowSegment2.setStrokeWidth(strokeWidth);
    }

    private void createArrow(int up , boolean self) {
        Point endPoint;
        if(self){
            endPoint = new Point((int)selfLoop.getEndX(),(int)selfLoop.getEndY() - 1);
        }
        else{
            if( up == 1){
                endPoint = new Point ((int)curve.getEndX() +1 ,(int) curve.getEndY() + 1);
            }
            else{
                endPoint = new Point ((int)curve.getStartX() ,(int) curve.getStartY() - 1);
            }
        }
        if(up == 1){
            arrowSegment1 = new Line(endPoint.getX() , endPoint.getY() , endPoint.getX() , endPoint.getY() - arrowSegmentLength);
            arrowSegment2 = new Line(endPoint.getX() , endPoint.getY(), endPoint.getX() - arrowSegmentLength , endPoint.getY());

        }
        else{
            arrowSegment1 = new Line(endPoint.getX() , endPoint.getY(), endPoint.getX() + arrowSegmentLength , endPoint.getY());
            arrowSegment2 = new Line(endPoint.getX() , endPoint.getY(), endPoint.getX() , endPoint.getY() + arrowSegmentLength);
        }
    }

    private void createWeight(Vertex from) {
        weightText = new Text(Integer.toString(weight));
        weightText.setFill(weightColor);
        stackPane = new StackPane();
        stackPane.getChildren().add(weightText);
        stackPane.setLayoutX(selfLoop.getBoundsInLocal().getMaxX() - weightText.getBoundsInLocal().getWidth()/2);
        stackPane.setLayoutY(from.getPosition().y - weightText.getBoundsInLocal().getHeight() / 2);
        stackPane.setBackground(new Background(new BackgroundFill(canvasBackground , CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));
    }

    private CubicCurve createSelfLoop(Vertex vertex , int vertexRadius) {
        CubicCurve curve = new CubicCurve((vertex.getPosition().x + vertexRadius * Math.sqrt(3) / 2 ) + 2 , vertex.getPosition().y - vertexRadius/ 2, vertex.getPosition().x + 70 , vertex.getPosition().y - 50,vertex.getPosition().x + 70 , vertex.getPosition().y + 50,(vertex.getPosition().x + vertexRadius * Math.sqrt(3) / 2) + 2 , vertex.getPosition().y + vertexRadius/ 2);
        curve.setFill(Color.TRANSPARENT);
        curve.setStroke(curvesColor);
        curve.setStrokeWidth(strokeWidth);
        return curve;
    }

    private void createWeight(Vertex firstOne, Vertex secondOne, int up) {
        int distance = secondOne.getPosition().x - firstOne.getPosition().x;
        weightText = new Text(Integer.toString(weight));
        weightText.setFill(weightColor);
        stackPane = new StackPane();
        stackPane.getChildren().add(weightText);
        stackPane.setLayoutX(firstOne.getPosition().x + distance/2 - weightText.getBoundsInLocal().getWidth()/2);
        if(up == 1) {
            stackPane.setLayoutY(curve.getBoundsInLocal().getMinY() - weightText.getBoundsInLocal().getHeight() / 2);
        }
        else{
            stackPane.setLayoutY(curve.getBoundsInLocal().getMaxY() - weightText.getBoundsInLocal().getHeight() / 2);
        }
        stackPane.setBackground(new Background(new BackgroundFill(canvasBackground , CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));
    }

    private void curveStyle() {
        curve.setFill(Color.TRANSPARENT);
        curve.setStroke(curvesColor);
        curve.setStrokeWidth(strokeWidth);
    }

    private QuadCurve createCurve(Vertex firstOne , Vertex secondOne, int vertexRadius , int up){
        int distance = secondOne.getPosition().x - firstOne.getPosition().x;
        int x1 = firstOne.getPosition().x;
        int y1 = firstOne.getPosition().y;
        int x2 = secondOne.getPosition().x;
        int y2 = secondOne.getPosition().y;
        QuadCurve curve = new QuadCurve((x1 + vertexRadius/Math.sqrt(2)) +2 ,y1 - vertexRadius /Math.sqrt(2) * up,x1 + distance/2,y1 - curveSlopnessFactor*distance * up *controlFactor, x2 - vertexRadius/Math.sqrt(2) ,y2 - vertexRadius /Math.sqrt(2) * up );
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
        if (!(curve == null)) {
            canvas.getChildren().add(curve);
        }
        else{
            canvas.getChildren().add(selfLoop);
        }
        canvas.getChildren().add(arrowSegment1);
        canvas.getChildren().add(arrowSegment2);
        canvas.getChildren().add(stackPane);
    }

    public void removeEdge(AnchorPane canvas){
        if (!(curve == null)) {
            canvas.getChildren().remove(curve);
        }
        else{
            canvas.getChildren().remove(selfLoop);
        }
        canvas.getChildren().remove(arrowSegment1);
        canvas.getChildren().remove(arrowSegment2);
        canvas.getChildren().remove(stackPane);
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
