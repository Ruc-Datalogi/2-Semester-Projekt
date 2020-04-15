import processing.core.PApplet;
import processing.core.PVector;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Vertex {
    PVector position;
    int st, et, d, mt;
    float distCentroid = 100000;
    private boolean isDepot;
    boolean isEdge;

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
        this.d   = d;
        this.et  = et;
        this.mt  = mt;
        this.st  = st;
    }

    boolean isDepot(){
        return this.isDepot;
    }

    void setDepot(){
        isDepot = true;
    }


}
