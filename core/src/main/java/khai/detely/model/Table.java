package khai.detely.model;

import javafx.scene.control.Control;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Table implements Serializable {
    private List<Cell> topHeaders = new LinkedList<>();
    private List<Cell> rightHeaders = new LinkedList<>();

    private List<List<Cell>> content = new LinkedList<>();

    private Control defaultContentComponent;

    public Table(Control defaultContentComponent) {
        this.defaultContentComponent = defaultContentComponent;
    }

    public void addHeader(String name, Control component) {
        Cell cell = new Cell(name, component);
        topHeaders.add(cell);
        rightHeaders.add(cell);

    }


    public class Cell {
        String name;
        Control component;

        public Cell(String name, Control component) {
            this.name = name;
            this.component = component;
        }
    }
}
