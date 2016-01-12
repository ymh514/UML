package UML;

import javafx.scene.Node;

public class CompLineBtn extends Buttons{
	CompLineMode compLineMode;
	public CompLineBtn(String name, Node icon) {
		super(name, icon);
		compLineMode = new CompLineMode();
//		this.setOnMouseClicked(compLineMode);
//		this.setOnMouseDragged(compLineMode);
//		this.setOnMouseReleased(compLineMode);

		// TODO Auto-generated constructor stub
	}

}
