package khai.detely.viewmodel;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.LinkedList;
import java.util.List;

public class MainVM {

    private Graph graph = new SingleGraph("Tutorial 1");

    private List<List<Control>> controls=new LinkedList<>();

    public Graph getGraph() {
        return graph;
    }

    public List<List<Control>> getControls() {
        return controls;
    }
}
