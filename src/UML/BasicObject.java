package UML;

import java.util.ArrayList;

import com.sun.swing.internal.plaf.basic.resources.basic;

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
    protected ArrayList<Point2D> portCoordinate;

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
        
//        System.out.println("port1-- x:"+port1.getX()+" y:"+port1.getY());
//        System.out.println("port2-- x:"+port2.getX()+" y:"+port2.getY());
//        System.out.println("port3-- x:"+port3.getX()+" y:"+port3.getY());
//        System.out.println("port4-- x:"+port4.getX()+" y:"+port4.getY());

        this.getChildren().addAll(port1,port2,port3,port4);
//        this.getChildren().add(text);
    }
    public void setShowSelect(Boolean showValue){
    	this.port1.setVisible(showValue);
    	this.port2.setVisible(showValue);
    	this.port3.setVisible(showValue);
    	this.port4.setVisible(showValue);
    }
    public Shape draw(){
		return null;
    }
    
    public Point2D[] getBoundary() {
        Point2D[] points = new Point2D[2];
        points[0] = new Point2D(this.getLayoutX(), this.getLayoutY());
        points[1] = new Point2D(this.getLayoutX() + width, this.getLayoutY() + height);
        return points;
    }
}
