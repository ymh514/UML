package UML;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.text.Text;

public class Mode implements EventHandler<MouseEvent>{
	@Override
	public void handle(MouseEvent event) {
		// TODO Auto-generated method stub

		
		if(event.getEventType() == MouseEvent.MOUSE_PRESSED){
//			System.out.println("Mouse Pressed");

		}
		else if(event.getEventType() == MouseEvent.MOUSE_DRAGGED){
//			System.out.println("Mouse Dragged");

		}
		else if(event.getEventType() == MouseEvent.MOUSE_RELEASED){
//			System.out.println("Mouse Realeased");

		}
	}
	public Mode getMode(){
		return this;
	}

}
