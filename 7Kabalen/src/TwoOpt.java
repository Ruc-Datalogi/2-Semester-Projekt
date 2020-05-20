import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;

public class TwoOpt {
    int k = 0;

    public TwoOpt() {

    }

    Route makeTwoOptRoute(ArrayList<Vertex> vertices) {
        k = 0;
        int n = vertices.size();
        boolean done=false;
        int numSwaps;
        Vertex[] knuder = vertices.toArray(new Vertex[0]);
        while(!done){
            numSwaps=0;
            {
                outerLoop:
                for (int i = k; i < n; i++) {
                    for (int j = i + 2; j < n; j++) {

                        float d1 = knuder[i].position.dist(knuder[(i + 1) % n].position) + knuder[j].position.dist(knuder[(j + 1) % n].position);
                        float d2 = knuder[i].position.dist(knuder[j].position) + (knuder[(i + 1) % n].position.dist(knuder[(j + 1) % n].position));

                        if (d2 < d1) {
                            int min = Math.min((i + 1) % n, (j + 1) % n);
                            int max = Math.max((i + 1) % n, ((j + 1) % n));
                            Vertex[] m = Arrays.copyOfRange(knuder, min, max);
                            invertUsingFor(m);
                            Vertex[] l = Arrays.copyOfRange(knuder, 0, min);
                            Vertex[] r = Arrays.copyOfRange(knuder, max, n);
                            System.arraycopy(l, 0, knuder, 0, l.length);
                            System.arraycopy(m, 0, knuder, l.length, m.length);
                            System.arraycopy(r, 0, knuder, l.length + m.length, r.length);
                            numSwaps++;
                            k = i;
                            continue outerLoop;
                        }
                    }
                }
                k = 0;
            }
            if(numSwaps==0){done=true;}
        }
        Route newRoute = new Route();
        for (Vertex v : knuder) {
            newRoute.addVertex(v);
        }
        return newRoute;
    }

    void invertUsingFor (Object[]array){
        for (int i = 0; i < array.length / 2; i++) {
            Object temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }
}

