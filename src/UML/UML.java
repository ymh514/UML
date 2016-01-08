package UML;

import java.awt.Button;
import java.util.ArrayList;

import com.sun.glass.ui.MenuBar;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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

        BorderPane myPane = new BorderPane();
        
        Scene primaryScene = new Scene(myPane);
        primaryStage.setScene(primaryScene);
        primaryStage.show();

	}
}
