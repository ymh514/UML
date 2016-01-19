package UML;

import java.util.ArrayList;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
	private ArrayList<Mode> modeList = new ArrayList<Mode>();
	private MenuBar mulMenuBar;
	private MenuItem changeNameItem;
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
		this.mulMenuBar = this.uml.getMenuBar();
		this.changeNameItem = this.mulMenuBar.getMenus().get(1).getItems().get(2);
		this.buttonsList = this.buttonPanel.buttonsList;
		
//		System.out.println("### "+this.buttonPanel.getChildren().get(0).getId());
		
		/*
		 * menuBar
		 */
		changeNameItem.setOnAction(event ->{
			changeNameEvent();
		});

		modeList.add(selectMode);
		modeList.add(assocLineMode);
		modeList.add(geneLineMode);
		modeList.add(compLineMode);
		modeList.add(classBoxMode);
		modeList.add(useCaseMode);

		for(int i=0;i<buttonsList.size();i++){
			Mode tempMode = modeList.get(i);
			int tempI = i;
			buttonsList.get(tempI).setOnMouseClicked(event ->{
				modeIndex = tempI;
				currentMode = tempMode;
				System.out.println(currentMode.getClass().getSimpleName());
				setSelectBtn(buttonsList.get(tempI));
				setMouse();
			});
		}
		
//		buttonsList.get(0).setOnMouseClicked(event ->{
//			modeIndex = 0;
//			currentMode = selectMode;
//			setSelectBtn(buttonPanel.selectBtn);
//			System.out.println(currentMode.getClass().getSimpleName());
//			setMouse();
//        });
//		buttonsList.get(1).setOnMouseClicked(event ->{
//			modeIndex = 1;
//			currentMode = assocLineMode;
//			setSelectBtn(buttonPanel.assocLineBtn);
//			System.out.println(currentMode.getClass().getSimpleName());
//			setMouse();
//		});
//		buttonsList.get(2).setOnMouseClicked(event ->{
//			modeIndex = 2;
//			currentMode = geneLineMode;
//			setSelectBtn(buttonPanel.geneLineBtn);
//			System.out.println(currentMode.getClass().getSimpleName());
//			setMouse();
//		});
//		buttonsList.get(3).setOnMouseClicked(event ->{
//			modeIndex = 3;
//			currentMode = compLineMode;
//			setSelectBtn(buttonPanel.compLineBtn);
//			System.out.println(currentMode.getClass().getSimpleName());
//			setMouse();
//		});
//		buttonsList.get(4).setOnMouseClicked(event ->{
//			modeIndex = 4;
//			currentMode = classBoxMode;
//			setSelectBtn(buttonPanel.classBoxBtn);
//			System.out.println(currentMode.getClass().getSimpleName());
//			setMouse();
//			
//		});
//		buttonsList.get(5).setOnMouseClicked(event ->{
//			modeIndex = 5;
//			currentMode = useCaseMode;
//			setSelectBtn(buttonPanel.useCaseBtn);
//			System.out.println(currentMode.getClass().getSimpleName());
//			setMouse();
//        });
		
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
	public void changeNameEvent(){
		for(int i=0;i<shapeList.size();i++){
			if(shapeList.get(i).getSelectState() == true){
				if(shapeList.get(i) instanceof BasicObject){
					BasicObject tempBasicObj = (BasicObject) shapeList.get(i);
				
					BorderPane changeNamePane = new BorderPane();
					changeNamePane.setPadding(new Insets(20,10,20,10));
					TextField changeNameText = new TextField();
					changeNameText.setPrefSize(200, 20);
					HBox btnHbox = new HBox(120);
					Button confirmBtn = new Button("OK");
					Button cancelBtn = new Button("Cancel");
					btnHbox.setPadding(new Insets(5,0,0,0));
					btnHbox.getChildren().addAll(confirmBtn,cancelBtn);
					changeNamePane.setCenter(changeNameText);
					changeNamePane.setBottom(btnHbox);;
		
					Scene changeNameScene = new Scene(changeNamePane);
					Stage changeNameWindow = new Stage();
					changeNameWindow.setScene(changeNameScene);
					changeNameWindow.show();
					
					confirmBtn.setOnMouseClicked(okEvent ->{
						tempBasicObj.text.setText(changeNameText.getText());
						changeNameWindow.close();;
					});
					cancelBtn.setOnMouseClicked(cancelEvent ->{
						changeNameWindow.close();;
					});
					
					shapeList.set(i, tempBasicObj);
				}
			}
		}

	}
}
