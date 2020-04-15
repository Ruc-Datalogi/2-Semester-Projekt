import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class KMeans {
    int K;
    ArrayList<Vertex> Vertices;
    ArrayList<Centroid> Centroids;
    GfxComponent gfxComponent;
    KMeans(ArrayList<Vertex> Dataset , int K, ArrayList<Vertex> vertexArrayList, PApplet parent, ArrayList<Vehicle> vehicleArrayList){
        this.Vertices = Dataset;
        this.K = K;
        Centroids = new ArrayList<>();
        init();
        this.gfxComponent = new GfxComponent(parent.width, parent.height, vertexArrayList, vehicleArrayList, parent);
        scanner();
    }


    void init(){
        for (int i = 0; i<K; i++){
            int randx = (int)(Math.random() * 100);
            int randy = (int)(Math.random() * 100);
            Centroid centroid = new Centroid();
            centroid.position = new PVector(randx, randy);
            Centroids.add(centroid);
            System.out.println(centroid.position);
        }
    }
    void scanner(){
        for (Vertex vertex: Vertices){
            Centroid tempCentroid = new Centroid();
            for (Centroid centroid: Centroids){
                System.out.println(centroid.Cluster);
                if(vertex.distCentroid > vertex.position.dist(centroid.position)){
                    vertex.distCentroid = vertex.position.dist(centroid.position);
                    tempCentroid = centroid;
                }

            }
            if (tempCentroid!=null){
                tempCentroid.Cluster.add(vertex);
            }

        }
    }
}

class Centroid{
    PVector position;
    ArrayList<Vertex> Cluster;

    Centroid(){
        ArrayList<Vertex> Cluster = new ArrayList<>();
        this.Cluster = Cluster;

    }
}