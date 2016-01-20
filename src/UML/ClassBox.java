package UML;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class ClassBox extends BasicObject{

	public ClassBox(double x, double y) {
		super(120 ,150);
        this.setLayoutX(x);
        this.setLayoutY(y);
        Rectangle rectangle =  new Rectangle(width,height);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.GAINSBORO);
        rectangle.setDisable(true);

        Line line1 = new Line(0, 50, width, 50);
        Line line2 = new Line(0, 100, width, 100);
        line1.setDisable(true);
        line2.setDisable(true);

        text = new Text();
        text.setText(" '' ");
        text.setX(30);
        text.setY(23);
        text.setDisable(true);

        super.tunePortPosition();
        
        this.getChildren().addAll(rectangle, line1, line2, text);
		System.out.println("class Box constructor");
	}
	
	public void draw(Canvas canvas){
		canvas.getChildren().add(this);
	}

}
