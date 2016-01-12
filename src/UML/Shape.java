package UML;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Shape extends Pane {

	private double height = 150;
	private double halfHeight = 75;
	private double width = 120;
	private double halfWidth = 60;
	private double portSize = 10;
	private double halfPortSize = 5;

    private ArrayList<Point2D> anchors;


    private Port port1;
    private Port port2;
    private Port port3;
    private Port port4;

    private Text text;
    private Boolean portVisibility = false;

    public Shape(double w, double h) {
        anchors = new ArrayList<>(4);
        width = w;
        halfWidth = w/2;
        height = h;
        halfHeight = h/2;
        text = new Text("");
        this.getChildren().add(text);
    }

    protected void relocatePortsAndPoints() {
        port1 = new Port(-portSize, halfHeight - halfPortSize, portSize, portSize);
        port1.setDisable(true);
        port2 = new Port(halfWidth - halfPortSize, -portSize, portSize, portSize);
        port2.setDisable(true);
        port3 = new Port(halfWidth - halfPortSize, height, portSize, portSize);
        port3.setDisable(true);
        port4 = new Port(width, halfHeight - halfPortSize, portSize, portSize);
        port4.setDisable(true);

        anchors.add(new Point2D(this.getLayoutX(), this.getLayoutY() + height / 2));
        anchors.add(new Point2D(this.getLayoutX() + width / 2, this.getLayoutY()));
        anchors.add(new Point2D(this.getLayoutX() + width / 2, this.getLayoutY() + height));
        anchors.add(new Point2D(this.getLayoutX() + width, this.getLayoutY() + height / 2));
    }

    public void portVisible() {
        if(!portVisibility) {
            this.getChildren().addAll(port1, port2, port3, port4);
        }
        portVisibility = true;

    }
    public void portUnVisible() {
        if(portVisibility) {
            this.getChildren().removeAll(port1, port2, port3, port4);
        }
        portVisibility = false;
    }
    
    public void setName(String name) {
        text.setText(name);
    }
    public void draw(){
    	
    }
}
