package UML;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Canvas extends Pane {
	private Pane canvasPane;
	private ArrayList<Shape> shapeList = new ArrayList<Shape>();
	public ArrayList<Mode> currentMode = new ArrayList<Mode>();
	public ArrayList<Buttons> buttonsList = new ArrayList<Buttons>();
	
	private UML uml;
	public ButtonPanel buttonPanel;
	public int modeIndex;
	private String eventCoordinate = new String();
	
	public Canvas(UML p){
		canvasPane = new Pane();
		canvasPane.setPrefSize(800, 600);
		canvasPane.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, null, null)));
		canvasPane.setLayoutX(90);
		canvasPane.setLayoutY(10);

		this.uml = p;
		this.buttonPanel = this.uml.getButtonPanel();
		
		currentMode.add(buttonPanel.selectBtn.selectMode);
		currentMode.add(buttonPanel.assocLineBtn.assocLineMode.getMode());
		currentMode.add(buttonPanel.geneLineBtn.geneLineMode.getMode());
		currentMode.add(buttonPanel.compLineBtn.compLineMode.getMode());
		currentMode.add(buttonPanel.classBoxBtn.classBoxMode.getMode());
		currentMode.add(buttonPanel.useCaseBtn.useCaseMode.getMode());

		buttonsList.add(buttonPanel.selectBtn);
		buttonsList.add(buttonPanel.assocLineBtn);
		buttonsList.add(buttonPanel.geneLineBtn);
		buttonsList.add(buttonPanel.compLineBtn);
		buttonsList.add(buttonPanel.classBoxBtn);
		buttonsList.add(buttonPanel.useCaseBtn);

		buttonPanel.selectBtn.setOnMouseClicked(event ->{
			modeIndex = 0;
			setSelectBtn(buttonPanel.selectBtn);
			System.out.println(currentMode.get(modeIndex).getClass().getSimpleName());
        });
		buttonPanel.assocLineBtn.setOnMouseClicked(event ->{
			modeIndex = 1;
			setSelectBtn(buttonPanel.assocLineBtn);
			System.out.println(currentMode.get(modeIndex).getClass().getSimpleName());

		});
		buttonPanel.geneLineBtn.setOnMouseClicked(event ->{
			modeIndex = 2;
			setSelectBtn(buttonPanel.geneLineBtn);
			System.out.println(currentMode.get(modeIndex).getClass().getSimpleName());
		});
		buttonPanel.compLineBtn.setOnMouseClicked(event ->{
			modeIndex = 3;
			setSelectBtn(buttonPanel.compLineBtn);
			System.out.println(currentMode.get(modeIndex).getClass().getSimpleName());
		});
		buttonPanel.classBoxBtn.setOnMouseClicked(event ->{
			modeIndex = 4;
			setSelectBtn(buttonPanel.classBoxBtn);
			System.out.println(currentMode.get(modeIndex).getClass().getSimpleName());
		});
		buttonPanel.useCaseBtn.setOnMouseClicked(event ->{
			modeIndex = 5;
			setSelectBtn(buttonPanel.useCaseBtn);
			System.out.println(currentMode.get(modeIndex).getClass().getSimpleName());
        });
		
		canvasPane.setOnMouseMoved(event -> {
			eventCoordinate = " x: "+event.getX()+" y: "+event.getY();
			this.uml.eventCoordinate.setText(eventCoordinate);
		});
		
		canvasPane.setOnMousePressed(currentMode.get(modeIndex));
		canvasPane.setOnMouseDragged(currentMode.get(modeIndex));
		canvasPane.setOnMouseReleased(currentMode.get(modeIndex));
		
	}
	public void setSelectBtn(Buttons selectedBtn){
		for(int i=0 ; i<buttonsList.size();i++){
			if(i!=modeIndex){
				buttonsList.get(i).setBackground(Background.EMPTY);
			}
		}
		selectedBtn.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
	}
	public Pane getCanvasPane(){
		return canvasPane;
	}
	public String getEventCoordinate(){
		return eventCoordinate;
	}
}
