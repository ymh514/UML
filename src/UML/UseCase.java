package UML;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;

public class UseCase extends BasicObject{

	public UseCase(double x, double y) {
        super(120, 80);
        this.setLayoutX(x);
        this.setLayoutY(y);

        Ellipse ellipse = new Ellipse();
        ellipse.setCenterX(halfWidth);
        ellipse.setCenterY(halfHeight);
        ellipse.setRadiusX(halfWidth);
        ellipse.setRadiusY(halfHeight);
        ellipse.setFill(Color.GAINSBORO);
        ellipse.setStroke(Color.BLACK);
        ellipse.setDisable(true);

        text = new Text();
        text.setText("use case");
        text.setX(37);
        text.setY(45);
        text.setDisable(true);
        this.getChildren().addAll(ellipse, text);
		System.out.println("use case constructor");

		// TODO Auto-generated constructor stub
	}

}
