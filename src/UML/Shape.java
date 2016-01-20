package UML;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Shape extends Pane {

	protected boolean selectState = false;
	
    public Shape() {
    	System.out.println("shape constructor");
    }
    public void draw(Canvas canvas){    	
    
    }
    public boolean getSelectState(){
    	return selectState;
    }
    public void setSelected(Boolean selectState){
    	this.selectState = selectState;
    	this.setShowSelect(selectState);
    }
    public void setShowSelect(Boolean showValue){
    	
    }

    public Point2D[] getBoundary() {
        return null;
    }
    public void tunePortPosition(){
    	
    }
}
