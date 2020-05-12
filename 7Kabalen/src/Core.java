
import processing.core.PApplet;
import java.util.ArrayList;

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
        DataImporter datagirl = new DataImporter("SOLOMON2.csv",this); //change what data you want to look at here.

        try {
            vertexArrayList = datagirl.generateVertice();
        } catch (Exception e) {
            System.out.println("The data generation broke");
        }

        gfxComponent = new GfxComponent(this.width, this.height, vertexArrayList, vehicleList, this);
        cw = new CW(vertexArrayList, this, vehicleList);
        KMeans = new KMeans(vertexArrayList, 8, vertexArrayList, this, vehicleList);

        final float initClock;
        initClock = System.nanoTime();
        timeToInit = (float) ((initClock - initClock2) * Math.pow(10, -9));
    }

    float timeToInit;

    public void draw() {
        background(0);

        fill(0, 255, 0);
        text(frameRate, 20, 20);
        text("Time to Initialisation: " + timeToInit, 20, 35);

        cw.getGfxComponent().drawCustomers();
        //cw.getGfxComponent().drawRoutes(cw.getRoutes());
        //KMeans.gfxComponent.drawCentroids(KMeans.Centroids);
        KMeans.gfxComponent.drawRoutes(KMeans.TwoOptedRoutes);
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
