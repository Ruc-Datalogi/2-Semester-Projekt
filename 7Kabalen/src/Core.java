import processing.core.*;
import processing.data.*;

import java.nio.channels.DatagramChannel;
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


    public void setup(){

        DataGirl datagirl = new DataGirl("SOLOMON2.csv");
        datagirl.setParent(this);
        vertexArrayList = datagirl.generateVertice();
        System.out.println(datagirl.generateVertice().size());
        System.out.println(datagirl.vertexArrayList);
    }

    ArrayList<Vertex> vertexArrayList;


    public void draw(){
        background(0);
        rect(50,50,50,50);
        text(frameRate,20,20); //this is the frameRate counter

        for (int i=0; i<vertexArrayList.size(); i++) {
            Vertex p = vertexArrayList.get(i);
            System.out.println(p.position);
            p.setParent(this);
            p.display();
        }


        }
}
