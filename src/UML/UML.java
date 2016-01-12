package UML;

import java.util.ArrayList;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UML extends Application {
	public Text eventCoordinate;
	private Canvas canvasPane;
	private ButtonPanel buttonPanel;
	
    public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
        primaryStage.setTitle("UML");
        BorderPane umlPane = new BorderPane();

        buttonPanel = new ButtonPanel(this);
        canvasPane = new Canvas(this);

        umlPane.setLeft(buttonPanel.getButtonPanel());
        umlPane.setRight(canvasPane.getCanvasPane());
        umlPane.setBottom(eventCoordinate = new Text(canvasPane.getEventCoordinate()));

        Scene primaryScene = new Scene(umlPane);
        primaryStage.setScene(primaryScene);
        primaryStage.show();

	}
	
	public Canvas getCanvas(){
		return this.canvasPane;
	}
	
	public ButtonPanel getButtonPanel(){
		return this.buttonPanel;
	}
	public Text getCoordinate(){
		return this.eventCoordinate;
	}
}
