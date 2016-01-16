package UML;

import javafx.scene.shape.Rectangle;

public class Port extends Rectangle {

	public Port(double x,double y,double width,double height){
		System.out.println("Port constructor");
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);
		this.setVisible(false);
	}
}
