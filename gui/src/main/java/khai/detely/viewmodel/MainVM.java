package khai.detely.viewmodel;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import khai.detely.model.Cell;
import khai.detely.utils.Validator;

import java.util.Objects;

public class MainVM {

    private khai.detely.model.Graph graph;

    public MainVM() {
        this.graph = new khai.detely.model.Graph();
    }

    public khai.detely.model.Graph getGraph() {
        return graph;
    }

    public TextField getNewTextField() {
        TextField textField = new TextField("-");
        textField.setPrefWidth(50);

        TextFormatter<String> formatter = new TextFormatter<>(change -> {
            if (!change.getText().matches("[0-9.-]*")) {
                change.setText("");
            }
            return change;
        });
        textField.setTextFormatter(formatter);
        textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                return;
            }
            if (Validator.validDouble(textField.getText())) {
                Cell cell = graph.getTable().getCell(textField);
                if (Objects.isNull(graph.getGraphStream().getEdge(String.valueOf(cell.hashCode())))) {
                    graph.getGraphStream().addEdge(String.valueOf(cell.hashCode()),
                        graph.getTable().getRowIndex(cell) - 1,
                        graph.getTable().getColumnIndex(cell) - 1).setAttribute("ui.label", textField.getText());
                }
            } else {
                textField.setText("-");
            }
        });
        return textField;
    }

    public boolean validateDirectionTextField(String str) {
        if (!Validator.validateDirectionTextField(str)) {
            return false;
        }
        Cell cell = graph.getTable().getCell(str);
        return Objects.nonNull(cell.getComponent());
    }
}
