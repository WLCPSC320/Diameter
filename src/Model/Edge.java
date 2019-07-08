package Model;

import javafx.util.Pair;

public class Edge {
    private Vertex vertexA;
    private Vertex vertexB;

    public Edge(Vertex a, Vertex b) {
        vertexA = a;
        vertexB = b;
    }

    // EFFECTS: returns true if an endpoint contains v
    public Boolean hasVertex(Vertex v) {
        return v.equals(vertexA) || v.equals(vertexB);
    }

    // EFFECTS: returns the endpoint of the edge that is not v
    public Vertex getEndpoint(Vertex v) {
        if (v.equals(vertexA)) {
            return vertexB;
        } else {
            return vertexA;
        }
    }

    // EFFECTS: returns both endpoints as a pair
    public Pair<Vertex, Vertex> getVertex() {
        Pair retval = new Pair(vertexA, vertexB);
        return retval;
    }
}
