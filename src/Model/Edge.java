package Model;

import javafx.util.Pair;

public class Edge {
    private Vertex vertexA;
    private Vertex vertexB;

    public Edge(Vertex a, Vertex b) {
        vertexA = a;
        vertexB = b;
    }

    public Boolean hasVertex(Vertex v) {
        return v.equals(vertexA) || v.equals(vertexB);
    }

    public Vertex getEndpoint(Vertex v) {
        if (v.equals(vertexA)) {
            return vertexB;
        } else {
            return vertexA;
        }
    }

    public Pair<Vertex, Vertex> getVertex() {
        Pair retval = new Pair(vertexA, vertexB);
        return retval;
    }
}
