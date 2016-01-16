package UML;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class BasicObject extends Shape {
	
	protected double height = 150;
	protected double halfHeight = 75;
	protected double width = 120;
	protected double halfWidth = 60;
    protected double portSize = 10;
    protected double halfPortSize = 5;

    protected Text text;
    protected Boolean portVisibility = false;

    protected Port port1;
    protected Port port2;
    protected Port port3;
    protected Port port4;
    
    public BasicObject(double w, double h) {
		System.out.println("Basic obj constructor");
        width = w;
        halfWidth = w/2;
        height = h;
        halfHeight = h/2;
//        text = new Text("");
        
        port1 = new Port(-portSize, halfHeight - halfPortSize, portSize, portSize);
        port2 = new Port(halfWidth - halfPortSize, -portSize, portSize, portSize);
        port3 = new Port(halfWidth - halfPortSize, height, portSize, portSize);
        port4 = new Port(width, halfHeight - halfPortSize, portSize, portSize);
        
        this.getChildren().addAll(port1,port2,port3,port4);
//        this.getChildren().add(text);
    }
    public Shape draw(){
		return null;
    }
}
