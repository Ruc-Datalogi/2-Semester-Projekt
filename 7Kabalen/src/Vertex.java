import processing.core.PApplet;
import processing.core.PVector;
import java.util.ArrayList;

public class Vertex {

    PApplet parent;
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
     * @param mt mean-time, or how long it takes to complete this task.
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

    void display(){
        parent.fill(255);
        parent.ellipse(position.x*10, position.y*10, 8, 8); //the multiplier basically just spreads them out
    }

    void setParent(PApplet p) {
        parent = p;
    }
}