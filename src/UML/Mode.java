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
import javafx.scene.text.Text;

public class Mode implements EventHandler<MouseEvent>{
	
	protected Canvas canvas;
	protected Shape newShape;
	protected ArrayList<Shape> shapeList;
	
	@Override
	public void handle(MouseEvent event) {
		// TODO Auto-generated method stub
		if(event.getEventType() == MouseEvent.MOUSE_PRESSED){
			System.out.println("Mouse Pressed");
		}
		else if(event.getEventType() == MouseEvent.MOUSE_DRAGGED){
			System.out.println("Mouse Dragged");
		}
		else if(event.getEventType() == MouseEvent.MOUSE_RELEASED){
			System.out.println("Mouse Realeased"); 
		}
	}
	
	public Mode(ArrayList<Shape> shapeList,Canvas canvasPane){
		this.shapeList = shapeList;
		this.canvas = canvasPane;
	}
	

	public Shape checkShape(double x,double y){

		/*
		 *  start from the tail for the depth info.
		 */
        for(int i=this.shapeList.size()-1; i>=0; i--) {
//        		System.out.println("this : "+this.shapeList.get(i).getClass().getSuperclass().getSuperclass().getName());
            if(this.shapeList.get(i).getClass().getSuperclass().getName() == "UML.BasicObject") {

                Shape tempShape = this.shapeList.get(i);
                System.out.println("-------------------------");
                System.out.println(i+" : "+tempShape.getClass().getName());
                System.out.println("-------------------------");
                Point2D[] points = tempShape.getBoundary();
//                System.out.print(tempShape.getBoundary());
                if(x >points[0].getX() && x < points[1].getX() && y > points[0].getY() && y < points[1].getY()){
                    return tempShape;
                }
//            } else if(this.canvas.getChildren().get(i).getClass().getName() == "UML.GroupPane") {
//                GroupPane tempShape = (GroupPane)this.canvas.getChildren().get(i);
//                Point2D[] points = tempShape.getBoundary();
//                if(x >points[0].getX() && x < points[1].getX() && y > points[0].getY() && y < points[1].getY()){
//                    return tempShape;
//                }
            }
        }
        return null;
	}
	
	public void checkShapeInRange(Point2D inputStartPoint,Point2D inputEndPoint){
		
		Point2D startPoint = inputStartPoint;
		Point2D endPoint = inputEndPoint;

		/*
		 *  start from the tail for the depth info.
		 *  without group
		 */
        for(int i=this.shapeList.size()-1; i>=0; i--) {
            if(this.shapeList.get(i).getClass().getSuperclass().getName() == "UML.BasicObject") { // maybe change shape and add line
                Shape tempShape = this.shapeList.get(i);
                Point2D[] points = tempShape.getBoundary();
                if(startPoint.getX()<points[0].getX() && startPoint.getY()<points[0].getY() && endPoint.getX()>points[1].getX() && endPoint.getY()>points[1].getY()){
                	tempShape.setSelected(true);

                	this.shapeList.set(i, tempShape);
                }
            }
            else if(this.shapeList.get(i).getClass().getSuperclass().getName() == "UML.LineObject"){
            	LineObject tempLine = (LineObject) this.shapeList.get(i);
            	if(startPoint.getX()<tempLine.connectLine.getStartX() && startPoint.getY()<tempLine.connectLine.getStartY() && endPoint.getX()>tempLine.connectLine.getEndX() && endPoint.getY()>tempLine.connectLine.getEndY()){
            		tempLine.setSelected(true);
                	this.shapeList.set(i, tempLine);
            	}
            	
            }
            else if(this.shapeList.get(i).getClass().getName() == "UML.GroupObject"){

                Shape tempShape = this.shapeList.get(i);
                Point2D[] points = tempShape.getBoundary();

                if(startPoint.getX()<points[0].getX() && startPoint.getY()<points[0].getY() && endPoint.getX()>points[1].getX() && endPoint.getY()>points[1].getY()){
                	tempShape.setSelected(true);

                	this.shapeList.set(i, tempShape);
                }
            	
            } 
        }
	}

	
	public Point2D getClosestPortDist(BasicObject endShape,double pressX,double pressY){
		
		Point2D pressCoor = new Point2D(pressX-endShape.getLayoutX(),pressY-endShape.getLayoutY());
		Point2D returnCoor = null;
		Point2D shapeLayout = new Point2D(endShape.getLayoutX(),endShape.getLayoutY());
		double distWizPorts[] = {0,0,0,0};
		for(int i=0;i<endShape.portList.size();i++){
			distWizPorts[i] = Math.hypot(pressCoor.getX()-endShape.portList.get(i).getX(), pressCoor.getY()-endShape.portList.get(i).getY());
		}
		double distTemp = Double.MIN_VALUE;
		for(int i=0;i<distWizPorts.length;i++){
			if(i==0){
				distTemp = distWizPorts[i];
			}
			if(distWizPorts[i]<distTemp){
				distTemp = distWizPorts[i];
			}
		}
		for(int i=0;i<distWizPorts.length;i++){
			if(distTemp == distWizPorts[0]){
				System.out.println("near port1");
				returnCoor = new Point2D(endShape.port1.getX()+shapeLayout.getX()+endShape.portSize, endShape.port1.getY()+shapeLayout.getY()+endShape.halfPortSize);
			}
			else if(distTemp == distWizPorts[1]){
				System.out.println("near port2");
				returnCoor = new Point2D(endShape.port2.getX()+shapeLayout.getX()+endShape.halfPortSize, endShape.port2.getY()+shapeLayout.getY()+endShape.portSize);
			}
			else if(distTemp == distWizPorts[2]){
				System.out.println("near port3");
				returnCoor = new Point2D(endShape.port3.getX()+shapeLayout.getX()+endShape.halfPortSize, endShape.port3.getY()+shapeLayout.getY());
			}
			else if(distTemp == distWizPorts[3]){
				System.out.println("near port4");
				returnCoor = new Point2D(endShape.port4.getX()+shapeLayout.getX(), endShape.port4.getY()+shapeLayout.getY()+endShape.halfPortSize);
			}
		}
		return returnCoor;
	}
	public Port getClosestPort(BasicObject endShape,double pressX,double pressY){
		
		Point2D pressCoor = new Point2D(pressX-endShape.getLayoutX(),pressY-endShape.getLayoutY());
		Port returnPort = null;

		double distWizPorts[] = {0,0,0,0};
		for(int i=0;i<endShape.portList.size();i++){
			distWizPorts[i] = Math.hypot(pressCoor.getX()-endShape.portList.get(i).getX(), pressCoor.getY()-endShape.portList.get(i).getY());
		}
		double distTemp = Double.MIN_VALUE;
		for(int i=0;i<distWizPorts.length;i++){
			if(i==0){
				distTemp = distWizPorts[i];
			}
			if(distWizPorts[i]<distTemp){
				distTemp = distWizPorts[i];
			}
		}
		for(int i=0;i<distWizPorts.length;i++){

			if(distTemp == distWizPorts[i]){
				returnPort = endShape.portList.get(i);
			}
		}
		return returnPort;
	}
	public void unSelectAllShape(){
		for(int i=0;i<shapeList.size();i++){
			shapeList.get(i).setSelected(false);
			shapeList.get(i).setShowSelect(false);
		}
	}
	

}
