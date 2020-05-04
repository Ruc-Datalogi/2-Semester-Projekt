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
        for (int i = 0; i < 10; i++){
            scanner();
            merge();
        }
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
    void merge(){
        float tempx = 0;
        float tempy = 0;
        for (Centroid centroid: Centroids){
            for (Vertex vertex: centroid.Cluster){
                tempx += vertex.position.x;
                tempy += vertex.position.y;
            }
            tempx = tempx/centroid.Cluster.size();
            tempy = tempy/centroid.Cluster.size();
            centroid.position.x = tempx;
            centroid.position.y = tempy;
            centroid.Cluster = new ArrayList<>();
        }
    }

}

class Centroid{
    PVector position;
    ArrayList<Vertex> Cluster;

    Centroid(){
        this.Cluster = new ArrayList<>();

    }
}