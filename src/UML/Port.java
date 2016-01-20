package UML;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

public class Port extends Rectangle {

	double layoutXOnCanvas;
	double layoutYOnCanvas;

	public Port(double x, double y, double width, double height) {
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);
		this.setVisible(false);
	}

	public void setLayoutXOnCanvas(double xOnCanvas) {
		this.layoutXOnCanvas = xOnCanvas;
	}

	public void setLayoutYOnCanvas(double yOnCanvas) {
		this.layoutYOnCanvas = yOnCanvas;
	}

	public double getLayoutXOnCanvas() {
		return this.layoutXOnCanvas;
	}

	public double getLayoutYOnCanvas() {
		return this.layoutYOnCanvas;
	}
}
