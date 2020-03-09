import processing.core.*;
import processing.data.*;

import java.nio.channels.DatagramChannel;

public class Core extends PApplet{
    /*
    Core should initialize and manage the program

     */
    public static void main(String[] args) {
        PApplet.main("Core");
    }

    public void settings() {
        size(1200, 720);
    }

    public void setup(){
        DataGirl datagirl = new DataGirl("SOLOMON1C.CSV");
        Table table = loadTable(datagirl.name);
        datagirl.table = table;
        if(table != null){System.out.println("Data has sucessfully been loaded.");}
        datagirl.generateVertice();
    }

    public void draw(){
        background(0);
        rect(50,50,50,50);
        text(frameRate,20,20); //this is the frameRate counter


    }
}
