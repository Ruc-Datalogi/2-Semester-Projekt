import processing.core.PVector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Vertex {

    PVector position;
    private int st, et, d, mt;
    private boolean isDepot;
    ArrayList<Vertex> edgeVertices;

    /**
     *
     * @param xy Position as a vector
     * @param st start-time
     * @param et end-time
     * @param d demand
     * @param mt mean time, or how long it takes to complete this task.
     */
    Vertex(PVector xy, int st, int et, int d, int mt){
        position = xy;
    }

    void linkVertices(){};

    /**
     * Takes a vertex as a parameter and denotes that this vertex is the parent's neighbour.
     * @param vertex
     */
    void addEdgeVertex(Vertex vertex){
        this.edgeVertices.add(vertex);
    }
}
