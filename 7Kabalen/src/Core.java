import processing.core.*;

import java.util.ArrayList;

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

    public void setup() {

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
    }

    public void draw(){
        background(0);
        text(frameRate,20,20); //this is the frameRate counter

        //display every vertex in the arraylist
        for (int i=0; i<vertexArrayList.size(); i++) {
            vertexArrayList.get(i).display();
        }
    }
}
