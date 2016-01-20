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
//	    for(int i=0;i<portList.size();i++){
//	    	portList.get(i).setLayoutXOnCanvas(this.getLayoutX()+portList.get(i).getX());
//	    	portList.get(i).setLayoutYOnCanvas(this.getLayoutY()+portList.get(i).getY());
//	    }
	    
//	    for(int i=0;i<portList.size();i++){
//	    	System.out.println("------"+i+"-----");
//	    	System.out.println("layout x :"+portList.get(i).getLayoutXOnCanvas());
//	    	System.out.println("layout y :"+portList.get(i).getLayoutYOnCanvas());
//	    }

        
        this.getChildren().addAll(rectangle, line1, line2, text);
		System.out.println("class Box constructor");
	}

}
