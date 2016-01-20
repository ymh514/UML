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
				selectShape.setLayoutX(event.getX() - pressX + shapeLayoutX);
				selectShape.setLayoutY(event.getY() - pressY + shapeLayoutY);
				selectShape.tunePortPosition();
				
				for(int i=0;i<shapeList.size();i++){
					if(shapeList.get(i) instanceof LineObject){
						LineObject tempLine = (LineObject) shapeList.get(i);
						
						tempLine.connectLine.setStartX(tempLine.beginPort.getLayoutXOnCanvas()+tempLine.getLayoutX());
						tempLine.connectLine.setStartY(tempLine.beginPort.getLayoutYOnCanvas()+tempLine.getLayoutY());
						tempLine.connectLine.setEndX(tempLine.endPort.getLayoutXOnCanvas()+tempLine.getLayoutX());
						tempLine.connectLine.setEndY(tempLine.endPort.getLayoutYOnCanvas()+tempLine.getLayoutY());
						shapeList.set(i, tempLine);
						if(tempLine.getClass().getName() == "UML.GeneLine"){
							GeneLine tempGeneLine = (GeneLine) tempLine;
							tempGeneLine.setTriangle();
							tempLine = tempGeneLine;
							
						}
						if(tempLine.getClass().getName() == "UML.CompLine"){
							CompLine tempCompLine = (CompLine) tempLine;
							tempCompLine.setRectangle();
							tempLine = tempCompLine;
						}
					}
				}
			}
			else{
                mouseRectangle.setHeight(event.getY() - pressY);
                mouseRectangle.setWidth(event.getX() - pressX);

			}
		}
		else if(event.getEventType() == MouseEvent.MOUSE_RELEASED ){
			if(selectShape!=null){
				selectShape.setLayoutX(event.getX() - pressX + shapeLayoutX);
				selectShape.setLayoutY(event.getY() - pressY + shapeLayoutY);
				selectShape.tunePortPosition();
	
	//			/*
	//			 * debug
	//			 * 
	//			 */
	//			BasicObject tempObj = (BasicObject) selectShape;
	//			for(int i=0;i<tempObj.portList.size();i++){
	//				System.out.println("port "+i+" x:" +tempObj.portList.get(i).getX());
	//				System.out.println("port "+i+" y:" +tempObj.portList.get(i).getY());
	//				
	//			}
				
				/*
				 *  fixx
				 */
				
				for(int i=0;i<shapeList.size();i++){
					if(shapeList.get(i) instanceof LineObject){
	//					System.out.println("%%%%%%%%%%%% : "+i);
						LineObject tempLine = (LineObject) shapeList.get(i);
						
	
	//					System.out.println(tempLine.beginPort.getX());
	//					System.out.println(tempLine.beginPort.getY());
	//					System.out.println(tempLine.endPort.getX());
	//					System.out.println(tempLine.endPort.getY());
						
	//					System.out.println(tempLine.beginPort.getLayoutX());
	//					System.out.println(tempLine.beginPort.getLayoutY());
	//					System.out.println(tempLine.endPort.getLayoutX());
	//					System.out.println(tempLine.endPort.getLayoutY());
						
	//					System.out.println(tempLine.beginPort.getLayoutXOnCanvas());
	//					System.out.println(tempLine.beginPort.getLayoutYOnCanvas());
	//					System.out.println(tempLine.endPort.getLayoutXOnCanvas());
	//					System.out.println(tempLine.endPort.getLayoutYOnCanvas());
						
						tempLine.connectLine.setStartX(tempLine.beginPort.getLayoutXOnCanvas()+tempLine.getLayoutX());
						tempLine.connectLine.setStartY(tempLine.beginPort.getLayoutYOnCanvas()+tempLine.getLayoutY());
						tempLine.connectLine.setEndX(tempLine.endPort.getLayoutXOnCanvas()+tempLine.getLayoutX());
						tempLine.connectLine.setEndY(tempLine.endPort.getLayoutYOnCanvas()+tempLine.getLayoutY());
						
						if(tempLine.getClass().getName() == "UML.GeneLine"){
							GeneLine tempGeneLine = (GeneLine) tempLine;
							tempGeneLine.setTriangle();
							tempLine = tempGeneLine;
							
						}
						if(tempLine.getClass().getName() == "UML.CompLine"){
							CompLine tempCompLine = (CompLine) tempLine;
							tempCompLine.setRectangle();
							tempLine = tempCompLine;
						}
	
						shapeList.set(i, tempLine);
					}
				}
				/*
				 *  fixx
				 */
			}
			else{
                super.canvas.getChildren().remove(mouseRectangle);
                Point2D startPoint = new Point2D(mouseRectangle.getX(), mouseRectangle.getY());
                Point2D endPoint = new Point2D(event.getX(), event.getY());
                this.checkShapeInRange(startPoint,endPoint);
//        		for(int i=0;i<shapeList.size();i++){
//        			System.out.println(shapeList.get(i).getSelectState());
//        		}
			}
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
