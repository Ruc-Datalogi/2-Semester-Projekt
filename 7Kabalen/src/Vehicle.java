import processing.core.PVector;

import java.util.ArrayList;

public class Vehicle {



    /*
        Vehicle should contain a unique number for identification
        Could contain capacity
     */
    public int id;
    public ArrayList<Vertex> assignedRouted;

    Vehicle(int vehicleID){
        this.id=vehicleID;
        assignedRouted = new ArrayList<Vertex>();
    }

    void addAssignment(Vertex newAssignment){
        assignedRouted.add(newAssignment);
    }

    @Override
    public String toString() {
        String veritcesText="";
        for (int i=0;i<assignedRouted.size();i++){
            veritcesText=veritcesText + "Vertex [" + assignedRouted.get(i).position.x +", " + assignedRouted.get(i).position.y + "]";
        }
        return "Vehicle[" + this.id + "] has assignments of the vericies:" +veritcesText+"length"+this.getRouteDistance();
    }

    public float getRouteDistance() {
        float total = 0;
        // assign each vehicle routing distance hjælp skkkkkkkkkrt

        for (int j = 0; j < this.assignedRouted.size()-1; j++) {
            PVector a = this.assignedRouted.get(j).position;
            PVector b = this.assignedRouted.get((j + 1)%(this.assignedRouted.size()-1)).position;
            total += a.dist(b);
        }

        return total;
    }






}

