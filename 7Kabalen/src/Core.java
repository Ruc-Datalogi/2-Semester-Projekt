import processing.core.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Core extends PApplet {
    /*
    Core should initialize and manage the program
     */
    public static void main(String[] args) {
        PApplet.main("Core");
        //TestLoader test = new TestLoader();
    }

    public void settings() {
        size(1200, 720);
    }

    ArrayList<Vertex> vertexArrayList;

    ArrayList<Vehicle> vehicleList;

    CW cw;
    gfxComponent gfxComponent;

    public void setup() {
        vehicleList = new ArrayList<>();
        surface.setResizable(true);

        //setup of the data handler and generation of the solomon data.
        DataImporter datagirl = new DataImporter("SOLOMON2.csv"); //change what data you want to look at here.
        datagirl.setParent(this);

        try {
            vertexArrayList = datagirl.generateVertice();
        } catch (Exception e) {
            System.out.println("The data generation broke");
        }

        System.out.println("Size of your data set: " + datagirl.generateVertice().size());
        System.out.println(datagirl.vertexArrayList);

        for (int i = 0; i < datagirl.vertexArrayList.size(); i++) {
            vertexArrayList.get(i).setParent(this);
        }

        //The below two for loops is for a simple solution assigning a vehicle to each vertex.
        /*for (int i = 0; i<datagirl.vertexArrayList.size();i++){
            Vehicle simpleVehicle = new Vehicle(i); //Just assigning the vehicle the id of the vertex for now, should be unique in the future
            simpleVehicle.addAssignment(vertexArrayList.get(i)); //adds the vertex to the vehicles list of assignments
            vehicleList.add(simpleVehicle); //adds the vehicle to our list of vehicles in core.
        }

        for (int i=0; i<vehicleList.size();i++){
            System.out.println(vehicleList.get(i).toString());
        }*/
        //Let's try and give each car 3 assignments, still in order of the Vertex array though
        for (int i = 0; i < Math.floor(datagirl.vertexArrayList.size() / 10); i++) {
            Vehicle simpleVehicle = new Vehicle(i); //Just assigning the vehicle the id of the vertex for now, should be unique in the future
            for (int j = 1; j < 11; j++) {
                simpleVehicle.addAssignment(vertexArrayList.get((i) * 10 + j)); //adds the vertex to the vehicles list of assignments

            }
            vehicleList.add(simpleVehicle); //adds the vehicle to our list of vehicles in core.
            //System.out.println("Vehicle[" + i + "]" + " Size: " + vehicleList.size());
        }

        for (int i = 0; i < vehicleList.size(); i++) {
            //System.out.println(vehicleList.get(i).toString());
        }
        //System.out.println("Vehicles: " + vehicleList.size());
        gfxComponent = new gfxComponent(this.width,this.height,vertexArrayList,vehicleList,this);
        cw = new CW(vertexArrayList, this);
    }



    public void draw() {
        background(0);
        fill(255);
        text(frameRate, 20, 20); //this is the frameRate counter
        gfxComponent.drawCustomers();
        //display every vertex in the arraylist
        //drawVehicleRoutes();
        cw.displayRoute();

    }

    public void frameResized(int w, int h) {
        //Need to implement resizing
        height = h;
        width = w;
    }

    public void keyPressed() {
        if ((key >= 'A' && key <= 'Z') || (key >= 'a' && key <= 'z')) {
            cw.scanner();
        }
    }
}
