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
	protected ArrayList<Port> portList = new ArrayList<>(4);
	protected Port port1;
	protected Port port2;
	protected Port port3;
	protected Port port4;

	public BasicObject(double w, double h) {

		width = w;
		halfWidth = w / 2;
		height = h;
		halfHeight = h / 2;

		// left
		port1 = new Port(-portSize, halfHeight - halfPortSize, portSize, portSize);
		// top
		port2 = new Port(halfWidth - halfPortSize, -portSize, portSize, portSize);
		// bottom
		port3 = new Port(halfWidth - halfPortSize, height, portSize, portSize);
		// right
		port4 = new Port(width, halfHeight - halfPortSize, portSize, portSize);

		portList.add(port1);
		portList.add(port2);
		portList.add(port3);
		portList.add(port4);

		this.getChildren().addAll(port1, port2, port3, port4);
	}

	public void setName(String name) {
		this.text.setText(name);
	}

	public Point2D[] getBoundary() {
		Point2D[] points = new Point2D[2];
		points[0] = new Point2D(this.getLayoutX(), this.getLayoutY());
		points[1] = new Point2D(this.getLayoutX() + width, this.getLayoutY() + height);
		return points;
	}

	public void tunePortPosition() {

		for (int i = 0; i < portList.size(); i++) {

			if (i == 0) {
				portList.get(i).setLayoutXOnCanvas(this.getLayoutX() + portList.get(i).getX() + this.portSize);
				portList.get(i).setLayoutYOnCanvas(this.getLayoutY() + portList.get(i).getY() + this.halfPortSize);
			} else if (i == 1) {
				portList.get(i).setLayoutXOnCanvas(this.getLayoutX() + portList.get(i).getX() + this.halfPortSize);
				portList.get(i).setLayoutYOnCanvas(this.getLayoutY() + portList.get(i).getY() + this.portSize);
			} else if (i == 2) {
				portList.get(i).setLayoutXOnCanvas(this.getLayoutX() + portList.get(i).getX() + this.halfPortSize);
				portList.get(i).setLayoutYOnCanvas(this.getLayoutY() + portList.get(i).getY());
			} else if (i == 3) {
				portList.get(i).setLayoutXOnCanvas(this.getLayoutX() + portList.get(i).getX());
				portList.get(i).setLayoutYOnCanvas(this.getLayoutY() + portList.get(i).getY() + this.halfPortSize);
			}
		}
	}

	@Override
	public void unGroup(ArrayList<Shape> shapeList) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSelected(Boolean selectState) {
		// TODO Auto-generated method stub
		super.selectState = selectState;
		this.setShowSelect(selectState);
	}

	public void setShowSelect(Boolean showValue) {
		this.port1.setVisible(showValue);
		this.port2.setVisible(showValue);
		this.port3.setVisible(showValue);
		this.port4.setVisible(showValue);
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub

	}

}
