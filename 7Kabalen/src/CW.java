import processing.core.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;


public class CW {
    //linkedList/linkedHashmap
    HashMap <Vertex, LinkedList<Vertex>> LinkedVertices;
    HashMap <Float, ArrayList<Vertex>> savingsMap;
    ArrayList<Float> savingsList;
    ArrayList<Vertex> vertexArrayList;
    int vertexAmount;
    private final static int vehicleAmount = 25;
    PApplet daddy;
    Vertex depot;

    /**
     * Initialization of the clarke-wright algorithm
     * @param vertexArrayList
     * @param parent
     */
    CW (ArrayList<Vertex> vertexArrayList, PApplet parent) {
        this.vertexArrayList = vertexArrayList;
        this.vertexAmount    = vertexArrayList.size()-1;
        LinkedVertices       = new HashMap<Vertex, LinkedList<Vertex>>();
        this.daddy           = parent;
        this.depot           = vertexArrayList.get(0);
        initRoute();
    }

    /**
     * Initialize the CW route
     * 1st step of the algorithm
     */
    void initRoute() {
        savingsMap  = new HashMap<>();
        savingsList = new ArrayList<>();

        for (int i = 1; i < vertexAmount; i++) {
            for(int j = 1; j < vertexAmount; j++) {
                //Create a temporary vertex for every vertex in the
                //solomon data.
                Vertex tempVertex = vertexArrayList.get(i);

                //Create a temporary list where we link the initial solution so that
                //we have a list of the depot and the i'th vertex.
                LinkedList<Vertex> tempVertexList = new LinkedList<Vertex>();

                //add the depot initially
                tempVertexList.add(vertexArrayList.get(0));

                //and then the vertex so that they're ordered.
                tempVertexList.add(tempVertex);

                //Put it into the HashMap <3
                LinkedVertices.put(tempVertex,tempVertexList);

                calculateSavings(vertexArrayList.get(i), vertexArrayList.get(j));
            }
        }
    }

    void runAlgorithm() {

    }

    /**
     * Caculates the cost, and subsequently savings between 3 vectors.
     * 2nd step of the algorithm
     * @param i a given vertex
     * @param j another given vertex
     */
    void calculateSavings(Vertex i, Vertex j){
        if(i != j) {
            float costj = depot.position.dist(j.position);
            float costi = depot.position.dist(i.position);
            float costij = i.position.dist(j.position);
            float savings = costi + costj - costij;


            ArrayList<Vertex> tempVertexList = new ArrayList<Vertex>();
            tempVertexList.add(i);
            tempVertexList.add(j);

            savingsList.add(savings);
            savingsMap.put(savings, tempVertexList);
        }
    }

    /**
     * 3rd step of the algorithm
     */

    int k = 0;
    int timeTotal = 0;

    void scanner(){
        savingsList.sort(Collections.reverseOrder());
        k = (k+1)%LinkedVertices.size();
        System.out.println(savingsList);

        for(int i = 0; i<k; i++){
            //get arraylist of the 2 vertices in relation to savings of the key.
            Vertex x = savingsMap.get(savingsList.get(i)).get(0);
            Vertex y = savingsMap.get(savingsList.get(i)).get(1);

            //Issue we can get duplicates for a savingslist value :C
            LinkedList<Vertex> tempList = new LinkedList<>();
                //Are they already linked?
                System.out.println(i);
                System.out.println(savingsMap.size());
                System.out.println(savingsList.size());
                if (!LinkedVertices.get(x).contains(y)) {
                    //Does the list exist?
                    System.out.println("u got through 1st step");
                    if(LinkedVertices.get(y).size()<3){
                        System.out.println("u got through 2nd step");
                        if (LinkedVertices.get(x).size() > 1) {
                            System.out.println("u got through 3rd step");
                            System.out.println(timeTotal);
                            System.out.println(y.et);

                            //Have we overstepped our timeTotal?
                            if (timeTotal < y.et || timeTotal == y.et) {
                                System.out.println("u got through all the steps hurray");
                                tempList.add(x);
                                tempList.add(y);
                                //STEP 3 Merge.
                                LinkedVertices.put(x, tempList);
                                //timeTotal += 10;
                            }
                        }
                    }
                }

        }
    }

    void displayRoute(){
        for (int i = 1; i < LinkedVertices.size(); i++) {
            LinkedList<Vertex> tempList = LinkedVertices.get(vertexArrayList.get(i));

            for (int j = 0; j < tempList.size()-1; j++){
                daddy.stroke((float) (Math.random()*255),(float) (Math.random()*255),(float) (Math.random()*255));
                daddy.line(tempList.get(j).position.x*daddy.width/80,tempList.get(j).position.y*daddy.height/80,tempList.get(j+1).position.x*daddy.width/80,tempList.get(j+1).position.y*daddy.height/80);
                if (j==tempList.size()-1){
                    daddy.line(tempList.get(j).position.x*daddy.width/80,tempList.get(j).position.y*daddy.height/80,tempList.get(0).position.x*daddy.width/80,tempList.get(0).position.y*daddy.height/80);
                }
            }
        }
    }
}
