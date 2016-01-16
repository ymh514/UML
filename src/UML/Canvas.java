package UML;

import java.util.ArrayList;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Canvas extends Pane {
//	private Pane canvasPane;
	
	private ArrayList<Shape> shapeList = new ArrayList<Shape>();
	private ArrayList<Buttons> buttonsList = new ArrayList<Buttons>();

	private Mode currentMode;
	private SelectMode selectMode = new SelectMode(shapeList,this);
	private AssocLineMode assocLineMode = new AssocLineMode(shapeList,this);
	private CompLineMode compLineMode = new CompLineMode(shapeList,this);
	private GeneLineMode geneLineMode = new GeneLineMode(shapeList,this);
	private ClassBoxMode classBoxMode = new ClassBoxMode(shapeList,this);
	private UseCaseMode useCaseMode = new UseCaseMode(shapeList,this);
	
	private UML uml;
	private ButtonPanel buttonPanel;
	private int modeIndex;
	private String eventCoordinate = new String();
	
	public Canvas(UML p){
//		canvasPane = new Pane();
		this.setPrefSize(800, 600);
		this.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, null, null)));
		this.setLayoutX(90);
		this.setLayoutY(10);

		this.uml = p;
		this.buttonPanel = this.uml.getButtonPanel();
		
		buttonsList.add(buttonPanel.selectBtn);
		buttonsList.add(buttonPanel.assocLineBtn);
		buttonsList.add(buttonPanel.geneLineBtn);
		buttonsList.add(buttonPanel.compLineBtn);
		buttonsList.add(buttonPanel.classBoxBtn);
		buttonsList.add(buttonPanel.useCaseBtn);

		buttonPanel.selectBtn.setOnMouseClicked(event ->{
			modeIndex = 0;
			currentMode = selectMode;
			setSelectBtn(buttonPanel.selectBtn);
			System.out.println(currentMode.getClass().getSimpleName());
			setMouse();
        });
		buttonPanel.assocLineBtn.setOnMouseClicked(event ->{
			modeIndex = 1;
			currentMode = assocLineMode;
			setSelectBtn(buttonPanel.assocLineBtn);
			System.out.println(currentMode.getClass().getSimpleName());
			setMouse();
		});
		buttonPanel.geneLineBtn.setOnMouseClicked(event ->{
			modeIndex = 2;
			currentMode = geneLineMode;
			setSelectBtn(buttonPanel.geneLineBtn);
			System.out.println(currentMode.getClass().getSimpleName());
			setMouse();
		});
		buttonPanel.compLineBtn.setOnMouseClicked(event ->{
			modeIndex = 3;
			currentMode = compLineMode;
			setSelectBtn(buttonPanel.compLineBtn);
			System.out.println(currentMode.getClass().getSimpleName());
			setMouse();
		});
		buttonPanel.classBoxBtn.setOnMouseClicked(event ->{
			modeIndex = 4;
			currentMode = classBoxMode;
			setSelectBtn(buttonPanel.classBoxBtn);
			System.out.println(currentMode.getClass().getSimpleName());
			setMouse();
			
		});
		buttonPanel.useCaseBtn.setOnMouseClicked(event ->{
			modeIndex = 5;
			currentMode = useCaseMode;
			setSelectBtn(buttonPanel.useCaseBtn);
			System.out.println(currentMode.getClass().getSimpleName());
			setMouse();
        });
		
		this.setOnMouseMoved(event -> {
			eventCoordinate = " x: "+event.getX()+" y: "+event.getY();
			this.uml.eventCoordinate.setText(eventCoordinate);
		});
		
	}

	public void setMouse(){
		this.setOnMousePressed(currentMode);
		this.setOnMouseDragged(currentMode);
		this.setOnMouseReleased(currentMode);
	}
	public void setSelectBtn(Buttons selectedBtn){
		for(int i=0 ; i<buttonsList.size();i++){
			if(i!=modeIndex){
				buttonsList.get(i).setBackground(Background.EMPTY);
			}
		}
		selectedBtn.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
	}
	public Canvas getCanvasPane(){
		return this;
	}
	public String getEventCoordinate(){
		return eventCoordinate;
	}
}
