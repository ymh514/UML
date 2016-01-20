package UML;

import java.util.ArrayList;

import javax.xml.ws.EndpointReference;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GroupObject extends Shape{

	protected double groupHeight;
	protected double groupWidth;
	protected Rectangle rectangle;
	
	protected ArrayList<Shape> componentList = new ArrayList<Shape>();
	
	public GroupObject() {
		// TODO Auto-generated constructor stub
//		this.setLayoutX(0);
//		this.setLayoutY(0);
//		this.setHeight(0);
//		this.setWidth(0);
	}
	public ArrayList<Shape> getComponentList(){
		return this.componentList;
	}
	public void loadCompList(){
		
		Point2D startPoint = new Point2D(0,0);
		Point2D endPoint = new Point2D(0,0);

       
		for(int i=0;i<componentList.size();i++){
            if(componentList.get(i).getClass().getSuperclass().getName() == "UML.BasicObject") {
            	BasicObject tempShape = (BasicObject)componentList.get(i);
                Point2D[] points = tempShape.getBoundary();
                if(i==0){
                	startPoint= new Point2D(points[0].getX(), points[0].getY());
                	endPoint= new Point2D(points[1].getX(), points[1].getY());

                }
                if(points[0].getX()<=startPoint.getX()){
                	startPoint= new Point2D(points[0].getX(),startPoint.getY());
                }
                if(points[0].getY()<=startPoint.getY()){
                	startPoint= new Point2D(startPoint.getX(),points[0].getY());
                }
                if(points[1].getX()>=endPoint.getX()){
                	endPoint= new Point2D(points[1].getX(),endPoint.getY());
                }
                if(points[1].getY()>=endPoint.getY()){
                	endPoint= new Point2D(endPoint.getX(),points[1].getY());
                }
            }
            System.out.println("input ");         
			componentList.get(i).setDisable(true);
			componentList.get(i).setSelected(false);
			componentList.get(i).setShowSelect(false);
		}

        groupWidth = endPoint.getX()-startPoint.getX();
        groupHeight = endPoint.getY()-startPoint.getY();
        
        this.setLayoutX(startPoint.getX()-10);
        this.setLayoutY(startPoint.getY()-10);
        this.setHeight(groupHeight+20);
        this.setWidth(groupWidth+20);

        rectangle = new Rectangle(groupWidth+20,groupHeight+20);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setDisable(true);
        this.getChildren().add(rectangle);
        
      System.out.println("---- "+rectangle.getLayoutX()+"----"+rectangle.getLayoutY());
      System.out.println("---- "+this.getLayoutX()+"----"+this.getLayoutY());

        
        for(int i=0;i<componentList.size();i++){
        	double offsetX = 0;
        	double offsetY = 0;
          System.out.println("xxx "+componentList.get(i).getLayoutX());
          System.out.println("yyy "+componentList.get(i).getLayoutY());
          offsetX = componentList.get(i).getLayoutX()-this.getLayoutX();
          offsetY = componentList.get(i).getLayoutY()-this.getLayoutY();
          componentList.get(i).setLayoutX(offsetX);
          componentList.get(i).setLayoutY(offsetY);

          this.getChildren().add(componentList.get(i));

        }
	}

}
