import java.util.ArrayList;
import java.util.Arrays;

public class TwoOpt {
    int k = 0;

    public TwoOpt() {

    }

    Route makeTwoOptRoute(ArrayList<Vertex> vertices) {
        k = 0;
        int buf2 = k;
        int n = vertices.size();
        boolean b = true;
        Vertex[] knuder = vertices.toArray(new Vertex[0]);
        System.out.println("Knu længd " + knuder.length + ", N: " + n);
        outerloop:
        {
            System.out.println("We go from top");
            for (int i = k; i < n; i++) {
                System.out.println("i: " + i);
                for (int j = i + 2; j < n; j++) {
                    System.out.println("And  J is " + j);
                    float d1 = knuder[i].position.dist(knuder[(i + 1) % n].position) + knuder[j].position.dist(knuder[(j + 1) % n].position);
                    //float d1 = vertices.get(i).position.dist(vertices.get((i+1)%n).position) +  vertices.get(j).position.dist(vertices.get((j+1)%n).position);
                    float d2 = knuder[i].position.dist(knuder[j].position) + (knuder[(i + 1) % n].position.dist(knuder[(j + 1) % n].position));
                    //float d2 = vertices.get(i).position.dist(vertices.get((j)).position) +  vertices.get(i+1).position.dist(vertices.get((j+1)%n).position);
                    System.out.println("We do distance mathemathics :)");
                    if (d2 < d1) {

                        int min = Math.min((i + 1) % n, (j + 1) % n);
                        int max = Math.max((i + 1) % n, ((j + 1) % n));
                        Vertex[] m = Arrays.copyOfRange(knuder, min, max);
                        invertUsingFor(m);
                        Vertex[] l = Arrays.copyOfRange(knuder, 0, min);
                        Vertex[] r = Arrays.copyOfRange(knuder, max, n);
                        System.out.println("r: " + r.length + "l: " + l.length + "m: " + m.length);
                        System.arraycopy(l, 0, knuder, 0, l.length);
                        System.arraycopy(m, 0, knuder, l.length, m.length);
                        System.arraycopy(r, 0, knuder, l.length + m.length, r.length);
                        System.out.println(":)");
                        k = i; // Scanner kun ucheckede kanter
                        break outerloop;
                    }
                }

                System.out.println("k:" + k);
                k = 0;
            }
        }
        System.out.println("We got out");
        Route newRoute = new Route();
        for (Vertex v : knuder) {
            newRoute.addVertex(v);
        }
        System.out.println("We made route");
        return newRoute; //cba errors
    }
    void invertUsingFor (Object[]array){
        for (int i = 0; i < array.length / 2; i++) {
            Object temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }

}

