package khai.detely.view.controllers;

import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import khai.detely.model.Cell;
import khai.detely.utils.Validator;
import khai.detely.viewmodel.MainVM;
import org.graphstream.ui.layout.Layout;
import org.graphstream.ui.layout.Layouts;
import org.graphstream.ui.swingViewer.GraphRenderer;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.Viewer;

import java.awt.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public SwingNode swingNode;
    @FXML
    public BorderPane rootPane;
    @FXML
    public GridPane gridPane;
    @FXML
    public Button removeButton;

    private MainVM mainVM;

    khai.detely.model.Graph graph;

    public MainController() {
        this.mainVM = new MainVM();
        graph = new khai.detely.model.Graph();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Viewer viewer = new Viewer(graph.getGraphStream(), Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);

        Layout layout = Layouts.newLayoutAlgorithm();
        viewer.enableAutoLayout(layout);
        GraphRenderer graphRenderer = Viewer.newGraphRenderer();
        ViewPanel graphViewPanel = viewer.addView(Viewer.DEFAULT_VIEW_ID, graphRenderer, false);

        graphViewPanel.setPreferredSize(new Dimension(700, 300));

        swingNode.setContent(graphViewPanel);
    }

    @FXML
    private void btnAddCol_Button(ActionEvent event) {
        Button leftControl = new Button("");
        leftControl.setPrefWidth(50);
        Button topControl = new Button("");
        topControl.setPrefWidth(50);
        graph.addNewNode(new Cell(topControl), new Cell(leftControl));
        leftControl.setText(String.valueOf(graph.getTable().getNodesCount()));
        topControl.setText(String.valueOf(graph.getTable().getNodesCount()));

        gridPane.add(topControl, 0, graph.getTable().getNodesCount());
        gridPane.add(leftControl, graph.getTable().getNodesCount(), 0);
        for (int i = 1; i <= graph.getTable().getNodesCount(); i++) {
            TextField textField = getNewTextField();
            gridPane.add(textField, i, graph.getTable().getNodesCount());
            graph.getTable().addRelation(graph.getTable().getNodesCount(), i, new Cell(textField));
        }
        removeButton.setDisable(false);
    }

    @FXML
    public void btnRemoveCol_Button(ActionEvent actionEvent) {
        gridPane.getChildren().removeIf(node -> GridPane.getRowIndex(node) == graph.getTable().getNodesCount());
        gridPane.getChildren().removeIf(node -> GridPane.getColumnIndex(node) == graph.getTable().getNodesCount());
        graph.removeLastNode();
        if (graph.getTable().getNodesCount() == 0) {
            removeButton.setDisable(true);
        }
    }

    private TextField getNewTextField() {
        TextField textField = new TextField("-");
        textField.setPrefWidth(50);

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

            }
        });
        return textField;
    }

}
