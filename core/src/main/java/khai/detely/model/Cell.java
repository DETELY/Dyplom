package khai.detely.model;

import javafx.scene.control.Control;

import java.util.Objects;

public class Cell<T extends Control> {

    private T component;

    private String name;

    public double capacity = 1;
    public int windowWidth = 1;
    public double transmissionProbability = 0;
    public double acknowledgmentProbability = 0;
    public double channelTime = 0;
    public double delay = 0;


    public Cell(T component, String name) {
        this.component = component;
        this.name = name;
    }

    public T getComponent() {
        return component;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cell)) {
            return false;
        }
        Cell<?> cell = (Cell<?>) o;
        return Objects.equals(component, cell.component) &&
            Objects.equals(name, cell.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(component, name);
    }
}
