import Model.Edge;
import Model.Vertex;
import javafx.util.Pair;

import java.util.ArrayList;

public class Diameter {
    private ArrayList<Vertex> vertexList;
    private ArrayList<Pair<Vertex,Vertex>> exploredPaths; // Prevents re-searching and searching for paths that aren't solutions
    private int diameter;
    private String starting; // Starting node for distance
    private String ending; // Ending node for distance (Arbitrary which one is start/end)

    public Diameter(ArrayList<Vertex> vertexList) {
        this.vertexList = vertexList;
        starting = "";
        ending = "";
        diameter = 0;
        exploredPaths = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: Returns the diameter
    public int getDiameter() {
        int retval = 0;

        for (Vertex start: vertexList) {
            int tempvalResult = 0;
            String tempEnding = "";
            for (Vertex dest: vertexList) {
                if (!start.equals(dest) && !isExplored(start,dest)) {
                    exploredPaths.add(new Pair(start,dest));
                    ArrayList<Vertex> traversedVertex = new ArrayList<>();
                    int temp = getShortestDistance(start, dest, traversedVertex);
                    if (temp > tempvalResult) {
                        tempvalResult = temp;
                        tempEnding = dest.getName();
                    }
                }
            }
            if (tempvalResult > retval) {
                retval = tempvalResult;
                starting = start.getName();
                ending = tempEnding;
            }
        }
        diameter = retval;
        return retval;
    }

    // EFFECTS: Returns the shortest distance between start and dest
    private int getShortestDistance(Vertex start, Vertex dest, ArrayList<Vertex> traversedVertex) {
        ArrayList<Edge> startEdgeList = start.getEdgeList(); // Get Edgelist or neighbours
        int retval = 1000; //Dummy return value
        if (containsEdgeDest(startEdgeList, dest)) { // If the dest is connected
            return 1; // Shortest distance from current node to that is 1
        } else {
            traversedVertex.add(start); // Mark that we traversed the current vertex
            for (Edge e: startEdgeList) {
                Vertex next = e.getEndpoint(start); //get neighbour
                if (!containsVertex(traversedVertex, next)) { // If the vertex is new
                    ArrayList<Vertex> newTraversed = copyTraversed(traversedVertex);
                    int temp = 1 + getShortestDistance(next, dest, newTraversed); //get the shortest distance to destination
                    if (retval > temp) { // If our return value is greater than shortest distance for vertex to dest
                        retval = temp; // Replace with shortest for all neighbour vertex visited so far
                        exploredPaths.add(new Pair(next, dest));
                    }
                }
            }
        }
        return retval; // retval is the shortest distance from start to dest
    }

    // EFFECTS: Returns true if edgeList contains dest, else false
    private Boolean containsEdgeDest(ArrayList<Edge> edgeList, Vertex dest) {
        for (Edge e: edgeList) {
            if (e.hasVertex(dest)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: Returns true if vertexList contains compareV, else false
    private Boolean containsVertex(ArrayList<Vertex> vertexList, Vertex compareV) {
        for (Vertex v: vertexList) {
            if (v.equals(compareV)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: Copies a list returning the copied list
    private ArrayList<Vertex> copyTraversed(ArrayList<Vertex> traversed) {
        ArrayList<Vertex> retval = new ArrayList<>();
        for (Vertex v: traversed) {
            retval.add(v);
        }
        return retval;
    }

    // EFFECTS: Returns true if path from start to dest has been explored, else false
    private Boolean isExplored(Vertex start, Vertex dest) {
        for (Pair<Vertex,Vertex> pair : exploredPaths) {
            if ((pair.getKey() == start && pair.getValue() == dest) || (pair.getKey() == dest && pair.getValue() == start)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: Prints out the start, end and diameter to console
    public void printPath() {
        System.out.println("Start Node: " + starting + ", End Node: " + ending + ", Diameter = " + diameter + ".");
    }
}
