import processing.core.*;

import java.util.ArrayList;
import java.util.HashMap;


public class CW {
    //linkedList/linkedHashmap
    HashMap <Vertex, ArrayList<Vertex>> LinkedVertices;
    HashMap <Float, ArrayList<Vertex>> savingsMap;
    ArrayList<Float> savingsList;
    ArrayList<Vertex> vertexArrayList;
    int vertexAmount;
    private final static int vehicleAmount = 25;
    //Degeneracy.
    PApplet daddy;

    /**
     * Initialization of the clarke-wright algorithm
     * @param vertexArrayList
     * @param parent
     */
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
            calculateSavings(vertexArrayList.get(i),vertexArrayList.get(i-1));
        }
    }

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

        //major spaghettikode *sips cumchalice*
        savingsList = new ArrayList<>();
        savingsList.add(savings);
        savingsMap  = new HashMap<>();
        savingsMap.put(savings,tempVertexList);
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
