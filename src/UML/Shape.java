package UML;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public abstract class Shape extends Pane {

	protected boolean selectState = false;
	
    public Shape() {
    	System.out.println("shape constructor");
    }
    public abstract void draw(Canvas canvas);
    
    public abstract void setSelected(Boolean selectState);
    
//    public abstract void setSelected(Boolean selectState){
//    	this.selectState = selectState;
//    	this.setShowSelect(selectState);
//    }
    public boolean getSelectState(){
    	return selectState;
    }

    public abstract void setShowSelect(Boolean showValue);
    

    public Point2D[] getBoundary() {
        return null;
    }
    public void tunePortPosition(){
    	
    }
    public abstract Point2D getGroupRectEndPt(Point2D points,Point2D endPoint,int i);
    
    public abstract Point2D getGroupRectStartPt(Point2D points,Point2D startPoint,int i);
    
    public abstract void unGroup(ArrayList<Shape> shapeList);
    
}
