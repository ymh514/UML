package UML;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class CompLine extends LineObject{
    private Polygon rectangle;

	public CompLine() {
		// TODO Auto-generated constructor stub
        rectangle = new Polygon();
        rectangle.getPoints().addAll(
                10.0, 0.0,
                20.0, 10.0,
                10.0, 20.0,
                0.0, 10.0);
        this.connectLine.setStroke(Color.BLACK);
        rectangle.setFill(Color.GRAY);
        rectangle.setStroke(Color.BLACK);

	}
    public void setRectangle() {
        double deltaX = this.connectLine.getEndX() - this.connectLine.getStartX();
        double deltaY = this.connectLine.getEndY() - this.connectLine.getStartY();
        rectangle.setLayoutX(this.connectLine.getEndX() - 10);
        rectangle.setLayoutY(this.connectLine.getEndY() - 10);
        System.out.println(-Math.atan2(-deltaY, deltaX) / Math.PI * 180 + 90);
        rectangle.setRotate(-Math.atan2(-deltaY, deltaX) / Math.PI * 180 +90 );//+90
    }

    public Polygon getEndObj() {
        return rectangle;
    }
    public void setEndObj(){
    	this.setRectangle();
    }

}
