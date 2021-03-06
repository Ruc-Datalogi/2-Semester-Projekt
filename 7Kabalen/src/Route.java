import processing.core.PVector;

import java.util.ArrayList;
import java.util.LinkedList;

public class Route implements Comparable<Route> {

    //Getters and setters
    public ArrayList<Vertex> getAssignedVertices() {
        return assignedVertices;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getSavings() {
        return savings;
    }

    public void setSavings(float savings) {
        this.savings = savings;
    }

    public void setAssignedVertices(ArrayList<Vertex> assignedVertices) {
        this.assignedVertices = assignedVertices;
    }

    public void setTimeTally(float tally) {
        this.timeTally = tally;
    }

    public float getTimeTally() {
        return timeTally;
    }

    /*
    Route should contain a unique number for identification
                Could contain capacity
    */
    private ArrayList<Vertex> assignedVertices;
    private float cost;
    private float savings;
    private float timeTally;

    Route() {
        assignedVertices = new ArrayList<Vertex>();
        cost = 0;
        savings = 0;
    }

    /**
     * Adds a vertex to the route and simulatinously calculates the cost of
     * the entire route.
     *
     * @param vertex
     */
    public void addVertex(final Vertex vertex) {
        cost += vertex.d;
        assignedVertices.add(vertex);
    }

    //Just for cost calculations
    public void addAllVertices(ArrayList<Vertex> addedRoutes) {
        for (int i = 0; i < addedRoutes.size(); i++) {
            cost += addedRoutes.get(i).d;
        }
    }
    public float getLength(){
        if(assignedVertices.size()<2){
            //Can't calculate distance between 1 or less points.
            return 0;
        }
        float totalLength=0;
        for(int i=1;i<assignedVertices.size();i++){
            totalLength=totalLength+assignedVertices.get(i).position.dist(assignedVertices.get(i-1).position);
        }
        totalLength+=assignedVertices.get(0).position.dist(assignedVertices.get(assignedVertices.size()-1).position); //Back to start from last
        return totalLength;
    }

    @Override
    public String toString() {
        String text = null;
        for (Vertex vertex : assignedVertices) {
            text = "Savings: " + savings;
        }
        return text;
    }

    @Override
    public int compareTo(Route r) {
        return Double.compare(r.savings, this.savings);
    }
}

