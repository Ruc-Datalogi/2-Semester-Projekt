//import javafx.scene.shape.Ellipse;
import processing.core.PApplet;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GfxComponent {

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayWidth(int displayWidth) {
        this.displayWidth = displayWidth;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }

    public void setDisplayHeight(int displayHeight) {
        this.displayHeight = displayHeight;
    }

    public ArrayList<Vertex> getVertexArrayList() {
        return vertexArrayList;
    }

    public void setVertexArrayList(ArrayList<Vertex> vertexArrayList) {
        this.vertexArrayList = vertexArrayList;
    }

    public ArrayList<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(ArrayList<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public PApplet getParent() {
        return parent;
    }

    public void setParent(PApplet parent) {
        this.parent = parent;
    }

    public dumbColour[] getTheseColours() {
        return TheseColours;
    }

    public void setTheseColours(dumbColour[] theseColours) {
        TheseColours = theseColours;
    }

    private int displayWidth, displayHeight;
    private ArrayList<Vertex> vertexArrayList;
    private ArrayList<Vehicle> vehicleList;
    private PApplet parent;

    static class dumbColour{
        int r,g,b;
        dumbColour(int r,int g, int b){
            this.r = r;
            this.g = g;
            this.b = b;
        }
    }

    GfxComponent(int width, int height, ArrayList<Vertex> vertexArrayList, ArrayList<Vehicle> vehicleList, PApplet parent){
        this.parent = parent;
        this.vertexArrayList = vertexArrayList;
        this.vehicleList = vehicleList;
    }

    public int getDrawWidth(float x) {
        return getDrawWidth((int) x);
    }

    public int getDrawHeight(float x) {
        return getDrawHeight((int) x);
    }

    public int getDrawWidth(int x) {
        if (parent.width == 0) {
            parent.width = displayWidth;
        }
        return x * parent.width / 100; //Should be 100 but current data doesn't exceed 77
    }

    public int getDrawHeight(int x) {
        if (parent.height == 0) {
            parent.height = displayHeight;
        }
        return x * parent.height / 100;//Should be 100 but current data doesn't exceed 77

    }

    public void drawBetweenTwoVertices(Vertex a, Vertex b) {
        parent.line(
                getDrawWidth(a.position.x), getDrawHeight(a.position.y),
                getDrawWidth(b.position.x), getDrawHeight(b.position.y)
        );
    }

    void drawCustomers() {
        //Let's handle the drawing here instead of passing around the parent?
        for (Vertex custom : vertexArrayList) {
            /*if (custom.isDepot()) {
                parent.fill(255, 0, 0);
                parent.text("pos x " + custom.position.x + " " + "pos y " + custom.position.y, getDrawWidth(custom.position.x) - 20, getDrawHeight((custom.position.y) - 5));
            } else {
                parent.fill(0, 255, 0);
                parent.text("pos x " + custom.position.x + " " + "pos y " + custom.position.y, getDrawWidth(custom.position.x) - 20, getDrawHeight(custom.position.y) - 5);
            }*/
            parent.fill(255);
            parent.ellipse(getDrawWidth((int) custom.position.x), getDrawHeight((int) custom.position.y), 8, 8); //the multiplier basically just spreads them out
            //Please figure out a way to translate the coordinates from the data to the visuals without multiplying by arbitrary values.
        }
    }

    void drawRoutes(ArrayList<Route> routes){
        int dumbNumber=0;
        //System.out.println("route: "+  routes);
        for(Route route : routes){
            dumbColour myUniqueSnowflakeColour = TheseColours[dumbNumber];
            parent.stroke(myUniqueSnowflakeColour.r,myUniqueSnowflakeColour.g,myUniqueSnowflakeColour.b);
            for(int i = 0; i < route.getAssignedVertices().size()-1; i++) {

                //parent.stroke(0, 255, 0);
                drawBetweenTwoVertices(route.getAssignedVertices().get(i), route.getAssignedVertices().get(i + 1));
                //parent.stroke(255,0,0);
                drawBetweenTwoVertices(route.getAssignedVertices().get(route.getAssignedVertices().size() - 1), route.getAssignedVertices().get(0));
                //parent.noStroke();
            }
            dumbNumber++;
        }
    }

    void drawCentroids(ArrayList<Centroid> centroids){
        for (Centroid centroid : centroids){
            parent.fill(255,0,0);
            parent.ellipse(getDrawWidth((int)(centroid.position.x)), getDrawHeight((int)(centroid.position.y)), 16,16);
            parent.text("pos x " + centroid.position.x + " " + "pos y " + centroid.position.y + "C: " + centroid.remainingCapacity, getDrawWidth(centroid.position.x) - 20, getDrawHeight((centroid.position.y) - 5));

            for (Vertex vertex : centroid.Cluster) {
                parent.stroke(255,0,0);
                parent.line(
                        getDrawWidth((int)(centroid.position.x)),
                        getDrawHeight((int)(centroid.position.y)),
                        getDrawWidth((int)(vertex.position.x)),
                        getDrawHeight((int)(vertex.position.y))
                );
            }
        }
    }

    //preset colours
    dumbColour[] TheseColours = {
            new dumbColour(247, 67, 67),
            new dumbColour(130, 94, 35),
            new dumbColour(27, 130, 9),
            new dumbColour(35, 122, 130),
            new dumbColour(61, 82, 130),
            new dumbColour(239, 116, 247),
            new dumbColour(247, 116, 175),
            new dumbColour(130, 35, 35),
            new dumbColour(194, 247, 17),
            new dumbColour(136, 247, 116),
            new dumbColour(116, 236, 247),
            new dumbColour(47, 9, 130),
            new dumbColour(125, 61, 130),
            new dumbColour(247, 178, 67),
            new dumbColour(114, 130, 61),
            new dumbColour(61, 130, 93),
            new dumbColour(116, 156, 247),
            new dumbColour(124, 67, 247),
            new dumbColour(130, 9, 64),
            new dumbColour(247, 67, 67),
            new dumbColour(130, 94, 35),
            new dumbColour(27, 130, 9),
            new dumbColour(35, 122, 130),
            new dumbColour(61, 82, 130),
            new dumbColour(239, 116, 247),
            new dumbColour(247, 116, 175),
            new dumbColour(130, 35, 35),
            new dumbColour(194, 247, 17),
            new dumbColour(136, 247, 116),
            new dumbColour(116, 236, 247),
            new dumbColour(47, 9, 130),
            new dumbColour(125, 61, 130),
            new dumbColour(247, 178, 67),
            new dumbColour(114, 130, 61),
            new dumbColour(61, 130, 93),
            new dumbColour(116, 156, 247),
            new dumbColour(124, 67, 247),
            new dumbColour(130, 94, 35),
            new dumbColour(27, 130, 9),
            new dumbColour(35, 122, 130),
            new dumbColour(61, 82, 130),
            new dumbColour(239, 116, 247),
            new dumbColour(247, 116, 175),
            new dumbColour(130, 35, 35),
            new dumbColour(194, 247, 17),
            new dumbColour(136, 247, 116),
            new dumbColour(116, 236, 247),
            new dumbColour(47, 9, 130),
            new dumbColour(125, 61, 130),
            new dumbColour(247, 178, 67),
            new dumbColour(114, 130, 61),
            new dumbColour(61, 130, 93),
            new dumbColour(116, 156, 247),
            new dumbColour(124, 67, 247),
            new dumbColour(130, 94, 35),
            new dumbColour(27, 130, 9),
            new dumbColour(35, 122, 130),
            new dumbColour(61, 82, 130),
            new dumbColour(239, 116, 247),
            new dumbColour(247, 116, 175),
            new dumbColour(130, 35, 35),
            new dumbColour(194, 247, 17),
            new dumbColour(136, 247, 116),
            new dumbColour(116, 236, 247),
            new dumbColour(47, 9, 130),
            new dumbColour(125, 61, 130),
            new dumbColour(247, 178, 67),
            new dumbColour(114, 130, 61),
            new dumbColour(61, 130, 93),
            new dumbColour(116, 156, 247),
            new dumbColour(124, 67, 247),
            new dumbColour(130, 94, 35),
            new dumbColour(27, 130, 9),
            new dumbColour(35, 122, 130),
            new dumbColour(61, 82, 130),
            new dumbColour(239, 116, 247),
            new dumbColour(247, 116, 175),
            new dumbColour(130, 35, 35),
            new dumbColour(194, 247, 17),
            new dumbColour(136, 247, 116),
            new dumbColour(116, 236, 247),
            new dumbColour(47, 9, 130),
            new dumbColour(125, 61, 130),
            new dumbColour(247, 178, 67),
            new dumbColour(114, 130, 61),
            new dumbColour(61, 130, 93),
            new dumbColour(116, 156, 247),
            new dumbColour(124, 67, 247),
            new dumbColour(130, 94, 35),
            new dumbColour(27, 130, 9),
            new dumbColour(35, 122, 130),
            new dumbColour(61, 82, 130),
            new dumbColour(239, 116, 247),
            new dumbColour(247, 116, 175),
            new dumbColour(130, 35, 35),
            new dumbColour(194, 247, 17),
            new dumbColour(136, 247, 116),
            new dumbColour(116, 236, 247),
            new dumbColour(47, 9, 130),
            new dumbColour(125, 61, 130),
            new dumbColour(247, 178, 67),
    };
}
