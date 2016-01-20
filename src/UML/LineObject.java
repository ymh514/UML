package UML;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class LineObject extends Shape {

	protected Line connectLine;
	protected Port beginPort;
	protected Port endPort;
	protected Rectangle geneObj;
	protected Polygon compObj;

	public LineObject() {
		connectLine = new Line();
		this.getChildren().add(connectLine);
	}

	public void draw(Canvas canvas) {

		this.setSelected(false);
		this.setConnectLineXY();
		this.setEndObj();
		canvas.getChildren().add(this.connectLine);
		canvas.getChildren().add(this.getEndObj());
	}

	public void setBeginPort(Port beginPort) {
		this.beginPort = beginPort;
	}

	public void setEndPort(Port endPort) {
		this.endPort = endPort;
	}

	public void setEndObj() {

	}

	public Polygon getEndObj() {
		return null;
	}

	public void setConnectLineXY() {
		this.connectLine.setStartX(this.beginPort.getLayoutXOnCanvas());
		this.connectLine.setStartY(this.beginPort.getLayoutYOnCanvas());
		this.connectLine.setEndX(this.endPort.getLayoutXOnCanvas());
		this.connectLine.setEndY(this.endPort.getLayoutYOnCanvas());
	}

	public void setConnectLineXYinGroup() {
		/*
		 * cause now we in a group so line's x,y need to add this group's
		 * layoutXY
		 */
		this.connectLine.setStartX(this.beginPort.getLayoutXOnCanvas() + this.getLayoutX());
		this.connectLine.setStartY(this.beginPort.getLayoutYOnCanvas() + this.getLayoutY());
		this.connectLine.setEndX(this.endPort.getLayoutXOnCanvas() + this.getLayoutX());
		this.connectLine.setEndY(this.endPort.getLayoutYOnCanvas() + this.getLayoutY());
	}

	@Override
	public Point2D[] getBoundary() {

		Point2D[] points = new Point2D[2];
		points[0] = new Point2D(this.connectLine.getStartX(), this.connectLine.getStartY());
		points[1] = new Point2D(this.connectLine.getEndY(), this.connectLine.getEndY());

		return points;
	}


	@Override
	public void unGroup(ArrayList<Shape> shapeList) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tunePortPosition() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSelected(Boolean selectState) {
		// TODO Auto-generated method stub
		super.selectState = selectState;

	}

	@Override
	public void setShowSelect(Boolean showValue) {
		// TODO Auto-generated method stub

	}
}
