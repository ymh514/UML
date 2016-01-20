package UML;

import java.util.ArrayList;

import javax.xml.ws.EndpointReference;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GroupObject extends Shape{

	protected double groupHeight;
	protected double groupWidth;
	protected Rectangle rectangle;
	
	protected ArrayList<Shape> componentList = new ArrayList<Shape>();
	
	public GroupObject() {
		// TODO Auto-generated constructor stub
	}
	public ArrayList<Shape> getComponentList(){
		return this.componentList;
	}

}
