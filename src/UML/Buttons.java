package UML;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;

public class Buttons extends ToggleButton {
	public Canvas canvas;
	public Buttons(String name,Node icon){
		this.setText(name);
		this.setGraphic(icon);
	}
}
