package UML;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class UML extends Application {
    public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
        primaryStage.setTitle("UML");

        Pane myPane = new Pane();
        
 
        
        Scene primaryScene = new Scene(myPane);
        primaryStage.setScene(primaryScene);
        primaryStage.show();

	}
}
