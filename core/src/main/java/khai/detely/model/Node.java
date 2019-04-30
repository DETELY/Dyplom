package khai.detely.model;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Node {
    private String name;
    private UUID id;

    private List<Node> linkedNodes = new LinkedList<Node>();

    private int weight;

    public Node(String name) {
        this.name = name;
        this.id = UUID.randomUUID();
    }

    public Node(Node node) {
        this.name = node.name;
        this.id = node.id;
        this.linkedNodes = node.linkedNodes;
        this.weight = node.weight;
    }

    //region getters/setters

    public String getName() {
        return name;
    }

    public Node setName(String name) {
        this.name = name;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public List<Node> getLinkedNodes() {
        return linkedNodes;
    }

    public Node setLinkedNodes(List<Node> linkedNodes) {
        this.linkedNodes = linkedNodes;
        return this;
    }

    public int getWeight() {
        return weight;
    }

    public Node setWeight(int weight) {
        this.weight = weight > 0 ? weight : this.weight;
        return this;
    }

    //endregion

    public void addLinkedNode(Node node) {
        linkedNodes.add(node);
    }

    public void addAllLinkedNode(List<Node> nodes) {
        linkedNodes.addAll(nodes);
    }
}
