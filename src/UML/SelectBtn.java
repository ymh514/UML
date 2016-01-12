package UML;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;

public class SelectBtn extends Buttons {
	public SelectMode selectMode;
	
	public SelectBtn(String name, Node icon) {
		super(name, icon);
//		this.setBackground(Background.EMPTY);
		selectMode = new SelectMode();
//		this.setOnMouseClicked(selectMode);
//		this.setOnMouseDragged(selectMode);
//		this.setOnMouseReleased(selectMode);
		// TODO Auto-generated constructor stub
	}

//	public EventHandler<? super MouseEvent> setOnMouseClicked() {
//		
//		return selectMode;
//	}
}
