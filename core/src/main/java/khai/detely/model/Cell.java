package khai.detely.model;

import javafx.scene.control.Control;

public class Cell <T extends Control> {

    private T component;

    private String name;

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
}
