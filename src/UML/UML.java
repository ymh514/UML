package UML;

import java.util.ArrayList;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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
	public Canvas canvasPane;
	public ButtonPanel buttonPanel;
	public MenuBar menuBar = new MenuBar();

	
    public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
        primaryStage.setTitle("UML");
        BorderPane umlPane = new BorderPane();
        
        generateMenubar();

        buttonPanel = new ButtonPanel(this);
        canvasPane = new Canvas(this);
        
        umlPane.setTop(menuBar);
        umlPane.setLeft(buttonPanel.getButtonPanel());
        umlPane.setRight(canvasPane.getCanvasPane());
        umlPane.setBottom(eventCoordinate = new Text(canvasPane.getEventCoordinate()));

        Scene primaryScene = new Scene(umlPane);
        primaryStage.setScene(primaryScene);
        primaryStage.setResizable(false);
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
	public MenuBar getMenuBar(){
		return this.menuBar;
	}
	public void generateMenubar(){
        Menu menuFile = new Menu("File");
        Menu menuEdit = new Menu("Edit");
        MenuItem groupItem = new MenuItem("Group");
        MenuItem ungroupItem = new MenuItem("UnGroup");
        MenuItem changeNameItem = new MenuItem("Change Object Name");
        MenuItem exitItem = new MenuItem("Exit");
        menuFile.getItems().add(exitItem);
        menuEdit.getItems().addAll(groupItem,ungroupItem,changeNameItem);
        menuBar.getMenus().addAll(menuFile,menuEdit);

	}
}
