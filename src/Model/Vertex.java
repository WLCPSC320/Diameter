package Model;

import javafx.util.Pair;

import java.util.ArrayList;

public class Vertex {
    private String name;
    private ArrayList<Edge> edgeList;
    private int degree;

    public Vertex(String name) {
        this.name = name;
        edgeList = new ArrayList<>();
        degree = 0;
    }

    // MODIFIES: this, v
    // EFFECTS: Adds an edge between this and v, increments degree by one for this and v
    public void connectVertex(Vertex v) {
        if (!isConnected(v)) {
            Edge temp = new Edge(this, v);
            edgeList.add(temp);
            v.addEdge(temp);
            degree++;
            v.degree++;
        }
    }

    // MODIFIES: this, v
    // EFFECTS: Removes an edge between this and v, decrements degree by one for this and v
    public void disconnectVertex(Vertex v) {
        for (Edge e: edgeList) {
            if (e.hasVertex(v)) {
                edgeList.remove(e);
                v.edgeList.remove(e);
                degree--;
                v.degree--;
            }
        }
    }

    // EFFECTS: Returns true if this is connected to v by an edge
    public Boolean isConnected(Vertex v) {
        for (Edge e: edgeList) {
            if (e.hasVertex(v)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: Returns a list of neighbouring vertices
    public ArrayList<Vertex> getNeighbours() {
        ArrayList<Vertex> retval = new ArrayList<>();

        for (Edge e: edgeList) {
            Pair<Vertex,Vertex> pairs = e.getVertex();
            if (pairs.getKey() == this) {
                retval.add(pairs.getValue());
            } else {
                retval.add(pairs.getKey());
            }
        }

        return retval;
    }

    // MODIFIES: this
    // EFFECTS: Adds an edge to edgeList
    public void addEdge(Edge e) {
        edgeList.add(e);
    }

    // MODIFIES: this
    // EFFECTS: Removes an edge from edgeList
    public void removeEdge(Edge e) {
        edgeList.remove(e);
    }

    // EFFECTS: returns edgeList
    public ArrayList<Edge> getEdgeList() {
        return edgeList;
    }

    // MODIFIES: this
    // EFFECTS: sets the name of this
    public void setName(String name) {
        this.name = name;
    }

    // EFFECTS: returns name
    public String getName() {
        return name;
    }

    // EFFECTS: returns degree
    public int getDegree() {
        return degree;
    }
}
