import processing.core.PApplet;
import processing.core.PVector;

import java.lang.reflect.Array;
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
        this.gfxComponent = new GfxComponent(parent.width, parent.height, vertexArrayList, vehicleArrayList, parent);

        init();
        for (int i = 0; i < 2; i++){
            scanner();
            merge();
        }
    }

    void init(){
        for (int i = 0; i<K; i++){
            float randx = (int)(Math.random() * 77);
            float randy = (int)(Math.random() * 77);
            Centroid centroid = new Centroid();
            centroid.position = new PVector(randx, randy);
            Centroids.add(centroid);
        }
    }

    void scanner(){
        for (Centroid centroid : Centroids) centroid.Cluster = new ArrayList<>();

        for (Vertex vertex: Vertices){
            Centroid tempCentroid = new Centroid();

            for (Centroid centroid: Centroids){
                if(vertex.distCentroid >= vertex.position.dist(centroid.position)){
                    vertex.distCentroid = vertex.position.dist(centroid.position);
                    tempCentroid = centroid;
                }
            }

            if (tempCentroid != null){
                tempCentroid.Cluster.add(vertex);
            }

            System.out.println(Vertices.size());
            for (Centroid centroid : Centroids) System.out.println("Index of: " + Centroids.indexOf(centroid));

            for (Centroid centroid : Centroids) System.out.println("Cluser size: " + centroid.Cluster.size());

            for (Centroid centroid : Centroids) System.out.println("Array cluster: " + centroid.Cluster);

        }
    }

    void merge(){
        for(Vertex vertex : Vertices){
            vertex.distCentroid = 100000000;
        }

        for (Centroid centroid: Centroids){
            float tempx = 0;
            float tempy = 0;

            for (Vertex vertex: centroid.Cluster){
                tempx += vertex.position.x;
                tempy += vertex.position.y;
            }

            if (centroid.Cluster.size() != 0){
            tempx = tempx/centroid.Cluster.size();
            tempy = tempy/centroid.Cluster.size();

            centroid.position.x = tempx;
            centroid.position.y = tempy;
            }
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