import processing.core.PVector;

import java.util.ArrayList;

import static processing.core.PApplet.arrayCopy;
import static processing.core.PApplet.reverse;

public class BruteForce {

    public BruteForce(){

    }

    void bruteForce(ArrayList<Centroid> centroids) {
        for (Centroid centroid : centroids){
            for(Vertex vertex : centroid.Cluster) {
                System.out.println(centroid.Cluster.indexOf(vertex));
            }
        }


    }
    void doForce(int[] order, int n){
        // STEP 1
        int largestI = -1;
        for (int i = 0; i < n - 1; i++) {
            if (order[i] < order[i + 1]) {
                largestI = i;
            }
        }

        if (largestI == -1) {
            System.out.println("finished & LargestI =" + largestI);
        }

        if (largestI != -1) {
            // STEP 2
            int largestJ = -1;
            for (int j = 0; j < n; j++) {
                if (order[largestI] < order[j]) {
                    largestJ = j;
                }
            }
            // STEP 3
            swap(order, largestI, largestJ);

            // STEP 4
            int size = n - largestI - 1;
            int[] endArray = new int[size];
            arrayCopy(order, largestI + 1, endArray, 0, size);
            endArray = reverse(endArray);
            arrayCopy(endArray, 0, order, largestI+1, size);
            ArrayList<Vertex> temp = new ArrayList<>();

            /*for (int i=0; i<n; i++) {
                 = knuder[i];
            }
            for (int i=0; i<n; i++) {
                knuder[i] = temp[i];
            }*/
        }
    }
    void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
