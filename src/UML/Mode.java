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
	
	public Shape getNewShape(){
		return this.newShape;
	}

	public Shape checkShape(double x,double y){
//		System.out.println("in 1 st");

        for(int i=0; i<this.shapeList.size(); i++) {
//        		System.out.println("this : "+this.shapeList.get(i).getClass().getSuperclass().getSuperclass().getName());
            if(this.shapeList.get(i).getClass().getSuperclass().getSuperclass().getName() == "UML.Shape") {

                Shape tempShape = this.shapeList.get(i);
                
                System.out.println(tempShape.getClass().getName());
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
	public Point2D getClosestPort(BasicObject endShape,double pressX,double pressY){
		
		Point2D pressCoor = new Point2D(pressX-endShape.getLayoutX(),pressY-endShape.getLayoutY());
		Point2D returnCoor = null;
		Point2D shapeLayout = new Point2D(endShape.getLayoutX(),endShape.getLayoutY());
//		System.out.println("pressX : "+pressX);
//		System.out.println("pressY : "+pressY);
//		System.out.println("shape layout x : "+shape.getLayoutX());
//		System.out.println("shape layout y : "+shape.getLayoutY());
//		System.out.println("x base on shape : "+(pressX-shape.getLayoutX()));
//		System.out.println("y base on shape : "+(pressY-shape.getLayoutY()));
		
		double distWizPort1 = Math.hypot(pressCoor.getX()-endShape.port1.getX(), pressCoor.getY()-endShape.port1.getY());
		double distWizPort2 = Math.hypot(pressCoor.getX()-endShape.port2.getX(), pressCoor.getY()-endShape.port2.getY());
		double distWizPort3 = Math.hypot(pressCoor.getX()-endShape.port3.getX(), pressCoor.getY()-endShape.port3.getY());
		double distWizPort4 = Math.hypot(pressCoor.getX()-endShape.port4.getX(), pressCoor.getY()-endShape.port4.getY());
		double distTemp = Math.min(distWizPort1, Math.min(distWizPort2, Math.min(distWizPort3,distWizPort4)));
		
		if(distTemp == distWizPort1){
			System.out.println("near port1");
			returnCoor = new Point2D(endShape.port1.getX()+shapeLayout.getX()+endShape.portSize, endShape.port1.getY()+shapeLayout.getY()+endShape.halfPortSize);
		}
		else if(distTemp == distWizPort2){
			System.out.println("near port2");
			returnCoor = new Point2D(endShape.port2.getX()+shapeLayout.getX()+endShape.halfPortSize, endShape.port2.getY()+shapeLayout.getY()+endShape.portSize);
		}
		else if(distTemp == distWizPort3){
			System.out.println("near port3");
			returnCoor = new Point2D(endShape.port3.getX()+shapeLayout.getX()+endShape.halfPortSize, endShape.port3.getY()+shapeLayout.getY());
		}
		else if(distTemp == distWizPort4){
			System.out.println("near port4");
			returnCoor = new Point2D(endShape.port4.getX()+shapeLayout.getX(), endShape.port4.getY()+shapeLayout.getY()+endShape.halfPortSize);
		}
		else{
			System.out.println("algo wrong");
		}
		
		return returnCoor;
	}
}
