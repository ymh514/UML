package UML;

import java.util.ArrayList;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class Mode implements EventHandler<MouseEvent> {

	protected Canvas canvas;
	protected Shape newShape;
	protected LineObject newLine;
	protected ArrayList<Shape> shapeList;
	/*
	 * under parameter is for line object
	 */
	private Shape startShape;
	private Shape endShape;
	private double pressX;
	private double pressY;
	private Line showLine;

	@Override
	public void handle(MouseEvent event) {
		/*
		 * these code is for line object,basic object will override
		 */
		if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
			startShape = null;
			pressX = event.getX();
			pressY = event.getY();

			startShape = checkShape(pressX, pressY);

			if (startShape != null) {
				Point2D startPort = getClosestPortDist((BasicObject) startShape, pressX, pressY);

				/*
				 * define the new line type
				 */
				if (this.getClass().getName() == "UML.AssocLineMode") {
					newLine = new AssocLine();
				} else if (this.getClass().getName() == "UML.GeneLineMode") {
					newLine = new GeneLine();
				} else if (this.getClass().getName() == "UML.CompLineMode") {
					newLine = new CompLine();
				}

				newLine.setBeginPort(getClosestPort((BasicObject) startShape, pressX, pressY));
				showLine = new Line(startPort.getX(), startPort.getY(), startPort.getX(), startPort.getY());
				newLine.connectLine.setStartX(startPort.getX());
				newLine.connectLine.setStartY(startPort.getY());
				canvas.getChildren().add(showLine);
			} else {
				startShape = null;
				newLine = null;
				showLine = new Line(pressX, pressY, pressX, pressY);
				showLine.setStroke(Color.RED);
				canvas.getChildren().add(showLine);
			}
		} else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
			showLine.setEndX(event.getX());
			showLine.setEndY(event.getY());

		} else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
			canvas.getChildren().remove(showLine);

			pressX = event.getX();
			pressY = event.getY();
			endShape = null;

			endShape = checkShape(pressX, pressY);

			if (endShape != null) {
				Point2D endPort = getClosestPortDist((BasicObject) endShape, pressX, pressY);
				newLine.endPort = getClosestPort((BasicObject) endShape, pressX, pressY);
				newLine.connectLine.setEndX(endPort.getX());
				newLine.connectLine.setEndY(endPort.getY());
				newLine.setEndObj();
				newLine.draw(canvas);
				shapeList.add(newLine);
			}

		}
	}

	public Mode(ArrayList<Shape> shapeList, Canvas canvasPane) {
		this.shapeList = shapeList;
		this.canvas = canvasPane;
	}

	public Shape checkShape(double x, double y) {

		/*
		 * start from the tail for the depth info.
		 */
		for (int i = this.shapeList.size() - 1; i >= 0; i--) {
			if (this.shapeList.get(i).getClass().getSuperclass().getName() == "UML.BasicObject") {

				Shape tempShape = this.shapeList.get(i);
				Point2D[] points = tempShape.getBoundary();
				if (x > points[0].getX() && x < points[1].getX() && y > points[0].getY() && y < points[1].getY()) {
					return tempShape;
				}
			}
		}
		return null;
	}

	public void checkShapeInRange(Point2D inputStartPoint, Point2D inputEndPoint) {

		Point2D startPoint = inputStartPoint;
		Point2D endPoint = inputEndPoint;

		/*
		 * start from the tail for the depth info. without group
		 */
		for (int i = this.shapeList.size() - 1; i >= 0; i--) {

			if (this.shapeList.get(i).getClass().getSuperclass().getName() == "UML.LineObject") {
				LineObject tempLine = (LineObject) this.shapeList.get(i);
				if (startPoint.getX() < tempLine.connectLine.getStartX()
						&& startPoint.getY() < tempLine.connectLine.getStartY()
						&& endPoint.getX() > tempLine.connectLine.getEndX()
						&& endPoint.getY() > tempLine.connectLine.getEndY()) {
					tempLine.setSelected(true);
					this.shapeList.set(i, tempLine);
				}

			} else {
				Shape tempShape = this.shapeList.get(i);
				Point2D[] points = tempShape.getBoundary();

				if (startPoint.getX() < points[0].getX() && startPoint.getY() < points[0].getY()
						&& endPoint.getX() > points[1].getX() && endPoint.getY() > points[1].getY()) {
					tempShape.setSelected(true);

					this.shapeList.set(i, tempShape);
				}

			}
		}
	}

	public Point2D getClosestPortDist(BasicObject endShape, double pressX, double pressY) {

		Point2D pressCoor = new Point2D(pressX - endShape.getLayoutX(), pressY - endShape.getLayoutY());
		Point2D returnCoor = null;
		Point2D shapeLayout = new Point2D(endShape.getLayoutX(), endShape.getLayoutY());
		double distWizPorts[] = { 0, 0, 0, 0 };
		for (int i = 0; i < endShape.portList.size(); i++) {
			distWizPorts[i] = Math.hypot(pressCoor.getX() - endShape.portList.get(i).getX(),
					pressCoor.getY() - endShape.portList.get(i).getY());
		}
		double distTemp = Double.MIN_VALUE;
		for (int i = 0; i < distWizPorts.length; i++) {
			if (i == 0) {
				distTemp = distWizPorts[i];
			}
			if (distWizPorts[i] < distTemp) {
				distTemp = distWizPorts[i];
			}
		}
		for (int i = 0; i < distWizPorts.length; i++) {
			if (distTemp == distWizPorts[0]) {
				returnCoor = new Point2D(endShape.port1.getX() + shapeLayout.getX() + endShape.portSize,
						endShape.port1.getY() + shapeLayout.getY() + endShape.halfPortSize);
			} else if (distTemp == distWizPorts[1]) {
				returnCoor = new Point2D(endShape.port2.getX() + shapeLayout.getX() + endShape.halfPortSize,
						endShape.port2.getY() + shapeLayout.getY() + endShape.portSize);
			} else if (distTemp == distWizPorts[2]) {
				returnCoor = new Point2D(endShape.port3.getX() + shapeLayout.getX() + endShape.halfPortSize,
						endShape.port3.getY() + shapeLayout.getY());
			} else if (distTemp == distWizPorts[3]) {
				returnCoor = new Point2D(endShape.port4.getX() + shapeLayout.getX(),
						endShape.port4.getY() + shapeLayout.getY() + endShape.halfPortSize);
			}
		}
		return returnCoor;
	}

	public Port getClosestPort(BasicObject endShape, double pressX, double pressY) {

		Point2D pressCoor = new Point2D(pressX - endShape.getLayoutX(), pressY - endShape.getLayoutY());
		Port returnPort = null;

		double distWizPorts[] = { 0, 0, 0, 0 };
		for (int i = 0; i < endShape.portList.size(); i++) {
			distWizPorts[i] = Math.hypot(pressCoor.getX() - endShape.portList.get(i).getX(),
					pressCoor.getY() - endShape.portList.get(i).getY());
		}
		double distTemp = Double.MIN_VALUE;
		for (int i = 0; i < distWizPorts.length; i++) {
			if (i == 0) {
				distTemp = distWizPorts[i];
			}
			if (distWizPorts[i] < distTemp) {
				distTemp = distWizPorts[i];
			}
		}
		for (int i = 0; i < distWizPorts.length; i++) {

			if (distTemp == distWizPorts[i]) {
				returnPort = endShape.portList.get(i);
			}
		}
		return returnPort;
	}

	public void unSelectAllShape() {
		for (int i = 0; i < shapeList.size(); i++) {
			shapeList.get(i).setSelected(false);
		}
	}

	public void addBasicObject() {
		this.shapeList.add(this.newShape);
		this.newShape.draw(canvas);

	}

}
