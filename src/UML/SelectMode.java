package UML;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SelectMode extends Mode{
	
	private Shape selectShape;
	private ArrayList<Shape> shapeList;
	private double shapeLayoutX;
	private double shapeLayoutY;
	private double pressX;
	private double pressY;
	private Rectangle mouseRectangle;
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
				selectShape.setSelected(true);
				shapeLayoutX = selectShape.getLayoutX();
				shapeLayoutY = selectShape.getLayoutY();
				pressX = event.getX();
				pressY = event.getY();
			}
			else{
				clearSelect();
                mouseRectangle = new Rectangle();
                super.canvas.getChildren().add(mouseRectangle);
                pressX = event.getX();
                pressY = event.getY();
                mouseRectangle.setX(pressX);
                mouseRectangle.setY(pressY);
                mouseRectangle.setHeight(0);
                mouseRectangle.setWidth(0);
                mouseRectangle.setFill(Color.TRANSPARENT);
                mouseRectangle.setStroke(Color.BLACK);

			}
		}
		
		else if(event.getEventType() == MouseEvent.MOUSE_DRAGGED){
			if(selectShape!=null){
//				selectObjAndTuneLine(event.getX(),event.getY());
				selectShape.setLayoutX(event.getX() - pressX + shapeLayoutX);
				selectShape.setLayoutY(event.getY() - pressY + shapeLayoutY);
				selectShape.tunePortPosition();
				
				canvas.getChildren().clear();
				for(int i=0;i<shapeList.size();i++){
					shapeList.get(i).draw(canvas);
				}
			}
			else{
                mouseRectangle.setHeight(event.getY() - pressY);
                mouseRectangle.setWidth(event.getX() - pressX);
			}
		}
		
		else if(event.getEventType() == MouseEvent.MOUSE_RELEASED ){
			if(selectShape!=null){
//				selectObjAndTuneLine(event.getX(),event.getY());
				selectShape.setLayoutX(event.getX() - pressX + shapeLayoutX);
				selectShape.setLayoutY(event.getY() - pressY + shapeLayoutY);
				selectShape.tunePortPosition();
				
				canvas.getChildren().clear();
				for(int i=0;i<shapeList.size();i++){
					shapeList.get(i).draw(canvas);
				}
			}
			else{
                super.canvas.getChildren().remove(mouseRectangle);
                Point2D startPoint = new Point2D(mouseRectangle.getX(), mouseRectangle.getY());
                Point2D endPoint = new Point2D(event.getX(), event.getY());
                this.checkShapeInRange(startPoint,endPoint);
			}
		}
	}
	
	public void clearSelect(){
		for(int i=0;i<shapeList.size();i++){
			shapeList.get(i).setSelected(false);
			selectShape = null;
		}
	}
}
