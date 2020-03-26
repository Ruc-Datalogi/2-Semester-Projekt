import processing.core.PApplet;

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

    void drawVehicleRoutes() {
        for (int i = 0; i < vehicleList.size(); i++) {
            parent.stroke(255, 200, 200);
            //First visual line is between depot and the first assignment
            Vertex depot = vertexArrayList.get(0);
            if (depot != null) {

                parent.line(getDrawWidth(depot.position.x), getDrawHeight(depot.position.x), getDrawWidth(vehicleList.get(i).assignedRouted.get(0).position.x), getDrawHeight(vehicleList.get(i).assignedRouted.get(0).position.y));
                int tempColour = (i + 1) * 255 / vehicleList.size();
                parent.stroke(tempColour, tempColour, 255);

                for (int j = 1; j < vehicleList.get(i).assignedRouted.size(); j++) {
                    Vertex previousAssignment = vehicleList.get(i).assignedRouted.get(j - 1);
                    Vertex assignment = vehicleList.get(i).assignedRouted.get(j);

                    drawBetweenTwoVertices(previousAssignment, assignment);


                }
                //Last line is between last assignment and depot
                parent.line(getDrawWidth(depot.position.x), getDrawHeight(depot.position.y), getDrawWidth(vehicleList.get(i).assignedRouted.get(vehicleList.get(i).assignedRouted.size() - 1).position.x), getDrawHeight(vehicleList.get(i).assignedRouted.get(vehicleList.get(i).assignedRouted.size() - 1).position.y));

                //line(vertexArrayList.get(0).position.x*10,vertexArrayList.get(0).position.y*10,vehicleList.get(i).assignedRouted.get(0).position.x*10,vehicleList.get(i).assignedRouted.get(0).position.y*10);
                //WHY DO WE MULTIPLY BY 10 RANDOMLY???
            }
        }
    }

}
