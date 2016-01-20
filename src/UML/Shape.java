package UML;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public abstract class Shape extends Pane {

	protected boolean selectState = false;

	public Shape() {

	}

	public abstract void draw(Canvas canvas);

	public abstract void setSelected(Boolean selectState);

	public abstract void setShowSelect(Boolean showValue);

	public abstract Point2D[] getBoundary();

	public abstract void unGroup(ArrayList<Shape> shapeList);

	public abstract void tunePortPosition();

	public boolean getSelectState() {
		return selectState;
	}

	public Point2D getGroupRectEndPt(Point2D points, Point2D endPoint, int i) {

		if (i == 0) {
			endPoint = new Point2D(points.getX(), points.getY());

		}
		if (points.getX() >= endPoint.getX()) {
			endPoint = new Point2D(points.getX(), endPoint.getY());
		}
		if (points.getY() >= endPoint.getY()) {
			endPoint = new Point2D(endPoint.getX(), points.getY());
		}
		return endPoint;
	}

	public Point2D getGroupRectStartPt(Point2D points, Point2D startPoint, int i) {

		if (i == 0) {
			startPoint = new Point2D(points.getX(), points.getY());

		}
		if (points.getX() <= startPoint.getX()) {
			startPoint = new Point2D(points.getX(), startPoint.getY());
		}
		if (points.getY() <= startPoint.getY()) {
			startPoint = new Point2D(startPoint.getX(), points.getY());
		}
		return startPoint;

	}

}
