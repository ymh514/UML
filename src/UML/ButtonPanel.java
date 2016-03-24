package UML;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class ButtonPanel extends VBox {
	public VBox buttonPanel;
	public SelectBtn selectBtn;
	public AssocLineBtn assocLineBtn;
	public GeneLineBtn geneLineBtn;
	public CompLineBtn compLineBtn;
	public ClassBoxBtn classBoxBtn;
	public UseCaseBtn useCaseBtn;
	public ArrayList<Buttons> buttonsList = new ArrayList<Buttons>();
	
	private UML uml;
	
	
	public ButtonPanel(UML p){
		
		this.uml = p;
		
        buttonPanel = new VBox(10);
        buttonPanel.setPadding(new Insets(15, 15, 15, 15));
        selectBtn = new SelectBtn("selct",new ImageView("1.png"),this.uml.getCanvas());
        assocLineBtn = new AssocLineBtn("assocline",new ImageView("2.png"),this.uml.getCanvas());
        geneLineBtn = new GeneLineBtn("genelin",new ImageView("3.png"),this.uml.getCanvas());
        compLineBtn = new CompLineBtn("compline",new ImageView("4.png"),this.uml.getCanvas());
        classBoxBtn = new ClassBoxBtn("classbox",new ImageView("5.png"),this.uml.getCanvas());
        useCaseBtn = new UseCaseBtn("usecase",new ImageView("6.png"),this.uml.getCanvas());

        ToggleGroup btnGroup = new ToggleGroup();
        selectBtn.setToggleGroup(btnGroup);
        assocLineBtn.setToggleGroup(btnGroup);
        geneLineBtn.setToggleGroup(btnGroup);
        compLineBtn.setToggleGroup(btnGroup);
        classBoxBtn.setToggleGroup(btnGroup);
        useCaseBtn.setToggleGroup(btnGroup);
        
        buttonPanel.getChildren().addAll(selectBtn,assocLineBtn,geneLineBtn,compLineBtn,classBoxBtn,useCaseBtn);
        
        buttonsList.add(selectBtn);
        buttonsList.add(assocLineBtn);
        buttonsList.add(geneLineBtn);
        buttonsList.add(compLineBtn);
        buttonsList.add(classBoxBtn);
        buttonsList.add(useCaseBtn);

	}
	public VBox getButtonPanel(){
		return this.buttonPanel;
	}

}
