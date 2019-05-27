package khai.detely.viewmodel;


import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import khai.detely.AppConstants;
import khai.detely.model.Cell;
import khai.detely.model.Graph;
import khai.detely.utils.Validator;
import khai.detely.view.ViewConstants;
import khai.detely.view.ViewUtil;
import khai.detely.view.contexts.FillContext;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Objects;

public class MainVM {

    private static final Logger log = Logger.getLogger(MainVM.class);

    private Graph graph;

    public MainVM() {
        this.graph = new Graph();
    }

    public Graph getGraph() {
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

    public Button getNewButton() {
        Button button = new Button("-");
        button.setPrefWidth(50);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                Cell cell = graph.getTable().getCell(button);
                FillContext.capacity = cell.capacity;
                FillContext.windowWidth = cell.windowWidth;
                FillContext.transmissionProbability = cell.transmissionProbability;
                FillContext.acknowledgmentProbability = cell.acknowledgmentProbability;
                FillContext.channelTime = cell.channelTime;
                FillContext.delay = cell.delay;
                Stage stage = ViewUtil.showModalView(ViewConstants.ViewPageName.FILL_CELL.getName(), AppConstants.PROGRAM_NAME, false);
                stage.addEventFilter(WindowEvent.WINDOW_HIDDEN, event1 -> {
                    cell.capacity = FillContext.capacity;
                    cell.windowWidth = FillContext.windowWidth;
                    cell.transmissionProbability = FillContext.transmissionProbability;
                    cell.acknowledgmentProbability = FillContext.acknowledgmentProbability;
                    cell.channelTime = FillContext.channelTime;
                    cell.delay = FillContext.delay;

                    button.setText(String.valueOf(cell.capacity));
                    if (Objects.isNull(graph.getGraphStream().getEdge(String.valueOf(cell.hashCode())))) {
                        graph.getGraphStream().addEdge(String.valueOf(cell.hashCode()),
                            graph.getTable().getRowIndex(cell) - 1,
                            graph.getTable().getColumnIndex(cell) - 1).setAttribute("ui.label", cell.capacity);
                    }else {
                        graph.getGraphStream().getEdge(String.valueOf(cell.hashCode())).setAttribute("ui.label", cell.capacity);
                    }
                });

            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        });
        return button;
    }

    public boolean validateDirectionTextField(String str) {
        if (!Validator.validateDirectionTextField(str)) {
            return false;
        }
        Cell cell = graph.getTable().getCell(str);
        return Objects.nonNull(cell.getComponent());
    }
}
