import processing.core.*;
import java.util.*;


public class CW {
    ArrayList<Route> routes;
    ArrayList<Route> savingsList;
    ArrayList<Vertex> LinkedVertices;
    ArrayList<Vertex> vertexArrayList;
    gfxComponent gfxComponent;
    PApplet daddy;
    Vertex depot;

    /**
     * Initialization of the clarke-wright algorithm
     *
     * @param vertexArrayList
     * @param parent
     */
    CW(ArrayList<Vertex> vertexArrayList, PApplet parent, ArrayList<Vehicle> vehicleArrayList) {
        this.vertexArrayList = vertexArrayList;
        this.daddy           = parent;
        this.depot           = vertexArrayList.get(0);
        this.gfxComponent    = new gfxComponent(parent.width, parent.height, vertexArrayList, vehicleArrayList, daddy);
        LinkedVertices       = new ArrayList<Vertex>();

        initRoute();
        calculateSavings();
        scanner();
    }

    /**
     * Initialize the CW route
     * 1st step of the algorithm
     */
    void initRoute() {
        //init data lists
        routes = new ArrayList<>();
        savingsList = new ArrayList<>();

        //make a new route for every vertex in the vertexArrayList
        for (Vertex vertex : vertexArrayList) {
            Route route   = new Route();
            vertex.isEdge = true;
            route.addVertex(depot);
            route.addVertex(vertex);
            route.addVertex(depot);
            routes.add(route);
        }
    }

    /**
     * Caculates the cost, and subsequently savings between 3 vectors.
     * 2nd step of the algorithm
     */

    void calculateSavings() {
        //distance calculation
        for (Vertex i : vertexArrayList) {
            for (Vertex j : vertexArrayList) {
                float costj = depot.position.dist(j.position);
                float costi = depot.position.dist(i.position);
                float costij = i.position.dist(j.position);
                float savings = costi + costj - costij;

                Route tempRoute = new Route();
                tempRoute.addVertex(i);
                tempRoute.addVertex(j);
                tempRoute.savings = savings;
                savingsList.add(tempRoute);
            }
        }
        Collections.sort(savingsList);
        for (Route route : savingsList) {
            System.out.println(route.toString());
        }
    }

    /**
     * 3rd step of the algorithm
     */

    void scanner() {
        for (Route route : savingsList){

            Vertex vertexI = route.assignedVertices.get(0);
            Vertex vertexJ = route.assignedVertices.get(1);
            //if conditional for checken edge case in vertex class.
        }
    }
}
