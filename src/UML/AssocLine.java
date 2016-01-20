package UML;

import javafx.scene.paint.Color;

public class AssocLine extends LineObject{

	public AssocLine() {
		this.connectLine.setStroke(Color.BLACK);
	}
	public void draw(Canvas canvas){
		this.setConnectLineXY();
		this.setEndObj();
        canvas.getChildren().add(this.connectLine);

	}

}
