package UML;

import java.util.ArrayList;

import com.sun.glass.ui.MenuBar;

import javafx.application.Application;
import javafx.geometry.Insets;
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
import javafx.stage.Stage;

public class UML extends Application {
	
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
}
