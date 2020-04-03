import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class CustomerDistanceData {
    /*
        Matrix of the distance between all customers
     */
    int arraySize;

    class distData{
        float distance=0;
        int id1;
        int id2;
        distData(){

        }

        @Override
        public String toString() {
            return "distData[" +id1 + "->" + id2 + "] {" +
                    "distance=" + distance +
                    '}';
        }
    }
    distData DistanceData[][];
    class SortbyDistance implements Comparator<distData>
    {
        // Used for sorting in ascending order of
        // roll number
        public int compare(distData a, distData b)
        {
            return (int) (a.distance - b.distance);
        }
    }


    CustomerDistanceData(ArrayList<Vertex> data){
        arraySize=data.size();
        DistanceData= new distData[arraySize][arraySize];
        for(int i=0;i<arraySize;i++){
            for(int k=0;k<arraySize;k++){
                DistanceData[i][k] = new distData();
                DistanceData[k][i] = new distData();
                if(i!=k && DistanceData[i][k].distance==0) {
                    float dist=CalculateDistanceBetween(data.get(i), data.get(k));
                    //Let's not calculate the same distance twice :)
                    DistanceData[i][k].distance = dist;
                    DistanceData[i][k].id1=i;
                    DistanceData[i][k].id2=k;
                    DistanceData[k][i].distance = dist;
                    DistanceData[k][i].id1=i;
                    DistanceData[k][i].id2=k;

                }
            }
        }
        sortDistances();
    }


    //Sorted ids based on distance
    void sortDistances(){
        for (int i=0;i<arraySize;i++) {
            Arrays.sort(DistanceData[i], new SortbyDistance());
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
            return DistanceData[id1][id2].distance;
        }
        return dist;
    }


}
