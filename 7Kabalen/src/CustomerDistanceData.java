import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class CustomerDistanceData {
    /*
        Matrix of the distance between all customers
     */
    float[][] DistanceData;
    int[][] SortedIDs;
    int arraySize;

    CustomerDistanceData(ArrayList<Vertex> data){
        arraySize=data.size();
        DistanceData= new float[arraySize][arraySize];
        SortedIDs = new int[arraySize][arraySize];
        for(int i=0;i<arraySize;i++){
            for(int k=0;k<arraySize;k++){
                if(i!=k && DistanceData[i][k]==0) {
                    float dist=CalculateDistanceBetween(data.get(i), data.get(k));
                    //Let's not calculate the same distance twice :)
                    DistanceData[i][k] = dist;
                    DistanceData[k][i] = dist;
                }
            }
        }
    sortDistances();
    }


    //Sorted ids based on distance
    void sortDistances(){
        for (int i=0;i<arraySize;i++){

        }
    }


    float CalculateDistanceBetween(Vertex a, Vertex b){
        float dist=0;
        dist = a.position.dist(b.position);

        return dist;
    }
    float getDistanceBetween(int id1,int id2){
        float dist=0;
        if(id1!=id2){
            //Only do the math if ID's are not the same

        }
        return dist;
    }

}
