import processing.core.PVector;

import java.util.ArrayList;
import java.util.Arrays;

import static processing.core.PApplet.arrayCopy;
import static processing.core.PApplet.reverse;

public class BruteForce {
    ArrayList<Route> routes = new ArrayList<>();

    public BruteForce(final ArrayList<Centroid> centroids) {
        bruteForce(centroids);
    }

    void bruteForce(ArrayList<Centroid> centroids) {
        for (Centroid centroid : centroids) {
            float bestEver = 2147483647; // largest float value possible.
            int[] order = new int[centroid.Cluster.size()];
            ArrayList<Vertex> knuder = new ArrayList<Vertex>();
            for (Vertex vertex : centroid.Cluster) {
                order[centroid.Cluster.indexOf(vertex)] = centroid.Cluster.indexOf(vertex);
                knuder.add(vertex);
            }
            System.out.println(knuder.size());
            doForce(order, knuder.size(), knuder, bestEver);
        }
    }

    void doForce(int[] order, int n, ArrayList<Vertex> knuder, float best) {
        int largestI = 1;
        Route route = new Route();
        while (largestI != -1) {
            float fact = 1;
            float factdivider = 0;
            factdivider++;
            for(int i=1;i<=n;i++){
                fact = fact*i;
            }
            fact = fact/2+1;
            largestI = -1;
            // STEP 1
            for (int i = 0; i < n - 1; i++) {
                if (order[i] < order[i + 1] && factdivider < fact) {
                    largestI = i;
                }
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
                arrayCopy(endArray, 0, order, largestI + 1, size);
                Vertex[] temp = new Vertex[n];

                for (int i = 0; i < n; i++) {
                    temp[order[i]] = knuder.get(i);
                }
                knuder = new ArrayList<Vertex>(Arrays.asList(temp));
                float length = 0;
                for (int i = 0; i < n; i++) {

                    PVector vertexPosition = knuder.get(i).position;

                    if (i < n - 1) {
                        length += vertexPosition.dist(knuder.get(i + 1).position);
                    }
                    if (i == n - 1) {
                        length += vertexPosition.dist(knuder.get(0).position);
                    }
                }
                if (length < best) {
                    best = length;
                    ArrayList<Vertex> knuderBest = new ArrayList<>(knuder);

                    route.setAssignedVertices(knuderBest);

                }
            }
            if(largestI == -1){
                routes.add(route);
            }
        }
    }

    void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
