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
	
	private ArrayList<Shape> shapeList = new ArrayList<Shape>();
	private ArrayList<Buttons> buttonsList = new ArrayList<Buttons>();
	private ArrayList<Mode> modeList = new ArrayList<Mode>();

	private Mode currentMode;
	private SelectMode selectMode = new SelectMode(shapeList,this);
	private AssocLineMode assocLineMode = new AssocLineMode(shapeList,this);
	private CompLineMode compLineMode = new CompLineMode(shapeList,this);
	private GeneLineMode geneLineMode = new GeneLineMode(shapeList,this);
	private ClassBoxMode classBoxMode = new ClassBoxMode(shapeList,this);
	private UseCaseMode useCaseMode = new UseCaseMode(shapeList,this);
	private MenuBar mulMenuBar;
	private MenuItem changeNameItem;
	private MenuItem groupItem;

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
		this.groupItem = this.mulMenuBar.getMenus().get(1).getItems().get(0);
		this.buttonsList = this.buttonPanel.buttonsList;
				
		/*
		 * menuBar 
		 */
		
		groupItem.setOnAction(event ->{
			groupEvent();
		});
		
		changeNameItem.setOnAction(event ->{
			changeNameEvent();
		});

		
		modeList.add(selectMode);
		modeList.add(assocLineMode);
		modeList.add(geneLineMode);
		modeList.add(compLineMode);
		modeList.add(classBoxMode);
		modeList.add(useCaseMode);
		
		buttonsEventToMode();
		
		this.setOnMouseMoved(event -> {
			eventCoordinate = " x: "+event.getX()+" y: "+event.getY();
			this.uml.eventCoordinate.setText(eventCoordinate);
		});
		
	}

	public void setCanvasMouseEvent(){
	
		this.setOnMousePressed(currentMode);
		this.setOnMouseDragged(currentMode);
		this.setOnMouseReleased(currentMode);
		
	}
	public void setSelectBtn(Buttons selectedBtn){
		/*
		 *  show choosed btn
		 */
		
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
	
	public void groupEvent(){
		System.out.println("-------- " + shapeList.size()+" ---------");

		GroupObject tempGroup = new GroupObject();
		ArrayList<Shape> tempComponentList = new ArrayList<Shape>();
		tempComponentList = tempGroup.getComponentList();
		
		for(int i=0;i<shapeList.size();i++){
			Shape tempShape = shapeList.get(i);
			System.out.println(tempShape.getSelectState());
		}
		
		for(int i=0;i<shapeList.size();i++){
			Shape tempShape = shapeList.get(i);
			Boolean temp = tempShape.getSelectState();

			if(temp == true){
				tempComponentList.add(tempShape);
				
				if(tempShape instanceof LineObject){
					System.out.println("友友友");

					LineObject tempLine = (LineObject) tempShape;
					
					for(int j=0;j<this.getChildren().size();j++){
						if(this.getChildren().get(j) == tempLine.connectLine){
							this.getChildren().remove(j);
						}
					}
				}
				else{
					this.getChildren().remove(i);
				}
				shapeList.remove(i);
				i=-1;
			}		

		}
		
		// 要讓group跑出來
		if(tempGroup != null){
			tempGroup.loadCompList();
			shapeList.add(tempGroup);
			this.getChildren().add(tempGroup);
//			System.out.println("group "+tempGroup.getChildren().size());
		}
		
		System.out.println("-------- " + shapeList.size()+" ---------");
	}
	
	public void changeNameEvent(){
		
		/*
		 *  change name if selected a basic object(search shapeList)
		 */
		
		for(int i=0;i<shapeList.size();i++){
			
			if(shapeList.get(i).getSelectState() == true){
				if(shapeList.get(i) instanceof BasicObject){
					BasicObject tempBasicObj = (BasicObject) shapeList.get(i);
				
					BorderPane changeNamePane = new BorderPane();
					changeNamePane.setPadding(new Insets(20,10,20,10));
					TextField changeNameText = new TextField();
					changeNameText.setPrefSize(200, 20);
					HBox btnHbox = new HBox(220);
					Button confirmBtn = new Button("OK");
					Button cancelBtn = new Button("Cancel");
					btnHbox.setPadding(new Insets(5,0,0,0));
					btnHbox.getChildren().addAll(confirmBtn,cancelBtn);
					changeNamePane.setCenter(changeNameText);
					changeNamePane.setBottom(btnHbox);;
		
					Scene changeNameScene = new Scene(changeNamePane);
					Stage changeNameWindow = new Stage();
					changeNameWindow.setTitle("Please enter new name");
					changeNameWindow.setHeight(150);
					changeNameWindow.setWidth(350);
					changeNameWindow.setScene(changeNameScene);
					changeNameWindow.show();
										
					// change name
					confirmBtn.setOnMouseClicked(okEvent ->{
						tempBasicObj.text.setText(changeNameText.getText());
						changeNameWindow.close();;
					});
					
					// cancel change 
					cancelBtn.setOnMouseClicked(cancelEvent ->{
						changeNameWindow.close();;
					});
					
					// update the shape
					shapeList.set(i, tempBasicObj);
				}
			}
		}

	}
	
	public void buttonsEventToMode(){
		/*
		 * only will run six time when call this function
		 */
		for(int i=0;i<buttonsList.size();i++){
			Mode tempMode = modeList.get(i);
			int tempI = i;
			buttonsList.get(tempI).setOnMouseClicked(event ->{
				System.out.println(" insid : "+shapeList.size());
				modeIndex = tempI;
				currentMode = tempMode;
				currentMode.unSelectAllShape();
				System.out.println(currentMode.getClass().getSimpleName());
				setSelectBtn(buttonsList.get(tempI));
				setCanvasMouseEvent();
			});
		}

	}
}
