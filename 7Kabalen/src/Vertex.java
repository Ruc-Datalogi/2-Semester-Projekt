import processing.core.PApplet;
import processing.core.PVector;
import java.util.ArrayList;

public class Vertex {

    PApplet parent;
    PVector position;
    private int st, et, d, mt;
    private boolean isDepot;
    ArrayList<Vertex> edgeVertices;
    private int height,width;

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
        //if vertex is depot display as red
        if(isDepot){
            parent.fill(255,0,0);
            parent.text("pos x " + position.x + " " + "pos y " + position.y, getDrawWidth((int) position.x)-20,getDrawHeight((int) position.y)-5);
        } else {
            parent.fill(0,255,0);
            parent.text("pos x " + position.x +" " + "pos y " + position.y, getDrawWidth((int) position.x)-20,getDrawHeight((int) position.y)-5);
        }


        parent.fill(255);
        parent.ellipse(getDrawWidth((int) position.x),getDrawHeight((int) position.y), 8, 8); //the multiplier basically just spreads them out
        //Please figure out a way to translate the coordinates from the data to the visuals without multiplying by arbitrary values.

    }
    public int getDrawWidth(int x){
        if (width==0){
            this.width=parent.width;
        }
        return x*width/100;
    }
    public int getDrawHeight(int x){
        System.out.println(height);
        if (height==0){
            this.height=parent.height;
        }
        return x*height/100;

    }
    void setParent(PApplet p) {
        parent = p;
        this.height=parent.height;
        this.width=parent.width;
    }

    void setDepot(){
        isDepot = true;
    }
}
