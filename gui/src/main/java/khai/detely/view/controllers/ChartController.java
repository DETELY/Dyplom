package khai.detely.view.controllers;

import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

import java.net.URL;
import java.util.ResourceBundle;

public class ChartController implements Initializable {
    public LineChart lineChart;
    public CategoryAxis xAxis;
    public NumberAxis yAxis;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
