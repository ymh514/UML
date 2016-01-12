package UML;

import javafx.scene.Node;

public class UseCaseBtn extends Buttons{
	UseCaseMode useCaseMode;
	public UseCaseBtn(String name, Node icon) {
		super(name, icon);
		useCaseMode = new UseCaseMode();
//		this.setOnMouseClicked(useCaseMode);
		// TODO Auto-generated constructor stub
	}

}
