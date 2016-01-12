package UML;

import javafx.scene.Node;

public class AssocLineBtn extends Buttons{
	AssocLineMode assocLineMode;
	public AssocLineBtn(String name, Node icon) {
		super(name, icon);
		assocLineMode = new AssocLineMode();
//		this.setOnMouseClicked(assocLineMode);
//		this.setOnMouseDragged(assocLineMode);
//		this.setOnMouseReleased(assocLineMode);

		// TODO Auto-generated constructor stub
	}

}
