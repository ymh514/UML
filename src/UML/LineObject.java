package UML;

import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class LineObject extends Shape {
	
    protected Shape startShape;
    protected Shape endShape;
    protected Line connectLine;
    protected Rectangle geneObj;
    protected Polygon compObj;
    
	public LineObject() {
		connectLine = new Line();
		this.getChildren().add(connectLine);
		System.out.println("Line Object constructor");

		// TODO Auto-generated constructor stub
	}

    public void setStartShape(Shape shape) {
    	startShape = shape;

    }

    public void setEndShape(Shape shape) {
    	endShape = shape;
    }

}
