package khai.detely.model;

import javafx.scene.control.Control;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Table implements Serializable {

    private List<List<Cell>> table = new LinkedList<>();

    public Table() {
        List row = new LinkedList();
        row.add(null);
        table.add(row);
    }

    public void addNode(Cell topComponent, Cell leftComponent) {
        table.get(0).add(topComponent);
        LinkedList row = new LinkedList();
        row.add(leftComponent);
        table.add(row);
    }

    public void removeLastNode() {
        if (table.size() == 1) {
            return;
        }
        table.remove(table.size() - 1);
        table.get(0).remove(table.get(0).size() - 1);
    }

    public void addRelation(int rowIndex, int columnIndex, Cell component) {
        table.get(rowIndex).add(columnIndex, component);
    }

    public Cell getComponent(int rowIndex, int columnIndex) {
        return table.get(rowIndex).get(columnIndex);
    }

    public int getNodesCount() {
        return table.get(0).size() - 1;
    }

    public int getRowIndex(Cell cell) {
        int counter = -1;
        for (List<Cell> ts : table) {
            counter++;
            if (ts.contains(cell)) {
                break;
            }
        }
        return counter;
    }

    public int getColumnIndex(Cell cell) {
        return table.get(getRowIndex(cell)).indexOf(cell);
    }

    public int getRowIndex(Control component) {
        return getRowIndex(getCell(component));
    }

    public int getColumnIndex(Control component) {
        return getColumnIndex(getCell(component));
    }

    public Cell getCell(Control component) {
        return table
            .stream()
            .flatMap(Collection::stream)
            .filter(Objects::nonNull)
            .filter(cell -> cell.getComponent().equals(component))
            .findFirst().orElse(new Cell(null, ""));
    }

    public Cell getCell(String name) {
        return table
            .stream()
            .flatMap(Collection::stream)
            .filter(Objects::nonNull)
            .filter(cell -> cell.getName().equals(name))
            .findFirst().orElse(new Cell(null, ""));
    }

    public Cell getLastTopCell() {
        return table.get(0).get(table.get(0).size() - 1);
    }

    public Cell getLastLeftCell() {
        return table.get(table.size() - 1).get(0);
    }

    public List<Cell> getAllRelationAndHeadersCell(Cell topCell) {
        assert Objects.isNull(topCell) : "topCell is null";
        List<Cell> cells = new LinkedList<>();
        cells.add(topCell);
        cells.addAll(table.get(table.get(0).indexOf(topCell)));
        return cells;
    }
}
