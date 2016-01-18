package UML;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;

public class AssocLineMode extends Mode{
	

	private Shape startShape;
	private Shape endShape;
	private double pressX;
	private double pressY;
	private AssocLine assocLine;
	private Line showLine;
	
	public AssocLineMode(ArrayList<Shape> shapeList, Canvas canvas) {
		super(shapeList,canvas);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(MouseEvent event) {
		if(event.getEventType() == MouseEvent.MOUSE_PRESSED){
			System.out.println("start association target :"+event.getTarget());
			startShape = null;
			if(event.getTarget() instanceof Shape){
				startShape = (Shape) event.getTarget();
				System.out.println("start target: "+event.getTarget());

                pressX = event.getX();
                pressY = event.getY();
                assocLine = new AssocLine();
                assocLine.setStartShape(startShape);
                Point2D startPort = getClosestPort((BasicObject)startShape,pressX,pressY);
                
                showLine = new Line(startPort.getX(), startPort.getY(),
                		startPort.getX(), startPort.getY());
                
                assocLine.connectLine.setStartX(startPort.getX());
                assocLine.connectLine.setStartY(startPort.getY());
                
                canvas.getChildren().add(showLine);
			}
			else{
			}
		}
		else if(event.getEventType() == MouseEvent.MOUSE_DRAGGED ){
			showLine.setEndX(event.getX());
			showLine.setEndY(event.getY());

		}
		else if(event.getEventType() == MouseEvent.MOUSE_RELEASED ){
          
			canvas.getChildren().remove(showLine);

            pressX = event.getX();
            pressY = event.getY();
            endShape = null;
            Node tempNode = checkShape(pressX, pressY);
            if(tempNode != null) {
                if(tempNode.getClass().getSuperclass().getSuperclass().getName().equals("UML.Shape")) {
                	endShape = checkShape(pressX, pressY);
                }
            }                  
            
            if(endShape!=null){
        		System.out.println("find start shape");
                Point2D endPort = getClosestPort((BasicObject)endShape,pressX,pressY);
                assocLine.connectLine.setEndX(endPort.getX());
                assocLine.connectLine.setEndY(endPort.getY());
               
                canvas.getChildren().add(assocLine.connectLine);
                shapeList.add(assocLine);
                
            	startShape = null;
            	endShape = null;
				showLine = null;
            	pressX = 0;
            	pressY = 0;

            }
                        
//            Point2D endPort = getClosestPort((BasicObject)endShape);
//            assocLine.connectLine.setEndX(endPort.getX());
//            assocLine.connectLine.setEndY(endPort.getY());
//
////            System.out.println("-----------------");
////            System.out.println("start X :"+assocLine.connectLine.getStartX());
////            System.out.println("start Y :"+assocLine.connectLine.getStartY());
////            System.out.println("end X :"+assocLine.connectLine.getEndX());
////            System.out.println("end Y :"+assocLine.connectLine.getEndY());
////            System.out.println("-----------------");
//
//            
//            canvas.getChildren().add(assocLine.connectLine);

		}
	}

}
