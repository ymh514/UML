package UML;

import java.awt.Button;
import java.util.ArrayList;

import com.sun.glass.ui.MenuBar;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
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
	
	private VBox buttonVBox;
	private MenuBar menuBar;
	private Pane canvas;
	private Button selectBtn;
	private Button assLineBtn;
	private Button genLineBtn;
	private Button comLineBtn;
	private Button classBtn;
	private Button useCaseBtn;
	
    public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
        primaryStage.setTitle("UML");

        BorderPane umlPane = new BorderPane();
        Pane canvas = new Pane();
        VBox buttonVBox = new VBox(20);
        buttonVBox.setPrefSize(100, 600);
        buttonVBox.setPadding(new Insets(15, 15, 15, 15));

        selectBtn = new Button();
        
        canvas.setPrefSize(800, 600);
        canvas.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, null, null)));
        umlPane.setRight(canvas);
        umlPane.setLeft(buttonVBox);
        
        Scene primaryScene = new Scene(umlPane);
        primaryStage.setScene(primaryScene);
        primaryStage.show();

	}
}
