import processing.core.*;

import java.util.*;


public class CW {
    //linkedList/linkedHashmap
    HashMap <Vertex, LinkedList<Vertex>> LinkedVertices;
    //TODO
    LinkedList <Vertex> LinkedVertexList;
    HashMap <Float, ArrayList<Vertex>> savingsMap;
    ArrayList <ArrayList<Float>> SijList;
    ArrayList<Float> savingsList;
    //The integer key, when sorted, denotes from non increasing order where our savings are largest.
    TreeMap<Integer, Sij> sijMap;
    //TODO
    ArrayList<Vertex> vertexArrayList;
    //Import methods from gfx component
    gfxComponent gfxComponent;
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
        //init data lists
        savingsMap       = new HashMap<>();
        savingsList      = new ArrayList<>();
        sijMap           = new TreeMap<>();
        LinkedVertexList = new LinkedList<>();

        //first i loop generates the initial solution
        for (int i = 0; i < vertexAmount; i++) {
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

            //calculate the savings for every i to j vertex in the graph
            for(int j = 0; j < vertexAmount; j++) {
                calculateSavings(j, vertexArrayList.get(i), vertexArrayList.get(j));
            }
            sortSij();
            System.out.println(sijMap.get(1).s);
            System.out.println(sijMap.get(sijMap.size()-1).s);
        }

    }

    void runAlgorithm() {}

    /**
     * Caculates the cost, and subsequently savings between 3 vectors.
     * 2nd step of the algorithm
     * @param i a given vertex
     * @param j another given vertex
     */
    void calculateSavings(int k, Vertex i, Vertex j){
        if(i != j) {
            float costj   = depot.position.dist(j.position);
            float costi   = depot.position.dist(i.position);
            float costij  = i.position.dist(j.position);
            float savings = costi + costj - costij;

            Sij tempSij = new Sij(savings, i, j);

            ArrayList<Vertex> tempVertexList = new ArrayList<Vertex>();
            tempVertexList.add(i);
            tempVertexList.add(j);

            savingsList.add(savings);
            savingsMap.put(savings, tempVertexList);

            calculateSavingsDebug(i,j,savings);

            savingsList.sort(Collections.reverseOrder());

            sijMap.put(k, tempSij);
            System.out.println(sijMap.keySet());
        }
        System.out.println("Size of savingsMap data: " + savingsMap.size());
        System.out.println("Size of savingsList data: " + savingsList.size());
    }

    //Calculates on what index we should put our savings data.
    void sortSij() {
        for (int i = 1; i < sijMap.size(); i++) {
            for (int j = 1; j < sijMap.size(); j++) {
                float savingsi = sijMap.get(i).s;
                float savingsj = sijMap.get(j).s;

                if (savingsi > savingsj) {
                    Sij firstInd = sijMap.get(i);
                    sijMap.put(i, sijMap.get(j));
                    sijMap.put(j, firstInd);
                }
            }
        }
    }

    /**
     * 3rd step of the algorithm
     */

    int i = 0;
    int timeTotal = 0;

    void scanner(){
        i = (i+1)%LinkedVertices.size();
        System.out.println(savingsList);

        //get arraylist of the 2 vertices in relation to savings of the key.
        Vertex x = savingsMap.get(savingsList.get(i)).get(0);
        Vertex y = savingsMap.get(savingsList.get(i)).get(1);

        //Issue we can get duplicates for a savingslist value :C
        LinkedList<Vertex> tempList = new LinkedList<>();

        System.out.println("Current index: " + i);
        System.out.println("Analyzing vertices: " + x.toString() + y.toString());
        System.out.println("Analyzing list: " + LinkedVertices.get(x).toString());

        //Are they already linked?
        if (!LinkedVertices.get(x).contains(y)) {
            //Are they already involved in a list?
            System.out.println("u got through 1st step");

            //find a better way of doing this, for depot we get size() to be 2
            //in fact we dont ever surpass 2 as the arrays are always pairs of 2

            if(LinkedVertices.get(y).size()<3){
                System.out.println("u got through 2nd step" + LinkedVertices.get(y).size());
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

    void calculateSavingsDebug(Vertex i, Vertex j, float savings){
        System.out.println("Calc savings: Vertex pair: " + i.toString() + " " + j.toString());
        System.out.println("Vertex position: " + i.position.toString() + j.position.toString());
        System.out.println("savings: " + savings);
        //Should we discard 0 savings-pairs?
        if (savings == 0) System.out.println("Error.");
    }

    class Sij {
        float s;
        Vertex i;
        Vertex j;
        ArrayList<Float> savingsMatrix;

        Sij(float s,Vertex i,Vertex j){
            this.s = s;
            this.i = i;
            this.j = j;
            this.savingsMatrix = new ArrayList<Float>();
            savingsMatrix.add(s);
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
