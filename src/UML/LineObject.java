package UML;

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
	
    public void draw(Canvas cavas){
//		shapeList.set(i, tempLine);

    }
    public void setBeginPort(Port beginPort){
    	this.beginPort = beginPort;
    }
    public void setEndPort(Port endPort){
    	this.endPort = endPort;
    }
    public void setEndObj(){
    	
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
}
