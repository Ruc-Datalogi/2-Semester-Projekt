import processing.core.PApplet;
import processing.core.PVector;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Vertex {
    public PVector getPosition() {
        return position;
    }

    public void setPosition(PVector position) {
        this.position = position;
    }

    PVector position;
    int st, et, d, mt;
    float distCentroid = 10000000;
    private boolean isDepot;
    
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

    @Override
    public String toString() {
        return position.toString() + "d: " + this.d;
    }

    boolean isDepot(){
        return this.isDepot;
    }

    void setDepot(){
        isDepot = true;
    }
}
