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
		// TODO Auto-generated constructor stu
	}
	public void generateRec(){
		Point2D startPoint = new Point2D(0,0);
		Point2D endPoint = new Point2D(0,0);

       
		for(int i=0;i<componentList.size();i++){
            	Shape tempShape = componentList.get(i);
            	System.out.println(tempShape.getClass().getName());
                Point2D[] points = tempShape.getBoundary();
            	startPoint = tempShape.getGroupRectStartPt(points[0], startPoint, i);
            	endPoint = tempShape.getGroupRectEndPt(points[1], endPoint, i);
            	
		}		
        groupWidth = endPoint.getX()-startPoint.getX();
        groupHeight = endPoint.getY()-startPoint.getY();
        
        this.setLayoutX(startPoint.getX()-10);
        this.setLayoutY(startPoint.getY()-10);
        this.setHeight(groupHeight+20);
        this.setWidth(groupWidth+20);
        
        for(int i=0;i<componentList.size();i++){
        	double offsetX = this.getLayoutX()-0;
        	double offsetY = this.getLayoutY()-0;
	        componentList.get(i).setLayoutX(componentList.get(i).getLayoutX()-offsetX);
	        componentList.get(i).setLayoutY(componentList.get(i).getLayoutY()-offsetY);
			componentList.get(i).setDisable(true);
			componentList.get(i).setSelected(false);
			componentList.get(i).setShowSelect(false);
			
					
			
			
			if(componentList.get(i).getClass().getSuperclass().getName() == "UML.LineObject"){
				LineObject tempLine = (LineObject) componentList.get(i);

				tempLine.setConnectLineXY();
				tempLine.setEndObj();
				
				this.getChildren().add(tempLine.connectLine);
				
				if(tempLine.getEndObj()!=null){
					this.getChildren().add(tempLine.getEndObj());
				}
			}
			else{
				this.getChildren().add(componentList.get(i));
			}
		}
        
        rectangle = new Rectangle(groupWidth+20,groupHeight+20);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setDisable(true);
        this.setShowSelect(false);
        this.getChildren().add(rectangle);

	}
	public ArrayList<Shape> getComponentList(){
		return this.componentList;
	}
	public void draw(Canvas canvas){
	
		canvas.getChildren().add(this);
	}
	@Override
	public void setSelected(Boolean selectState) {
		// TODO Auto-generated method stub
    	super.selectState = selectState;
    	this.setShowSelect(selectState);

		for (int i=0;i<componentList.size();i++) {
			componentList.get(i).setSelected(selectState);
			componentList.get(i).setShowSelect(selectState);
		}
	}
	@Override
	public void setShowSelect(Boolean showValue) {
		// TODO Auto-generated method stub
		if(showValue ==true){
			this.rectangle.setStroke(Color.BLUE);
			this.rectangle.setVisible(true);
		}
		else{
			this.rectangle.setVisible(false);
		}
	}

	@Override
    public Point2D getGroupRectEndPt(Point2D points,Point2D endPoint,int i){
		
        if(i==0){
        	endPoint= new Point2D(points.getX(), points.getY());

        }
        if(points.getX()>=endPoint.getX()){
        	endPoint= new Point2D(points.getX(),endPoint.getY());
        }
        if(points.getY()>=endPoint.getY()){
        	endPoint= new Point2D(endPoint.getX(),points.getY());
        }
        return endPoint;
    }
	@Override
    public Point2D getGroupRectStartPt(Point2D points,Point2D startPoint,int i){
		
        if(i==0){
        	startPoint= new Point2D(points.getX(), points.getY());

        }
        if(points.getX()<=startPoint.getX()){ 
        	startPoint= new Point2D(points.getX(),startPoint.getY());
        }
        if(points.getY()<=startPoint.getY()){
        	startPoint= new Point2D(startPoint.getX(),points.getY());
        }
        return startPoint;

    }


    
    public Point2D[] getBoundary() {
        Point2D[] points = new Point2D[2];
        points[0] = new Point2D(this.getLayoutX(), this.getLayoutY());
        points[1] = new Point2D(this.getLayoutX() + this.groupWidth, this.getLayoutY() + this.groupHeight);
        return points;
    }
	@Override
	public void unGroup(ArrayList<Shape> shapeList) {
		// TODO Auto-generated method stub
		for(int i=0;i<componentList.size();i++){
			
	        componentList.get(i).setLayoutX(componentList.get(i).getLayoutX()+this.getLayoutX());
	        componentList.get(i).setLayoutY(componentList.get(i).getLayoutY()+this.getLayoutY());
			componentList.get(i).setSelected(false);
			componentList.get(i).setShowSelect(false);

			componentList.get(i).setDisable(false);
			
			if(componentList.get(i).getClass().getSuperclass().getName() == "UML.LineObject"){
				LineObject tempLine = (LineObject) componentList.get(i);
				tempLine.setConnectLineXY();
				tempLine.setEndObj();
				componentList.get(i).getChildren().add(tempLine.connectLine);
				
				if(tempLine.getEndObj()!=null){
					componentList.get(i).getChildren().add(tempLine.getEndObj());
				}
			}
			shapeList.add(componentList.get(i));
		}
		shapeList.remove(this);

	}

}
