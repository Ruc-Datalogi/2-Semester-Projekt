import processing.core.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

import static processing.core.PApplet.exp;

public class SA {

    ArrayList<Route> s;
    ArrayList<Route> s0;
    float k;
    float kmax;
    float T = 1;

    public void SA(float kmax, ArrayList<Route> initalRoute) {
        this.kmax = kmax;
        s0 = initalRoute;
        k = 0;

        float scost = 0;
        float s0cost = 0;
        float p = 0;

        for (Route route : s) {
            scost = scost + route.cost;
        }

        for (Route route : s0) {
            s0cost = s0cost + route.cost;
        }

        for (int i = 0; kmax < i; i++) {
            k = i;
            T = (k + 1 / kmax);

            if (s0cost < scost) {
                s = s0;
            } else {
                p = exp(-(s0cost - scost / T));
                if (p < Math.random()) {
                    s = s0;
                }
            }


        }

    }

}





