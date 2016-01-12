package UML;

import java.util.ArrayList;

import javafx.scene.control.Toggle;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Canvas extends Pane {
	private Pane canvasPane;
	private ArrayList<Shape> shapeList = new ArrayList<Shape>();
	public ArrayList<Mode> currentMode = new ArrayList<Mode>();
	private UML uml;
	public ButtonPanel buttonPanel;
	public int modeIndex;
	
	public Canvas(UML p){
		canvasPane = new Pane();
		canvasPane.setPrefSize(800, 600);
		canvasPane.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, null, null)));
		this.uml = p;
		this.buttonPanel = this.uml.getButtonPanel();

		currentMode.add(buttonPanel.selectBtn.selectMode);
		currentMode.add(buttonPanel.assocLineBtn.assocLineMode.getMode());
		currentMode.add(buttonPanel.geneLineBtn.geneLineMode.getMode());
		currentMode.add(buttonPanel.compLineBtn.compLineMode.getMode());
		currentMode.add(buttonPanel.classBoxBtn.classBoxMode.getMode());
		currentMode.add(buttonPanel.useCaseBtn.useCaseMode.getMode());

		buttonPanel.selectBtn.setOnMouseClicked(event ->{
			modeIndex = 0;
			System.out.println(currentMode.get(modeIndex).getClass().getSimpleName());
        });
		buttonPanel.assocLineBtn.setOnMouseClicked(event ->{
			modeIndex = 1;
			System.out.println(currentMode.get(modeIndex).getClass().getSimpleName());
		});
		buttonPanel.geneLineBtn.setOnMouseClicked(event ->{
			modeIndex = 2;
			System.out.println(currentMode.get(modeIndex).getClass().getSimpleName());
		});
		buttonPanel.compLineBtn.setOnMouseClicked(event ->{
			modeIndex = 3;
			System.out.println(currentMode.get(modeIndex).getClass().getSimpleName());
		});
		buttonPanel.classBoxBtn.setOnMouseClicked(event ->{
			modeIndex = 4;
			System.out.println(currentMode.get(modeIndex).getClass().getSimpleName());
		});
		buttonPanel.useCaseBtn.setOnMouseClicked(event ->{
			modeIndex = 5;
			System.out.println(currentMode.get(modeIndex).getClass().getSimpleName());
        });
		
		canvasPane.setOnMousePressed(currentMode.get(modeIndex));
		canvasPane.setOnMouseDragged(currentMode.get(modeIndex));
		canvasPane.setOnMouseReleased(currentMode.get(modeIndex));
		
	}
	
	public Pane getCanvasPane(){
		return canvasPane;
	}
}
