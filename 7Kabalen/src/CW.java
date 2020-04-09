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
        this.daddy = parent;
        this.depot = vertexArrayList.get(0);
        this.gfxComponent = new gfxComponent(parent.width, parent.height, vertexArrayList, vehicleArrayList, daddy);
        LinkedVertices = new ArrayList<Vertex>();

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
        routes = new ArrayList<>();
        savingsList = new ArrayList<>();
        depot.isEdge = false;

        //make a new route for every vertex in the vertexArrayList
        for (Vertex vertex : vertexArrayList) {
            Route route = new Route();
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
                if (!(i == j) && !i.isDepot() && !j.isDepot()) {
                    float costj   = depot.position.dist(j.position);
                    float costi   = depot.position.dist(i.position);
                    float costij  = i.position.dist(j.position);
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
        for (Route route : savingsList) {
            Route sij = route;
            Vertex i = sij.assignedVertices.get(0);
            Vertex j = sij.assignedVertices.get(sij.assignedVertices.size() - 1);

            //get which route i, and j are in.
            for (Route routeI : routes) {
                for (Route routeJ : routes) {
                    if (routeI.assignedVertices.contains(i) && routeJ.assignedVertices.contains(j)) {
                        //check if i and j are in the same route
                        if (!(routeI == routeJ)) {
                            //check if i and j are edge vertices

                            //i first in order j last
                            if (routeI.assignedVertices.indexOf(i) == 1 && routeJ.assignedVertices.indexOf(j) == routeJ.assignedVertices.size() - 1) {
                                ArrayList<Vertex> tempRouteI = routeI.assignedVertices;
                                System.out.println("Hey hey this bis beroe adding " + routeJ.assignedVertices);
                                tempRouteI.remove(depot);
                                routeJ.assignedVertices.addAll(tempRouteI);
                                System.out.println("this is after adding " + routeJ.assignedVertices);
                                routes.get(routes.indexOf(routeI)).assignedVertices = new ArrayList<Vertex>();

                                //j first, i last
                            } else if (routeJ.assignedVertices.indexOf(j) == 1 && routeI.assignedVertices.indexOf(i) == routeI.assignedVertices.size() - 1) {
                                ArrayList<Vertex> tempRouteJ = routeJ.assignedVertices;
                                System.out.println("Hey hey this bis beroe adding " + routeI.assignedVertices);
                                tempRouteJ.remove(depot);
                                routeI.assignedVertices.addAll(tempRouteJ);
                                System.out.println("this is after adding " + routeI.assignedVertices);
                                routes.get(routes.indexOf(routeJ)).assignedVertices = new ArrayList<>();
                            }
                        }
                    }
                }
            }
        }
    }

    int k = 0;

    void stepScanner() {
        Route sij = savingsList.get(k);
        Vertex i = sij.assignedVertices.get(0);
        Vertex j = sij.assignedVertices.get(sij.assignedVertices.size() - 1);

        //get which route i, and j are in.
        for (Route routeI : routes) {
            for (Route routeJ : routes) {
                if (routeI.assignedVertices.contains(i) && routeJ.assignedVertices.contains(j)) {
                    //check if i and j are in the same route
                    if (!(routeI == routeJ)) {
                        //check if i and j are edge vertices

                        //i first in order j last
                        if (routeI.assignedVertices.indexOf(i) == 1 && routeJ.assignedVertices.indexOf(j) == routeJ.assignedVertices.size() - 1) {
                            ArrayList<Vertex> tempRouteI = routeI.assignedVertices;
                            System.out.println("Hey hey this bis beroe adding " + routeJ.assignedVertices);
                            tempRouteI.remove(depot);
                            routeJ.assignedVertices.addAll(tempRouteI);
                            System.out.println("this is after adding " + routeJ.assignedVertices);
                            routes.get(routes.indexOf(routeI)).assignedVertices = new ArrayList<Vertex>();

                            //j first, i last
                        } else if (routeJ.assignedVertices.indexOf(j) == 1 && routeI.assignedVertices.indexOf(i) == routeI.assignedVertices.size() - 1) {
                            ArrayList<Vertex> tempRouteJ = routeJ.assignedVertices;
                            System.out.println("Hey hey this bis beroe adding " + routeI.assignedVertices);
                            tempRouteJ.remove(depot);
                            routeI.assignedVertices.addAll(tempRouteJ);
                            System.out.println("this is after adding " + routeI.assignedVertices);
                            routes.get(routes.indexOf(routeJ)).assignedVertices = new ArrayList<>();
                        }
                    }
                }
            }
        }
        k = (k + 1) % savingsList.size();
    }
}
