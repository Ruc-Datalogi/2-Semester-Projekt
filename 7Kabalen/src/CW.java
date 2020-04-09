import processing.core.*;

import java.lang.reflect.Array;
import java.sql.SQLOutput;
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
        //scanner();
    }

    /**
     * Initialize the CW route
     * 1st step of the algorithm
     */
    void initRoute() {
        //init data lists
        routes       = new ArrayList<>();
        savingsList  = new ArrayList<>();
        depot.isEdge = false;

        //make a new route for every vertex in the vertexArrayList
        for (Vertex vertex : vertexArrayList) {
            Route route   = new Route();
            vertex.isEdge = true;
            route.addVertex(depot);
            route.addVertex(vertex);
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
                if(!(i==j) && !i.isDepot() && !j.isDepot()){
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
            int sizeAssignedVertices = route.assignedVertices.size()-1;

            Vertex vertexI = route.assignedVertices.get(0);
            Vertex vertexJ = route.assignedVertices.get(sizeAssignedVertices);

            //if conditional for checken edge case in vertex class.

            //check if vertex is 0,i or j,0
            if (vertexI.isEdge && vertexJ.isEdge){

                //scan through all routes
                for(Route route2 : routes){

                    //if the route contains the vertexI
                    //then add J
                    if (route2.assignedVertices.contains(vertexI)){
                        route2.assignedVertices.add(vertexJ);

                        //if the route is now larger than 3 after you've added J, set the previous
                        //vertex in the arrayList to no longer be an edge vertex.
                        if (route2.assignedVertices.size()>3) {
                            route2.assignedVertices.get(route2.assignedVertices.indexOf(vertexI)).isEdge = false;
                        }

                    } else route2.assignedVertices.remove(vertexJ);
                }
            }
        }
    }

    int i = 0;
    void stepScanner() {
        Route route = savingsList.get(i);
        int sizeAssignedVertices = route.assignedVertices.size()-1;

        Vertex vertexI = route.assignedVertices.get(0);
        Vertex vertexJ = route.assignedVertices.get(sizeAssignedVertices);
        //if conditional for checken edge case in vertex class.

        //check if vertex is 0,i or j,0

        System.out.println("Route " + route);
        System.out.println("Route size" + route.assignedVertices.size());
        System.out.println("Vertices: " + vertexI + " , " + vertexJ);

        if ((vertexI != vertexJ) && vertexI.isEdge && vertexJ.isEdge){
            System.out.println("passed this step you nignognigger");

            //scan through all routes
            for(Route route2 : routes){
                Route caseRoute = route2;

                System.out.println("Route 2 size: " + route2.assignedVertices.size());
                System.out.println("Route 2: " + route2.assignedVertices);

                //if the route contains the vertexI
                //then add J
                if (route2.assignedVertices.contains(vertexI)){

                    System.out.println("Vertex I, route 2: " + vertexI);
                    if(!(route2.assignedVertices.contains(vertexI)&&route2.assignedVertices.contains(vertexJ))){
                        System.out.println("Vertex J, route 2: " + vertexJ);

                        System.out.println("you passed this tep you abolsute faucking fag");
                        caseRoute.assignedVertices.add(vertexJ);

                        //if the route is now larger than 3 after you've added J, set the previous
                        //vertex in the arrayList to no longer be an edge vertex.
                        if (route2.assignedVertices.size()>3) {
                            System.out.println("you passed dis mon");

                            route2.assignedVertices.get(route2.assignedVertices.indexOf(vertexI)).isEdge = false;
                            System.out.println("Looking at this route " + route2.assignedVertices + " , " + route2.assignedVertices.get(route2.assignedVertices.indexOf(vertexI)) + " was assinged false");
                            for (Route route3 : routes){
                                if (route3.assignedVertices.contains(vertexJ)){
                                    route3.assignedVertices.remove(vertexJ);
                                }
                            }
                            routes.set(routes.indexOf(route2), caseRoute);
                        }
                    }
                }
            }
        }
        i = (i+1)%savingsList.size();
    }
}
