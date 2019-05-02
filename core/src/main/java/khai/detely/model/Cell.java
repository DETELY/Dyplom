package khai.detely.model;

import javafx.scene.control.Control;

public class Cell <T extends Control> {

    private T component;


    public Cell(T component) {
        this.component = component;
    }

    public T getComponent() {
        return component;
    }
}
