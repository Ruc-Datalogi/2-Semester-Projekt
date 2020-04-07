import processing.core.PVector;

import java.util.ArrayList;

public class Vehicle implements Comparable<Vehicle> {
    /*
        Vehicle should contain a unique number for identification
        Could contain capacity
     */
    public ArrayList<Vertex> assignedVertices;
    float cost;
    float savings;

    Vehicle(){
        assignedVertices = new ArrayList<Vertex>();
        cost             = 0;
        savings          = 0;
    }

    void addAssignment(Vertex newAssignment){
        assignedVertices.add(newAssignment);
    }

    public float getRouteDistance() {
        float total = 0;
        // assign each vehicle routing distance hjælp skkkkkkkkkrt

        for (int j = 0; j < this.assignedVertices.size()-1; j++) {
            PVector a = this.assignedVertices.get(j).position;
            PVector b = this.assignedVertices.get((j + 1)%(this.assignedVertices.size()-1)).position;
            total += a.dist(b);
        }
        return total;
    }

    @Override
    public int compareTo(Vehicle vehicle) {
        return 0;
    }
}

