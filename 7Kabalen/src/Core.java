import processing.core.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Core extends PApplet {
    /*
    Core should initialize and manage the program
     */

    public static void main(String[] args) {
        PApplet.main("Core");


    }

    public void settings() {
        size(1200, 720);
    }

    ArrayList<Vertex> vertexArrayList;
    ArrayList<Vehicle> vehicleList;
    CW cw;
    gfxComponent gfxComponent;

    public void setup() {
        float initClock2 = System.nanoTime();
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

        gfxComponent = new gfxComponent(this.width,this.height,vertexArrayList,vehicleList,this);
        cw           = new CW(vertexArrayList, this, vehicleList);

        float initClock;
        initClock = System.nanoTime();
        timeToInit = (float) ((initClock-initClock2)*Math.pow(10,-9));
        System.out.println();
    }

    float timeToInit;
    public void draw() {
        background(0);

        fill(0,255,0);
        text(frameRate, 20, 20);
        text("Time to Initialisation: " + timeToInit, 20, 35);

        cw.gfxComponent.drawCustomers();
        cw.gfxComponent.drawRoutes(cw.routes);
        //cw.scanner();

        if(run == true){
            cw.stepScanner();
        }
    }

    //Controls
    boolean run = false;
    public void keyPressed() {
        if ((key >= 'A' && key <= 'Z') || (key >= 'a' && key <= 'z')) {
            cw.stepScanner();
        } else if ((keyCode == BACKSPACE)){
            run = !run;
        }
    }
}
