package UML;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class GeneLine extends LineObject{
   
	private Polygon triangle;

	public GeneLine() {
		// TODO Auto-generated constructor stub
        triangle = new Polygon();
        triangle.getPoints().addAll(
                10.0,0.0,
                0.0,20.0,
                20.0,20.0);
//        triangle.getPoints().addAll(
//                0.0,0.0,
//                0.0,20.0,
//                20.0,10.0);  
        this.connectLine.setStroke(Color.BLACK);
        triangle.setFill(Color.GRAY);
        triangle.setStroke(Color.BLACK);

	}
	
    public void setTriangle() {
        double deltaX = this.connectLine.getEndX() - this.connectLine.getStartX();
        double deltaY = this.connectLine.getEndY() - this.connectLine.getStartY();
        triangle.setLayoutX(this.connectLine.getEndX() - 10);
        triangle.setLayoutY(this.connectLine.getEndY() - 10);
        System.out.println(-Math.atan2(-deltaY, deltaX) / Math.PI * 180 + 90);
        triangle.setRotate(-Math.atan2(-deltaY, deltaX) / Math.PI * 180 + 90 );//+90
    }

    public Polygon getTriangle() {
        return triangle;
    }

}
