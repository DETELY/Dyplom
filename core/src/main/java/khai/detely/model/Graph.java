package khai.detely.model;

import org.graphstream.graph.implementations.SingleGraph;

public class Graph {
    private org.graphstream.graph.Graph graph;

    private Table table;

    public Graph() {
        this.table = new Table();
        this.graph = new SingleGraph("Tutorial 1");
        graph.setStrict(true);
    }

    public void addNewNode(Cell topControl, Cell leftControl) {
        table.addNode(topControl, leftControl);
        graph.addNode(String.valueOf(table.getNodesCount())).setAttribute("ui.label", table.getNodesCount());
    }

    public void removeLastNode() {
        if (table.getNodesCount() == 0) {
            return;
        }
        graph.removeNode(String.valueOf(table.getNodesCount()));
        table.removeLastNode();
    }

    public org.graphstream.graph.Graph getGraphStream() {
        return graph;
    }

    public Table getTable() {
        return table;
    }
}
