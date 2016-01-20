package UML;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class GeneLineMode extends Mode{
	private Shape startShape;
	private Shape endShape;
	private double pressX;
	private double pressY;
	private GeneLine geneLine;
	private Line showLine;

	public GeneLineMode(ArrayList<Shape> shapeList, Canvas canvas) {
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
                geneLine = new GeneLine();
                geneLine.setBeginPort(getClosestPort((BasicObject)startShape, pressX, pressY));                
                showLine = new Line(startPort.getX(), startPort.getY(),
                		startPort.getX(), startPort.getY());
                geneLine.connectLine.setStartX(startPort.getX());
                geneLine.connectLine.setStartY(startPort.getY());
                canvas.getChildren().add(showLine);
            }else{
        		System.out.println("can't find start shape");
            	startShape = null;
            	geneLine = null;
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
                geneLine.setEndPort(getClosestPort((BasicObject)endShape, pressX, pressY));
                geneLine.connectLine.setEndX(endPort.getX());
                geneLine.connectLine.setEndY(endPort.getY());
                geneLine.setTriangle();
                geneLine.draw(canvas);
                shapeList.add(geneLine);
               
            }
		}
	}

}
