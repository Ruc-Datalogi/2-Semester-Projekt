import processing.core.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Core extends PApplet{
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
        for (int i = 0; i<Math.floor(datagirl.vertexArrayList.size()/10);i++){
            Vehicle simpleVehicle = new Vehicle(i); //Just assigning the vehicle the id of the vertex for now, should be unique in the future
            for (int j=1;j<11;j++) {
                simpleVehicle.addAssignment(vertexArrayList.get((i)*10+j)); //adds the vertex to the vehicles list of assignments

            }
            vehicleList.add(simpleVehicle); //adds the vehicle to our list of vehicles in core.
            //System.out.println("Vehicle[" + i + "]" + " Size: " + vehicleList.size());
        }

        for (int i=0; i<vehicleList.size();i++){
            //System.out.println(vehicleList.get(i).toString());
        }
        //System.out.println("Vehicles: " + vehicleList.size());

    }
    public int getDrawWidth(float x){

        return getDrawWidth((int) x);
    }
    public int getDrawHeight(float x){
        return getDrawHeight((int) x);
    }
    public int getDrawWidth(int x){
        if (width==0){
            this.width=displayWidth;
        }
        return x*width/80; //Should be 100 but current data doesn't exceed 77
    }
    public int getDrawHeight(int x){
        if (height==0){
            this.height=displayHeight;
        }
        return x*height/80;//Should be 100 but current data doesn't exceed 77

    }

    void drawBetweenTwoVertices(Vertex a, Vertex b){
        line(getDrawWidth(a.position.x), getDrawHeight(a.position.y), getDrawWidth(b.position.x), getDrawHeight(b.position.y));

    }
    void drawCustomers(){
        //Let's handle the drawing here instead of passing around the parent?
        for (int i=0;i<vertexArrayList.size();i++) {
            Vertex custom = vertexArrayList.get(i);
            if (custom.isDepot()) {
                fill(255, 0, 0);
                text("pos x " + custom.position.x + " " + "pos y " + custom.position.y, getDrawWidth( custom.position.x) - 20, getDrawHeight(( custom.position.y) - 5));
            } else {
                fill(0, 255, 0);
                text("pos x " + custom.position.x + " " + "pos y " + custom.position.y, getDrawWidth( custom.position.x) - 20, getDrawHeight(custom.position.y) - 5);
            }


            fill(255);
            ellipse(getDrawWidth((int) custom.position.x), getDrawHeight((int) custom.position.y), 8, 8); //the multiplier basically just spreads them out
            //Please figure out a way to translate the coordinates from the data to the visuals without multiplying by arbitrary values.
        }

    }

    void drawVehicleRoutes(){
        for (int i=0;i<vehicleList.size();i++){
            stroke(255,200,200);
            //First visual line is between depot and the first assignment
            Vertex depot = vertexArrayList.get(0);
            if(depot!=null) {

                line(getDrawWidth(depot.position.x), getDrawHeight(depot.position.x), getDrawWidth(vehicleList.get(i).assignedRouted.get(0).position.x), getDrawHeight(vehicleList.get(i).assignedRouted.get(0).position.y));
                int tempColour = (i+1)*255/vehicleList.size();
                stroke(tempColour,tempColour,255);

                for (int j = 1; j < vehicleList.get(i).assignedRouted.size(); j++) {
                    Vertex previousAssignment=vehicleList.get(i).assignedRouted.get(j-1);
                    Vertex assignment = vehicleList.get(i).assignedRouted.get(j);

                    drawBetweenTwoVertices(previousAssignment,assignment);


                }
                //Last line is between last assignment and depot
                line(getDrawWidth(depot.position.x), getDrawHeight(depot.position.y), getDrawWidth(vehicleList.get(i).assignedRouted.get(vehicleList.get(i).assignedRouted.size() - 1).position.x), getDrawHeight(vehicleList.get(i).assignedRouted.get(vehicleList.get(i).assignedRouted.size() - 1).position.y));

                //line(vertexArrayList.get(0).position.x*10,vertexArrayList.get(0).position.y*10,vehicleList.get(i).assignedRouted.get(0).position.x*10,vehicleList.get(i).assignedRouted.get(0).position.y*10);
                //WHY DO WE MULTIPLY BY 10 RANDOMLY???
            }
        }
    }

    public void draw(){
        background(0);
        fill(255);
        text(frameRate,20,20); //this is the frameRate counter

        //display every vertex in the arraylist
        drawCustomers();
        drawVehicleRoutes();



    }
    public void frameResized(int w, int h){
        //Need to implement resizing
        height=h;
        width=w;
    }


}
