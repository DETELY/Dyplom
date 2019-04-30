package khai.detely.view.controllers;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import khai.detely.viewmodel.MainVM;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.layout.Layout;
import org.graphstream.ui.layout.Layouts;
import org.graphstream.ui.swingViewer.GraphRenderer;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.Viewer;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public SwingNode swingNode;
    @FXML
    public BorderPane rootPane;
    @FXML
    public GridPane gridPane;

    private MainVM mainVM;

    Graph graph = new MultiGraph("Tutorial 1");

    private int counter = 1;

    public MainController() {
        this.mainVM=new MainVM();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);

        Layout layout = Layouts.newLayoutAlgorithm();
        viewer.enableAutoLayout(layout);
        GraphRenderer graphRenderer = Viewer.newGraphRenderer();
        ViewPanel graphViewPanel = viewer.addView(Viewer.DEFAULT_VIEW_ID, graphRenderer, false);

        graphViewPanel.setPreferredSize(new Dimension(700, 300));

        swingNode.setContent(graphViewPanel);
    }

    @FXML
    private void btnAddCol_Button(ActionEvent event) {
        TextField topNode=getNewTextField();
        TextField rightNode=getNewTextField();



        gridPane.add(topNode, 0, counter);
        gridPane.add(rightNode, counter, 0);
        for (int i = 1; i <= counter; i++) {
            TextField textField=getNewTextField();
            gridPane.add(textField, i, counter);
        }
        graph.addNode(String.valueOf(counter));
        counter++;
    }

    private TextField getNewTextField(){
        TextField textField=new TextField("-");
        textField.setPrefWidth(10);

        textField.textProperty().addListener(new javafx.beans.value.ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable, String oldValue, String newValue) {
                gridPane.getChildren().filtered(obj->obj.equals(observable));
            }
        });
        return textField;
    }
}
