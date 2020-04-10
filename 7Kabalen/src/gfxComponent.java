import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class gfxComponent {
    int displayWidth, displayHeight;
    ArrayList<Vertex> vertexArrayList;
    ArrayList<Vehicle> vehicleList;

    PApplet parent;

    gfxComponent(int width, int height, ArrayList<Vertex> vertexArrayList, ArrayList<Vehicle> vehicleList, PApplet parent){
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
        return x * parent.width / 80; //Should be 100 but current data doesn't exceed 77
    }

    public int getDrawHeight(int x) {
        if (parent.height == 0) {
            parent.height = displayHeight;
        }
        return x * parent.height / 80;//Should be 100 but current data doesn't exceed 77

    }

    public void drawBetweenTwoVertices(Vertex a, Vertex b) {
        parent.line(getDrawWidth(a.position.x), getDrawHeight(a.position.y), getDrawWidth(b.position.x), getDrawHeight(b.position.y));
    }

    void drawCustomers() {
        //Let's handle the drawing here instead of passing around the parent?
        for (Vertex custom : vertexArrayList) {
            if (custom.isDepot()) {
                parent.fill(255, 0, 0);
                parent.text("pos x " + custom.position.x + " " + "pos y " + custom.position.y, getDrawWidth(custom.position.x) - 20, getDrawHeight((custom.position.y) - 5));
            } else {
                parent.fill(0, 255, 0);
                parent.text("pos x " + custom.position.x + " " + "pos y " + custom.position.y, getDrawWidth(custom.position.x) - 20, getDrawHeight(custom.position.y) - 5);
            }
            parent.fill(255);
            parent.ellipse(getDrawWidth((int) custom.position.x), getDrawHeight((int) custom.position.y), 8, 8); //the multiplier basically just spreads them out
            //Please figure out a way to translate the coordinates from the data to the visuals without multiplying by arbitrary values.
        }
    }

    void drawRoutes(ArrayList<Route> routes){
        for(Route route : routes){
            for(int i = 0; i < route.assignedVertices.size()-1; i++) {
                parent.stroke(0, 255, 0);
                drawBetweenTwoVertices(route.assignedVertices.get(i), route.assignedVertices.get(i + 1));
                parent.stroke(255,0,0);
                drawBetweenTwoVertices(route.assignedVertices.get(route.assignedVertices.size() - 1), route.assignedVertices.get(0));
                parent.noStroke();
            }
        }
    }
}
