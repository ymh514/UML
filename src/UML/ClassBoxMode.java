package UML;

import java.util.ArrayList;

import javafx.scene.input.MouseEvent;

public class ClassBoxMode extends Mode{
	
	
	public ClassBoxMode(ArrayList<Shape> shapeList, Canvas canvas) {
		super(shapeList,canvas);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(MouseEvent event) {

		if(event.getEventType() == MouseEvent.MOUSE_PRESSED){
			this.newShape = new ClassBox(event.getX(), event.getY());
			this.shapeList.add(this.newShape);
			this.newShape.draw(canvas);
		}
	}

}
