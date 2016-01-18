package UML;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

public class SelectMode extends Mode{
	
	private Shape selectShape;
	private ArrayList<Shape> shapeList;
	private double shapeLayoutX;
	private double shapeLayoutY;
	private double pressX;
	private double pressY;
	public SelectMode(ArrayList<Shape> shapeList, Canvas canvas) {
		super(shapeList, canvas);
		this.shapeList=shapeList;
		// TODO Auto-generated constructor stub

	}
	@Override
	public void handle(MouseEvent event) {
		if(event.getEventType() == MouseEvent.MOUSE_PRESSED){
			System.out.println("select target :"+event.getTarget());
			if(event.getTarget() instanceof Shape){
				clearSelect();
				selectShape = (Shape) event.getTarget();
				System.out.println(selectShape.getClass().getSuperclass().getSuperclass().getName());
//                Point2D[] points = selectShape.getBoundary();
//				System.out.println(points[0].getX()+" "+points[0].getY());
//				System.out.println(points[1].getX()+" "+points[1].getY());

				selectShape.setShowSelect(true);
				selectShape.setSelected(true);
				shapeLayoutX = selectShape.getLayoutX();
				shapeLayoutY = selectShape.getLayoutY();
				pressX = event.getX();
				pressY = event.getY();
			}
			else{
				clearSelect();
			}
		}
		else if(event.getEventType() == MouseEvent.MOUSE_DRAGGED && selectShape!=null){
			selectShape.setLayoutX(event.getX() - pressX + shapeLayoutX);
			selectShape.setLayoutY(event.getY() - pressY + shapeLayoutY);
		}
		else if(event.getEventType() == MouseEvent.MOUSE_RELEASED && selectShape!=null){
			selectShape.setLayoutX(event.getX() - pressX + shapeLayoutX);
			selectShape.setLayoutY(event.getY() - pressY + shapeLayoutY);
		}
	}

	public void clearSelect(){
		for(int i=0;i<shapeList.size();i++){
			shapeList.get(i).setShowSelect(false);
			shapeList.get(i).setSelected(false);
			selectShape = null;
		}
	}
}
