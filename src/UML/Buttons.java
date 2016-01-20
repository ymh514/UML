package UML;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;

public class Buttons extends ToggleButton {
	public Buttons(String name, ImageView icon, Canvas canvasPane) {
		this.setText(name);
		this.setGraphic(icon);
		this.setBackground(Background.EMPTY);
	}
}
