package UML;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class LineObject extends Shape {
	
    protected Line connectLine;
    protected Port beginPort;
    protected Port endPort;
    protected Rectangle geneObj;
    protected Polygon compObj;
    
	public LineObject() {
		connectLine = new Line();
		this.getChildren().add(connectLine);
	}
	
    public void draw(Canvas canvas){
//		shapeList.set(i, tempLine);
    	this.setSelected(false);
    	canvas.getChildren().add(this.connectLine);
        canvas.getChildren().add(this.getEndObj());

    }
    public void setBeginPort(Port beginPort){
    	this.beginPort = beginPort;
    }
    public void setEndPort(Port endPort){
    	this.endPort = endPort;
    }
    public void setEndObj(){
    	
    }
    public Polygon getEndObj() {
		return null;
	}
    

    public void setConnectLineXY(){
		this.connectLine.setStartX(this.beginPort.getLayoutXOnCanvas()+this.getLayoutX());
		this.connectLine.setStartY(this.beginPort.getLayoutYOnCanvas()+this.getLayoutY());
		this.connectLine.setEndX(this.endPort.getLayoutXOnCanvas()+this.getLayoutX());
		this.connectLine.setEndY(this.endPort.getLayoutYOnCanvas()+this.getLayoutY());

    }
//    public void setBeginPort(Port beginPort){
//    	
//    }
    
	@Override
    public Point2D[] getBoundary() {
		
        Point2D[] points = new Point2D[2];
        points[0] = new Point2D(this.connectLine.getStartX(), this.connectLine.getStartY());
        points[1] = new Point2D(this.connectLine.getEndY(), this.connectLine.getEndY());

        return points;
    }

	@Override
	public void setSelected(Boolean selectState) {
		// TODO Auto-generated method stub
    	super.selectState = selectState;
    	
	}

	@Override
	public void setShowSelect(Boolean showValue) {
		// TODO Auto-generated method stub
		
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

	@Override
	public void unGroup(ArrayList<Shape> shapeList) {
		// TODO Auto-generated method stub
		
	}
}
