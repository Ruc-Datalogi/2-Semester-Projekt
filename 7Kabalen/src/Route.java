import processing.core.PVector;

import java.util.ArrayList;
import java.util.LinkedList;

public class Route implements Comparable<Route> {
    /*
        Route should contain a unique number for identification
        Could contain capacity
     */
    public ArrayList<Vertex> assignedVertices;
    float cost;
    float savings;
    Route() {
        assignedVertices = new ArrayList<Vertex>();
        cost             = 0;
        savings          = 0;
    }

    public void addVertex(Vertex vertex){
        cost += vertex.d;
        assignedVertices.add(vertex);

        if(vertex.et < cost){
            System.out.println("ya dunn");
        }
    }
    //Just for cost calculations
    public void addAllVertices(ArrayList<Vertex> addedRoutes){
        for(int i=0;i<addedRoutes.size();i++){
            cost+=addedRoutes.get(i).d;
        }
    }

    @Override
    public String toString() {
        String text = null;
        for(Vertex vertex : assignedVertices){
            text = "Savings: " + savings;
        }
        return text;
    }

    @Override
    public int compareTo(Route r) {
        return Double.compare(r.savings,this.savings);
    }
}

