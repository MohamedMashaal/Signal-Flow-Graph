import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

import java.awt.*;


/**
 * Created by Mohamed Mashaal on 4/21/2018.
 */
public class Vertex {
    private Circle circle;
    private Text label;
    private static final int strokeWidth = 2;
    private int number;
    private Color color;
    private StackPane stackPane;
    private int xPosition;
    private int yPosition;


    public Vertex(int x, int y, float radius , Color color, int number){
        this.number = number;
        this.color = color;
        this.xPosition = x;
        this.yPosition = y;
        circle = new Circle(radius , Color.TRANSPARENT);
        circle.setStroke(color);
        circle.setStrokeWidth(strokeWidth);
        label = new Text(Integer.toString(number));
        label.setFill(color);
        label.setBoundsType(TextBoundsType.VISUAL);
        stackPane = new StackPane();
        stackPane.getChildren().addAll(circle , label);
        stackPane.setLayoutX(x - radius);
        stackPane.setLayoutY(y - radius);

    }

    public void addVertex(AnchorPane pane ){
        pane.getChildren().add(stackPane);
    }

    public void removeVertex(AnchorPane pane ){
        pane.getChildren().remove(stackPane);
    }

    public Point getPosition(){
        return new Point(xPosition,yPosition);
    }

    public int getNumber(){
        return number;
    }

}
