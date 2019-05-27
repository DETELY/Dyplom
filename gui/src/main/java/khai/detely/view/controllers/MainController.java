package khai.detely.view.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import khai.detely.AppConstants;
import khai.detely.model.Cell;
import khai.detely.model.Direction;
import khai.detely.model.Graph;
import khai.detely.view.ViewConstants;
import khai.detely.view.ViewUtil;
import khai.detely.viewmodel.MainVM;
import org.apache.log4j.Logger;
import org.graphstream.ui.layout.Layout;
import org.graphstream.ui.layout.Layouts;
import org.graphstream.ui.swingViewer.GraphRenderer;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.Viewer;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private static final Logger log = Logger.getLogger(MainController.class);


    @FXML
    public SwingNode swingNode;
    @FXML
    public BorderPane rootPane;
    @FXML
    public GridPane gridPane;
    @FXML
    public Button removeButton;
    @FXML
    public TextField startNodeTextField;
    @FXML
    public TextField endNodeTextField;
    @FXML
    public TableView directionTableView;


    private ObservableList<Direction> directions;
    private MainVM mainVM;

    public MainController() {
        this.mainVM = new MainVM();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Viewer viewer = new Viewer(mainVM.getGraph().getGraphStream(), Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        directions = FXCollections.observableArrayList();

        Layout layout = Layouts.newLayoutAlgorithm();
        viewer.enableAutoLayout(layout);
        GraphRenderer graphRenderer = Viewer.newGraphRenderer();
        ViewPanel graphViewPanel = viewer.addView(Viewer.DEFAULT_VIEW_ID, graphRenderer, false);
        graphViewPanel.setPreferredSize(new Dimension(700, 300));
        swingNode.setContent(graphViewPanel);
        initTableView();
    }

    @FXML
    private void btnAddCol_Button(ActionEvent event) {
        Button leftControl = new Button("");
        leftControl.setPrefWidth(50);
        Button topControl = new Button("");
        topControl.setPrefWidth(50);
        Graph graph = mainVM.getGraph();
        String index = String.valueOf(graph.getTable().getNodesCount() + 1);
        graph.addNewNode(new Cell(topControl, index), new Cell(leftControl, index));
        leftControl.setText(index);
        topControl.setText(index);

        gridPane.add(topControl, 0, graph.getTable().getNodesCount());
        gridPane.add(leftControl, graph.getTable().getNodesCount(), 0);
        for (int i = 1; i <= graph.getTable().getNodesCount(); i++) {
            Button btn=mainVM.getNewButton();
            gridPane.add(btn, i, graph.getTable().getNodesCount());
            graph.getTable().addRelation(graph.getTable().getNodesCount(), i, new Cell(btn, ""));
        }
        removeButton.setDisable(false);
    }

    @FXML
    public void btnRemoveCol_Button(ActionEvent actionEvent) {
        Graph graph = mainVM.getGraph();
        gridPane.getChildren().removeIf(node -> GridPane.getRowIndex(node) == graph.getTable().getNodesCount());
        gridPane.getChildren().removeIf(node -> GridPane.getColumnIndex(node) == graph.getTable().getNodesCount());
        graph.removeLastNode();
        if (graph.getTable().getNodesCount() == 0) {
            removeButton.setDisable(true);
        }
    }

    @FXML
    public void addDirection(ActionEvent actionEvent) {
        if (mainVM.validateDirectionTextField(endNodeTextField.getText())
            && mainVM.validateDirectionTextField(startNodeTextField.getText())) {
            directions.add(new Direction(directions.size() + 1, startNodeTextField.getText(), endNodeTextField.getText()));
        }
    }

    @FXML
    public void deleteDirection(ActionEvent actionEvent) {
        if (directions.size() > 0) {
            directions.remove(directions.size() - 1);
        }
    }

    private void initTableView() {
        directionTableView.setItems(directions);
        TableColumn<Direction, String> idColumn = new TableColumn<>("Id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        directionTableView.getColumns().add(idColumn);

        TableColumn<Direction, String> startColumn = new TableColumn<>("Start");
        startColumn.setCellValueFactory(new PropertyValueFactory<>("startNode"));
        directionTableView.getColumns().add(startColumn);

        TableColumn<Direction, String> endColumn = new TableColumn<>("End");
        endColumn.setCellValueFactory(new PropertyValueFactory<>("endNode"));
        directionTableView.getColumns().add(endColumn);
    }

    public void btnCreateChart_Button(ActionEvent actionEvent) {
        try {
            ViewUtil.showModalView(ViewConstants.ViewPageName.CHART.getName(), AppConstants.PROGRAM_NAME, false);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
