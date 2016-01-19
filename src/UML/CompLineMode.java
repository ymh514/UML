package UML;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class CompLineMode extends Mode{
	private Shape startShape;
	private Shape endShape;
	private double pressX;
	private double pressY;
	private AssocLine assocLine;
	private Line showLine;

	public CompLineMode(ArrayList<Shape> shapeList, Canvas canvas) {
		super(shapeList,canvas);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void handle(MouseEvent event) {

		if(event.getEventType() == MouseEvent.MOUSE_PRESSED){
			startShape = null;
			pressX = event.getX();
            pressY = event.getY();
            
            Node tempNode = checkShape(pressX, pressY);
            if(tempNode != null) {
                if(tempNode.getClass().getSuperclass().getSuperclass().getName().equals("UML.Shape")) {
                	startShape = checkShape(pressX, pressY);
                }
            }             
            
            if(startShape!=null){
        		System.out.println("find start shape");
                Point2D startPort = getClosestPortDist((BasicObject)startShape,pressX,pressY);
                assocLine = new AssocLine();
                assocLine.beginPort = getClosestPort((BasicObject)startShape, pressX, pressY);
                
                showLine = new Line(startPort.getX(), startPort.getY(),
                		startPort.getX(), startPort.getY());
                assocLine.connectLine.setStartX(startPort.getX());
                assocLine.connectLine.setStartY(startPort.getY());
                canvas.getChildren().add(showLine);
            }else{
        		System.out.println("can't find start shape");
            	startShape = null;
            	assocLine = null;
            	showLine = new Line(pressX,pressY,pressX,pressY);
            	showLine.setStroke(Color.RED);
            	canvas.getChildren().add(showLine);
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
        		System.out.println("find end shape");
                Point2D endPort = getClosestPortDist((BasicObject)endShape,pressX,pressY);
                assocLine.endPort = getClosestPort((BasicObject)endShape, pressX, pressY);
                assocLine.connectLine.setEndX(endPort.getX());
                assocLine.connectLine.setEndY(endPort.getY());

                canvas.getChildren().add(assocLine.connectLine);
                shapeList.add(assocLine);
               
            }
//            startShape = null;
//            endShape = null;
//            assocLine = null;
//            showLine = null;
		}
	}
}
