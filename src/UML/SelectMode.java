package UML;

import java.util.ArrayList;

import javafx.scene.input.MouseEvent;

public class SelectMode extends Mode{
	//半成不能用 不完整 沒有選取
	
	private BasicObject selectShape;
	private double shapeLayoutX;
	private double shapeLayoutY;
	private double pressX;
	private double pressY;
	public SelectMode(ArrayList<Shape> shapeList, Canvas canvas) {
		super(shapeList, canvas);
		// TODO Auto-generated constructor stub

	}
	//testttt
	@Override
	public void handle(MouseEvent event) {
		if(event.getEventType() == MouseEvent.MOUSE_PRESSED){
			System.out.println("target :"+event.getTarget());
			if(event.getTarget() instanceof BasicObject){
				selectShape = new BasicObject(0, 0);
				selectShape = (BasicObject) event.getTarget();
				selectShape.port1.setVisible(true);
				selectShape.port2.setVisible(true);
				selectShape.port3.setVisible(true);
				selectShape.port4.setVisible(true);
				shapeLayoutX = selectShape.getLayoutX();
				shapeLayoutY = selectShape.getLayoutY();
				pressX = event.getX();
				pressY = event.getY();
			}
		}
		else if(event.getEventType() == MouseEvent.MOUSE_DRAGGED){
			selectShape.setLayoutX(event.getX() - pressX + shapeLayoutX);
			selectShape.setLayoutY(event.getY() - pressY + shapeLayoutY);
		}
		else if(event.getEventType() == MouseEvent.MOUSE_RELEASED){
			selectShape.setLayoutX(event.getX() - pressX + shapeLayoutX);
			selectShape.setLayoutY(event.getY() - pressY + shapeLayoutY);
		}
	}

}
