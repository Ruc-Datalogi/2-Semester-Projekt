
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Collections;


public class CW {
    //Getters and setters
    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(final ArrayList<Route> routes) {
        this.routes = routes;
    }

    public ArrayList<Route> getSavingsList() {
        return savingsList;
    }

    public void setSavingsList(final ArrayList<Route> savingsList) {
        this.savingsList = savingsList;
    }

    public ArrayList<Vertex> getVertexArrayList() {
        return vertexArrayList;
    }

    public void setVertexArrayList(final ArrayList<Vertex> vertexArrayList) {
        this.vertexArrayList = vertexArrayList;
    }

    public GfxComponent getGfxComponent() {
        return gfxComponent;
    }

    public void setGfxComponent(final GfxComponent gfxComponent) {
        this.gfxComponent = gfxComponent;
    }

    public PApplet getDaddy() {
        return daddy;
    }

    public void setDaddy(final PApplet daddy) {
        this.daddy = daddy;
    }

    public Vertex getDepot() {
        return depot;
    }

    public void setDepot(final Vertex depot) {
        this.depot = depot;
    }

    public static int getVehicleAmount() {
        return VEHICLEAMOUNT;
    }

    public static int getRouteCapacity() {
        return ROUTECAPACITY;
    }

    public int getK() {
        return k;
    }

    public void setK(final int k) {
        this.k = k;
    }

    private ArrayList<Route> routes;
    private ArrayList<Route> savingsList;
    private ArrayList<Vertex> vertexArrayList;
    private GfxComponent gfxComponent;
    private PApplet daddy;
    private Vertex depot;

    static final int VEHICLEAMOUNT = 25;
    static final int ROUTECAPACITY = 100;

    /**
     * Initialization of the clarke-wright algorithm
     * @param vertexArrayList solomon data
     * @param parent          for drawing
     */
    CW(final ArrayList<Vertex> vertexArrayList, final PApplet parent, final ArrayList<Vehicle> vehicleArrayList) {
        this.vertexArrayList = vertexArrayList;
        this.daddy = parent;
        this.depot = vertexArrayList.get(0);
        this.gfxComponent = new GfxComponent(parent.width, parent.height, vertexArrayList, vehicleArrayList, daddy);

        initRoute();
        calculateSavings();
        scanner();

        //results
        float totalLength = 0;
        int numRoutes=0;
        for (Route route : routes) {
            if(route.getAssignedVertices().size()>0) {
                totalLength += route.getLength();
                numRoutes++;
            }
            //System.out.println(route.getAssignedVertices());

        }

        System.out.println("C&W total length: " + totalLength + " with " + numRoutes + " routes.");
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
            Route route = new Route();
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
                    float costj = depot.position.dist(j.position);
                    float costi = depot.position.dist(i.position);
                    float costij = i.position.dist(j.position);
                    float savings = costi + costj - costij;

                    Route tempRoute = new Route();
                    tempRoute.addVertex(i);
                    tempRoute.addVertex(j);
                    tempRoute.setSavings(savings);
                    savingsList.add(tempRoute);
                }
            }
        }
        Collections.sort(savingsList);
    }

     /**
     * 3rd step of the algorithm
     */
    void scanner() {
        //for each savings pair in the sorted arraylist, get vertex i and j
        for (Route route : savingsList) {
            Vertex i = route.getAssignedVertices().get(0);
            Vertex j = route.getAssignedVertices().get(route.getAssignedVertices().size() - 1);

            //get which route i, and j are in.
            for (Route routeI : routes) {
                for (Route routeJ : routes) {
                    if (!(routeI == routeJ) && routeI.getAssignedVertices().contains(i) && routeJ.getAssignedVertices().contains(j)) {
                        //check if i and j are in the same route

                        //check if i and j are edge vertices
                        //i first in order j last

                        int indexI = routeI.getAssignedVertices().indexOf(i);
                        int indexJ = routeJ.getAssignedVertices().indexOf(j);
                        merge(routeI, routeJ, indexI, indexJ);

                        //j first, i last
                        // merge(routeJ, routeI, indexJ, indexI);

                        //TODO break the loop
                    }
                }
            }
        }
    }

    void merge(final Route route1, final Route route2, final int index1, final int index2) {
        if (index1 == 1 && index2 == route2.getAssignedVertices().size() - 1) {
            ArrayList<Vertex> tempRouteI = route1.getAssignedVertices();
            if (route2.getCost() + route1.getCost() < ROUTECAPACITY) {
                tempRouteI.remove(depot);
                route2.addAllVertices(tempRouteI);
                route2.getAssignedVertices().addAll(tempRouteI);
                routes.get(routes.indexOf(route1)).setAssignedVertices(new ArrayList<>());
            }
        }
    }

    private int k = 0;

    void timeWindowScanner(ArrayList<Route> routes){
        for (Route route : routes) {

        }

    }
    void stepScanner() {
        Route sij = savingsList.get(getK());
        Vertex i = sij.getAssignedVertices().get(0);
        Vertex j = sij.getAssignedVertices().get(sij.getAssignedVertices().size() - 1);

        //get which route i, and j are in.
        for (Route routeI : routes) {
            for (Route routeJ : routes) {
                if (!(routeI == routeJ) && routeI.getAssignedVertices().contains(i) && routeJ.getAssignedVertices().contains(j)) {
                    //check if i and j are in the same route

                    //check if i and j are edge vertices
                    //i first in order j last

                    int indexI = routeI.getAssignedVertices().indexOf(i);
                    int indexJ = routeJ.getAssignedVertices().indexOf(j);
                    merge(routeI, routeJ, indexI, indexJ);

                    //j first, i last
                    merge(routeJ, routeI, indexJ, indexI);
                }
            }
        }
        setK((k + 1) % savingsList.size());
    }
}
