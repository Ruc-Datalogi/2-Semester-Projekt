import processing.core.PApplet;
import processing.core.PVector;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Kmeans {
    PApplet parent;
    int K;  // assume that K > 0.
    int N;
    ArrayList <Vertex> vertexArrayList;

    //--------########----------

    Kmeans(int Number, int Kluster, ArrayList<Vertex> vertexArrayList) {
        this.N = Number;
        this.K = Kluster;
        this.vertexArrayList = vertexArrayList;
        setup();
    }

    void setup() {
        initCentroids();
        findClusters();
        displayClusters();
    }

    void initCentroids() {
        PVector[] centroids = new PVector[K];
        int j;
        ArrayList<Integer> points = new ArrayList<Integer>();
        int i=0;
        while (i < K) {
            Math.floor(Math.random()*vertexArrayList.size()-1);  // pick a random point
            if (!points.contains(j)) {  // not already picked
                centroids[i]= vertexArrayList.get(j).position;
                points.add(i);
                i++;
            }
        }
    }

    void findClusters() {

    }

    void displayClusters() {

    }

}
