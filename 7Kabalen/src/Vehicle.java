import java.util.ArrayList;

public class Vehicle {
    /*
        Vehicle should contain a unique number for identification
        Could contain capacity
     */
    private int id;
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
        return "Vehicle[" + this.id + "] has assignments of the vericies:" +veritcesText;
    }
}

