package UML;

import java.util.ArrayList;

import javafx.scene.input.MouseEvent;

public class ClassBoxMode extends Mode{
	
	private ClassBox classBox;
	
	public ClassBoxMode(ArrayList<Shape> shapeList, Canvas canvas) {
		super(shapeList,canvas);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(MouseEvent event) {

		if(event.getEventType() == MouseEvent.MOUSE_PRESSED){
			System.out.println("New ClassBox");
			classBox = new ClassBox(event.getX(), event.getY());
			this.newShape = classBox;
			this.shapeList.add(this.newShape);
			this.canvas.getChildren().add(this.newShape);
			System.out.println("canvas size: "+this.canvas.getChildren().size());
			System.out.println("shapeList size: "+this.shapeList.size());
		}
	}

}
