package khai.detely.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Direction {

    private SimpleIntegerProperty id;
    private SimpleStringProperty startNode;
    private SimpleStringProperty endNode;

    public Direction(int id, String startNode, String endNode) {
        this.id = new SimpleIntegerProperty(id);
        this.startNode = new SimpleStringProperty(startNode);
        this.endNode = new SimpleStringProperty(endNode);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getStartNode() {
        return startNode.get();
    }


    public void setStartNode(String startNode) {
        this.startNode.set(startNode);
    }

    public String getEndNode() {
        return endNode.get();
    }


    public void setEndNode(String endNode) {
        this.endNode.set(endNode);
    }
}
