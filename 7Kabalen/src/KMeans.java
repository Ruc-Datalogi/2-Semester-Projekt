import processing.core.PApplet;
import processing.core.PVector;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class KMeans {
    int K;
    ArrayList<Vertex> Vertices;
    ArrayList<Centroid> Centroids;
    ArrayList<Route> TwoOptedRoutes;
    GfxComponent gfxComponent;

    KMeans(ArrayList<Vertex> Dataset , int K, ArrayList<Vertex> vertexArrayList, PApplet parent, ArrayList<Vehicle> vehicleArrayList){
        this.Vertices = Dataset;
        this.K = K;
        Centroids = new ArrayList<>();
        this.gfxComponent = new GfxComponent(parent.width, parent.height, vertexArrayList, vehicleArrayList, parent);

        init();
        for (int i = 0; i < 5; i++){
            scanner();
            means();
        }
        generateTwoOptRoutesFromCentroids();

    }



    // generate k centroids with a random position, random position should be within the scope
    // of the solomon data set.
    void init(){
        for (int i = 0; i<K; i++){
            float randx = (int)(Math.random() * 77);
            float randy = (int)(Math.random() * 77);
            Centroid centroid = new Centroid();
            centroid.position = new PVector(randx, randy);
            Centroids.add(centroid);
        }
    }

void generateTwoOptRoutesFromCentroids(){
    TwoOpt TwoOptObject = new TwoOpt();
    TwoOptedRoutes = new ArrayList<Route>();
    for(Centroid c : Centroids){
        if(c.Cluster.size()>0) {
            System.out.println("cluster size: " + c.Cluster.size());
            //if(b == true) {
                Route MyRoute = TwoOptObject.makeTwoOptRoute(c.Cluster);
                TwoOptedRoutes.add(MyRoute);
            //}

        }
    }
}

    boolean isValidSolution(){
        int numberOfClusteredVertices=0;
        int numberofVertices=Vertices.size();
        for (Centroid centroid : Centroids){
            numberOfClusteredVertices= numberOfClusteredVertices+  centroid.Cluster.size();
        }
        //System.out.println("Num clustered: " + numberOfClusteredVertices);
        return numberofVertices==numberOfClusteredVertices;
    }
    void scanner(){
        //initialize the clusters
        for (Centroid centroid : Centroids){
            centroid.Cluster = new ArrayList<>();
            centroid.remainingCapacity=200;
        }

        //For all vertices in the solomon data set.
        for (Vertex vertex : Vertices){
            Centroid tempCentroid = new Centroid();

            //Check all centroids and see which distance is best.
            for (Centroid centroid: Centroids){
                if(vertex.distCentroid >= vertex.position.dist(centroid.position) && centroid.remainingCapacity-vertex.d>=0){
                    vertex.distCentroid = vertex.position.dist(centroid.position);
                    tempCentroid = centroid;


                }
            }

            //add vertex to centroids' cluster.
            if (tempCentroid != null ){
                tempCentroid.Cluster.add(vertex);
                tempCentroid.remainingCapacity= tempCentroid.remainingCapacity-vertex.d;
            }
        }
    }

    //Actually not merging, but the means part of kmeans.
    void means(){
        //initialize the distance from the vertex to the centroids, is used
        //in scanner to compare which centroid is best.
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
    int remainingCapacity;

    Centroid(){
        this.Cluster = new ArrayList<>();
        remainingCapacity=200;
    }
}