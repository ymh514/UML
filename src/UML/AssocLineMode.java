package UML;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class AssocLineMode extends Mode {
	private Shape startShape;
	private Shape endShape;
	private double pressX;
	private double pressY;
	private AssocLine assocLine;

	private Line showLine;

	public AssocLineMode(ArrayList<Shape> shapeList, Canvas canvas) {
		super(shapeList, canvas);
//		newLine = new AssocLine();
	}
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
                assocLine = new AssocLine();

				assocLine.setBeginPort(getClosestPort((BasicObject) startShape, pressX, pressY));
				showLine = new Line(startPort.getX(), startPort.getY(), startPort.getX(), startPort.getY());
				assocLine.connectLine.setStartX(startPort.getX());
				assocLine.connectLine.setStartY(startPort.getY());
				canvas.getChildren().add(showLine);
			} else {
				startShape = null;
				assocLine = null;
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
						assocLine.endPort = getClosestPort((BasicObject) endShape, pressX, pressY);
						assocLine.connectLine.setEndX(endPort.getX());
						assocLine.connectLine.setEndY(endPort.getY());
						assocLine.setEndObj();
						assocLine.draw(canvas);
						shapeList.add(assocLine);
					}

		}

	}

}
