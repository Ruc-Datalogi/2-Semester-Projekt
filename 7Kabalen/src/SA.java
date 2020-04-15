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
    double T = 100;


    public void SA(float kmax, ArrayList<Route> initalRoute) {
        this.kmax = kmax;
        s0 = initalRoute;
        k = 0;
        float S = 0;
        float S0 = 0;
        float p = 0;
        double alpha = 0.0015;
        float TStop = 230;


        while (T > TStop) {
            for (int i = 0; kmax < i; i++) {
                k = i;
                T = (k + 1 / kmax);

                if (S0 < S) {
                    S = S0;
                } else {
                    p = exp((float) -(S0 - S / T));
                    if (p < Math.random()) {
                        S = S0;

                    }
                }
                T = alpha * T;
            }
        }

    }


}





