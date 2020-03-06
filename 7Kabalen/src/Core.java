import processing.core.*;

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

    public void draw(){
        background(0);
        rect(50,50,50,50);
        text(frameRate,20,20); //this is the frameRate counter

    }
}
