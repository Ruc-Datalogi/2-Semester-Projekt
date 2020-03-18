import processing.core.*;

import java.util.ArrayList;
import java.util.HashMap;


public class CW {
    HashMap <Vertex, ArrayList<Vertex>> LinkedVertices;
    ArrayList<Vertex> vertexArrayList;
    HashMap <ArrayList<Vertex>, Float> savingsList;
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

            //Put it into the HashMap <3
            LinkedVertices.put(tempVertex,tempVertexList);
        }
    }

    ArrayList<Vertex> preVertex;

    //Hvilket id tager vi fra? Routen eller hele vertexArrayList?
    void calculateSavings(Vertex i, Vertex j){
        Vertex depot  = vertexArrayList.get(0);
        float costj   = depot.position.dist(j.position);
        float costi   = depot.position.dist(i.position);
        float costij  = i.position.dist(j.position);
        float savings = costi + costj - costij;

        ArrayList<Vertex> tempVertexList = new ArrayList<Vertex>();
        tempVertexList.add(i);
        tempVertexList.add(j);

        //istedet for at iterer post, så gøre det før, i.e. tjekke den forrige position i vores hashmap
        //se om savingsne er større hvis ikke så bare put bagefter men hvis den er større så replace og
        //add det gamle key-value pair bagpå 0<-bigdik

        //major spaghettikode *sips cumchalice*
        if(preVertex != null){
            if(savingsList.get(preVertex) < savings) {
            savingsList.put(tempVertexList, savings);
        } else {
                savingsList.put(tempVertexList, savings);
            }
        }
    }


    void scanner(){
        for(int i = 0; i<vertexAmount; i++){

        }
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
