
import processing.core.PApplet;
import java.util.ArrayList;
import java.util.Comparator;

public class Core extends PApplet {
    /*
    Core should initialize and manage the program
     */

    public static void main(final String[] args) {
        PApplet.main("Core");
    }

    public void settings() {
        size(1200, 720);
    }

    private ArrayList<Vertex> vertexArrayList;
    private ArrayList<Vehicle> vehicleList;
    KMeans KMeans;
    ArrayList<Route> bestKMeansRoute;
    private CW cw;
    private GfxComponent gfxComponent;

    //Getters and setters
    public ArrayList<Vertex> getVertexArrayList() {
        return vertexArrayList;
    }

    public void setVertexArrayList(final ArrayList<Vertex> vertexArrayList) {
        this.vertexArrayList = vertexArrayList;
    }

    public ArrayList<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(final ArrayList<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public CW getCw() {
        return cw;
    }

    public void setCw(final CW cw) {
        this.cw = cw;
    }

    public GfxComponent getGfxComponent() {
        return gfxComponent;
    }

    public void setGfxComponent(final GfxComponent gfxComponent) {
        this.gfxComponent = gfxComponent;
    }

    public float getTimeToInit() {
        return timeToInit;
    }

    public void setTimeToInit(final float timeToInit) {
        this.timeToInit = timeToInit;
    }

    public boolean isRun() {
        return run;
    }

    public void setRun(final boolean run) {
        this.run = run;
    }

    public void setup() {
        final float initClock2 = System.nanoTime();
        vehicleList = new ArrayList<>();
        surface.setResizable(true);

        //setup of the data handler and generation of the solomon data.
        DataImporter datagirl = new DataImporter("CVRP-A-N80-k-10.csv",this); //change what data you want to look at here.

        try {
            vertexArrayList = datagirl.generateVertice();
        } catch (Exception e) {
            System.out.println("The data generation broke");
        }

        gfxComponent = new GfxComponent(this.width, this.height, vertexArrayList, vehicleList, this);
        final float initClockCW1 = System.nanoTime();
        cw = new CW(vertexArrayList, this, vehicleList);
        final float initClockCW2 = System.nanoTime();
        System.out.println("CW time: " + (initClockCW2 - initClockCW1) * Math.pow(10, -9) );

        float shortestDist=100000;
        float longestDist=0;
        ArrayList<Vertex> sortedVertexList = (ArrayList<Vertex>) vertexArrayList.clone();
        sortedVertexList.remove(0); //removing depot from list and re-adding it later
        Comparator<Vertex> vertexComparator=Comparator.comparing(e -> e.d); //Comparing demand of each vertex
        sortedVertexList.sort(vertexComparator.reversed()); // sorting based on demand in reverse order so highest demand is first in list
        sortedVertexList.add(0,vertexArrayList.get(0)); // re-adding depot as first index in list.
        ArrayList<Double> allValidRouteLengths = new ArrayList<Double>();
        final float initClockKM1 = System.nanoTime();
        for(int i=0;i<50000;i++){
            KMeans = new KMeans(sortedVertexList, 10, vertexArrayList, this, vehicleList);


            if(KMeans.isValidSolution()) {

                float RouteLength=KMeans.getTotalRouteLength();
                allValidRouteLengths.add((double) RouteLength);
                //System.out.println("KMeans length: " + RouteLength);
                if(longestDist<RouteLength){
                    longestDist=RouteLength;
                }
                if(shortestDist>RouteLength){
                    bestKMeansRoute= (ArrayList<Route>) KMeans.TwoOptedRoutes.clone();
                    shortestDist=RouteLength;

                }
            }

        }
        final float initClockKM2 = System.nanoTime();
        System.out.println("KM time " + (initClockKM2 - initClockKM1)*1/50000 * Math.pow(10, -9) );
        double[] allValid = new double[allValidRouteLengths.size()];
        for (int i =0; i < allValidRouteLengths.size(); i++) {
            allValid[i] = allValidRouteLengths.get(i);
        }
        double[] stdData = calculateStandardVariation(allValid);
        System.out.println("Std: " + stdData[0] + " mean:" + stdData[1] + "all valid: "  + allValidRouteLengths.size());
        System.out.println("Best Dist: " + shortestDist + ". Worst Dist: " + longestDist);
        final float initClock;
        initClock = System.nanoTime();
        timeToInit = (float) ((initClock - initClock2) * Math.pow(10, -9));
    }
    double[] calculateStandardVariation(double numArray[]){
        double sum = 0.0, standardDeviation = 0.0;
        int length = numArray.length;

        for(double num : numArray) {
            sum += num;
        }

        double mean = sum/length;

        for(double num: numArray) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return new double[] {Math.sqrt(standardDeviation/length),mean};
    }

    float timeToInit;

    public void draw() {
        background(0);

        fill(0, 255, 0);
        //text(frameRate, 20, 20);
        //text("Time to Initialisation: " + timeToInit, 20, 35);

        cw.getGfxComponent().drawCustomers();
        //cw.getGfxComponent().drawRoutes(cw.getRoutes());
        //KMeans.gfxComponent.drawCentroids(KMeans.Centroids);
        KMeans.gfxComponent.drawRoutes(bestKMeansRoute);
        if (run) {
            cw.stepScanner();
        }
    }

    //Controls
    private boolean run = false;

    public void keyPressed() {
        if ((key >= 'A' && key <= 'Z') || (key >= 'a' && key <= 'z')) {
            cw.stepScanner();
        } else if ((keyCode == BACKSPACE)) {
            run = !run;
        }
    }
}
