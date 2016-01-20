package UML;

import java.util.ArrayList;

import javafx.scene.input.MouseEvent;

public class UseCaseMode extends Mode{
	

	public UseCaseMode(ArrayList<Shape> shapeList, Canvas canvas) {
		super(shapeList,canvas);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(MouseEvent event) {

		if(event.getEventType() == MouseEvent.MOUSE_PRESSED){
			this.newShape = new UseCase(event.getX(), event.getY());
			this.shapeList.add(this.newShape);
			this.newShape.draw(canvas);
		}
	}
}
