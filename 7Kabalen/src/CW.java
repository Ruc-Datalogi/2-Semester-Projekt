import processing.core.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

public class CW {
    HashMap <Vertex, ArrayList<Vertex>> LinkedVertices;
    ArrayList<Vertex> vertexArrayList;
    int vertexAmount;
    private final static int vehicleAmount = 25;
    //Degeneracy.
    PApplet daddy;

    CW (ArrayList<Vertex> vertexArrayList, PApplet parent) {
        this.vertexArrayList = vertexArrayList;
        this.vertexAmount    = vertexArrayList.size()-1;
        LinkedVertices       = new HashMap<Vertex, ArrayList<Vertex>>();
        this.daddy           = parent;

    }

    void initRoute() {
        for (int i = 1; i < vertexAmount; i++) {
            //Create a temporary vertex for every vertex in the
            //solomon data.
            Vertex tempVertex = vertexArrayList.get(i);

            //Create a temporary list where we link the initial solution so that
            //we have a list of the depot and the i'th vertex.
            ArrayList<Vertex> tempVertexList = new ArrayList<Vertex>();

            //add the depot initially
            tempVertexList.add(vertexArrayList.get(0));
            //and then the vertex so that they're ordered.
            tempVertexList.add(tempVertex);
            System.out.println(tempVertexList.size());
            System.out.println(tempVertex.position);

            //Put it into the HashMap <3
            LinkedVertices.put(tempVertex,tempVertexList);
        }
        System.out.println(LinkedVertices);
    }

    public float calculateSavings(){
        Vertex depot = vertexArrayList.get(0);
        int costi    = dist()
        int savings =
        return savings;
    }

    void displayRoute(){
        for (int i = 1; i < vertexAmount; i++) {
            ArrayList<Vertex> tempList = LinkedVertices.get(vertexArrayList.get(i));

            for (int j = 0; j < tempList.size()-1; j++){
                daddy.stroke(255,0,0);
                daddy.line(tempList.get(j).position.x*daddy.width/80,tempList.get(j).position.y*daddy.height/80,tempList.get(j+1).position.x*daddy.width/80,tempList.get(j+1).position.y*daddy.height/80);
            }
        }

    }
}
