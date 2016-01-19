package UML;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Shape extends Pane {

	protected boolean selectState;
	
    public Shape() {
    	System.out.println("shape constructor");
    }
    public void setShowSelect(Boolean showValue){
    	
    }
    public Shape draw(){
		return null;
    	
    }
    public void setSelected(Boolean selectState){
    	this.selectState = selectState;
    }
    public Point2D[] getBoundary() {
        return null;
    }
    public void tunePortPosition(){
    	
    }
}
