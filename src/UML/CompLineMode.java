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
	private CompLine compLine;
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
            
            startShape = checkShape(pressX, pressY);
            
            if(startShape!=null){
        		System.out.println("find start shape");
                Point2D startPort = getClosestPortDist((BasicObject)startShape,pressX,pressY);
                compLine = new CompLine();
                compLine.setBeginPort(getClosestPort((BasicObject)startShape, pressX, pressY));
                
                showLine = new Line(startPort.getX(), startPort.getY(),
                		startPort.getX(), startPort.getY());
                compLine.connectLine.setStartX(startPort.getX());
                compLine.connectLine.setStartY(startPort.getY());
                canvas.getChildren().add(showLine);
            }else{
        		System.out.println("can't find start shape");
            	startShape = null;
            	compLine = null;
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
            
            endShape = checkShape(pressX, pressY);
   
            if(endShape!=null){
        		System.out.println("find end shape");
                Point2D endPort = getClosestPortDist((BasicObject)endShape,pressX,pressY);
                compLine.endPort = getClosestPort((BasicObject)endShape, pressX, pressY);
                
                compLine.connectLine.setEndX(endPort.getX());
                compLine.connectLine.setEndY(endPort.getY());
                
                compLine.setRectangle();
                
                compLine.draw(canvas);
                shapeList.add(compLine);
               
            }
		}
	}
}
