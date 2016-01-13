package UML;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.text.Text;

public class BasicObject extends Shape {
	
	protected double height = 150;
	protected double halfHeight = 75;
	protected double width = 120;
	protected double halfWidth = 60;

    protected Text text;
    protected Boolean portVisibility = false;

    public BasicObject(double w, double h) {
		System.out.println("Basic obj constructor");
        width = w;
        halfWidth = w/2;
        height = h;
        halfHeight = h/2;
        text = new Text("");
        this.getChildren().add(text);
    }
    public Shape draw(){
		return null;
    }
}
