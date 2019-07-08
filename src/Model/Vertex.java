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

    public void connectVertex(Vertex v) {
        if (!isConnected(v)) {
            Edge temp = new Edge(this, v);
            edgeList.add(temp);
            v.addEdge(temp);
            degree++;
            v.degree++;
        }
    }

    public void disconnectVertex(Vertex v) {
        for (Edge e: edgeList) {
            if (e.hasVertex(v)) {
                edgeList.remove(e);
                v.edgeList.remove(e);
            }
        }
    }

    public Boolean isConnected(Vertex v) {
        for (Edge e: edgeList) {
            if (e.hasVertex(v)) {
                return true;
            }
        }
        return false;
    }

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

    public void addEdge(Edge e) {
        edgeList.add(e);
    }

    public void removeEdge(Edge e) {
        edgeList.remove(e);
    }

    public ArrayList<Edge> getEdgeList() {
        return edgeList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getDegree() {
        return degree;
    }
}
