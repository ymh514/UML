package UML;

import java.util.ArrayList;

import javax.xml.ws.EndpointReference;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GroupObject extends Shape {

	protected double groupHeight;
	protected double groupWidth;
	protected Rectangle rectangle;

	protected ArrayList<Shape> componentList = new ArrayList<Shape>();

	public GroupObject() {
		// TODO Auto-generated constructor stu
	}

	public void group() {
		/*
		 * set rectangle
		 */

		Point2D startPoint = new Point2D(0, 0);
		Point2D endPoint = new Point2D(0, 0);

		/*
		 * find boundary
		 */
		for (int i = 0; i < componentList.size(); i++) {
			Shape tempShape = componentList.get(i);
			System.out.println(tempShape.getClass().getName());
			Point2D[] points = tempShape.getBoundary();
			startPoint = tempShape.getGroupRectStartPt(points[0], startPoint, i);
			endPoint = tempShape.getGroupRectEndPt(points[1], endPoint, i);
		}

		groupWidth = endPoint.getX() - startPoint.getX();
		groupHeight = endPoint.getY() - startPoint.getY();

		this.setLayoutX(startPoint.getX() - 10);
		this.setLayoutY(startPoint.getY() - 10);
		this.setHeight(groupHeight + 20);
		this.setWidth(groupWidth + 20);

		rectangle = new Rectangle(groupWidth + 20, groupHeight + 20);
		rectangle.setStroke(Color.BLACK);
		rectangle.setFill(Color.TRANSPARENT);
		rectangle.setDisable(true);
		this.getChildren().add(rectangle);

		/*
		 * put component on group object
		 */
		for (int i = 0; i < componentList.size(); i++) {
			componentList.get(i).setLayoutX(componentList.get(i).getLayoutX() - this.getLayoutX());
			componentList.get(i).setLayoutY(componentList.get(i).getLayoutY() - this.getLayoutY());
			componentList.get(i).setDisable(true);
			componentList.get(i).setSelected(false);
			componentList.get(i).setShowSelect(false);

			/*
			 * line object judge
			 */
			if (componentList.get(i).getClass().getSuperclass().getName() == "UML.LineObject") {
				LineObject tempLine = (LineObject) componentList.get(i);

				tempLine.setConnectLineXYinGroup();
				tempLine.setEndObj();

				this.getChildren().add(tempLine.connectLine);

				if (tempLine.getEndObj() != null) {
					this.getChildren().add(tempLine.getEndObj());
				}
			} else {
				this.getChildren().add(componentList.get(i));
			}
		}
		this.setShowSelect(false);
	}

	@Override
	public void unGroup(ArrayList<Shape> shapeList) {
		// TODO Auto-generated method stub
		/*
		 * return component to canvas's shapeList
		 */
		for (int i = 0; i < componentList.size(); i++) {

			componentList.get(i).setLayoutX(componentList.get(i).getLayoutX() + this.getLayoutX());
			componentList.get(i).setLayoutY(componentList.get(i).getLayoutY() + this.getLayoutY());
			componentList.get(i).setSelected(false);
			componentList.get(i).setShowSelect(false);
			componentList.get(i).setDisable(false);

			if (componentList.get(i).getClass().getSuperclass().getName() == "UML.LineObject") {
				LineObject tempLine = (LineObject) componentList.get(i);

				tempLine.setConnectLineXY();
				tempLine.setEndObj();
				componentList.get(i).getChildren().add(tempLine.connectLine);

				if (tempLine.getEndObj() != null) {
					componentList.get(i).getChildren().add(tempLine.getEndObj());
				}
			} else {
				componentList.get(i).tunePortPosition();
			}
			shapeList.add(componentList.get(i));
		}
		// remove this group from shapeList
		shapeList.remove(this);
	}

	public ArrayList<Shape> getComponentList() {
		return this.componentList;
	}

	public void draw(Canvas canvas) {
		canvas.getChildren().add(this);
	}

	@Override
	public void setSelected(Boolean selectState) {
		// TODO Auto-generated method stub
		super.selectState = selectState;
		this.setShowSelect(selectState);

		for (int i = 0; i < componentList.size(); i++) {
			componentList.get(i).setSelected(selectState);
		}
	}

	@Override
	public void setShowSelect(Boolean showValue) {
		// TODO Auto-generated method stub
		if (showValue == true) {
			this.rectangle.setStroke(Color.BLUE);
			this.rectangle.setVisible(true);
		} else {
			this.rectangle.setVisible(false);
		}
	}

	public Point2D[] getBoundary() {
		Point2D[] points = new Point2D[2];
		points[0] = new Point2D(this.getLayoutX(), this.getLayoutY());
		points[1] = new Point2D(this.getLayoutX() + this.groupWidth, this.getLayoutY() + this.groupHeight);
		return points;
	}

	@Override
	public void tunePortPosition() {
		// TODO Auto-generated method stub

	}

}
