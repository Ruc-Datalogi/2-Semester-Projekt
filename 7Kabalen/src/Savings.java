import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Savings {
    private int _capacity;
    private int _weight;
    private double _cost;
    private double _savings;
    private ArrayList<Vertex> vertices;

    private void calculateSavings(){
        double originalCost = 0;
        double newCost = 0;
        double tempCost =0;
        Vertex prev = null;

        //Foreach Vertex in the route:
        for(Vertex c:vertices){
            // Distance from Depot
            tempCost = Math.sqrt((c.position.x*c.position.x)+(c.position.y*c.position.y));
            originalCost += (2.0*tempCost);

            if(prev != null){
                // Distance from previous Vertex to this Vertex
                double x = (prev.position.x - c.position.x);
                double y = (prev.position.y - c.position.y);
                newCost += Math.sqrt((x*x)+(y*y));
            }else{
                //If this is the first Vertex in the route, no change
                newCost += tempCost;
            }
            prev = c;
        }
        newCost += tempCost;
        _cost = newCost;
        _savings = originalCost - newCost;
    }

    public Route(int capacity){
        _capacity = capacity;
        vertices = new ArrayList<Vertex>();
        _weight =0;
        _cost= 0;
        _savings =0;
    }

    public void addVertex(Vertex c, boolean order){
        //Add Vertex to the start or end of the route?
        if(order){
            vertices.add(0,c);
        }else{
            vertices.add(c);
        }

        if(c.c > _capacity){
            System.out.println("Vertex order too large");
        }

        _weight += c.c;

        if(_weight > _capacity){
            System.out.println("Route Overloaded");
        }

        calculateSavings();
    }

    public double getSavings(){
        return _savings;
    }
    public double getCost(){
        return _cost;
    }
    public int getWeight(){
        return _weight;
    }
    public int compareTo(Route r) {
        return Double.compare(r.getSavings(), this._savings);
    }

}

//
//##Sequential solver##
//

public class ClarkeWright
{
    public static int truckCapacity = 0;

    public static ArrayList<List<Vertex>> solve(ArrayList<Vertex> vertices){
        ArrayList<List<Vertex>> solution = new ArrayList<List<Vertex>>();
        HashSet<Vertex> abandoned = new HashSet<Vertex>();

        //calculate the savings of all the pairs
        ArrayList<Route> pairs = new ArrayList<Route>();

        for(int i=0; i<vertices.size(); i++){
            for(int j=i+1; j<vertices.size(); j++){
                Route r = new Route(truckCapacity);
                r.addVertex(vertices.get(i),false);
                r.addVertex(vertices.get(j),false);
                pairs.add(r);
            }
        }
        //order pairs by savings
        Collections.sort(pairs);

        HashSet<Route> routes = new HashSet<Route>();
        routes.add(pairs.get(0));
        pairs.remove(0);

        //start combining pairs into routes
        for(Route ro :routes)
        {
            outerloop: for(int i=0; i<pairs.size(); i++){
                Route r = pairs.get(i);
                Vertex c1 = r.vertices.get(0);
                Vertex c2 = r.vertices.get(r.vertices.size()-1);
                Vertex cr1 = ro.vertices.get(0);
                Vertex cr2 = ro.vertices.get(ro.vertices.size()-1);

                boolean edge = false;
                for(int a=0; a<2;a++)
                {
                    edge = !edge;
                    Vertex e1 = (!edge) ? c1 : c2;
                    Vertex e2 = (edge) ? c1 : c2;
                    //do they have any common nodes?
                    if(e1 == cr1 ||e1 == cr2){
                        //could we combine these based on weight?
                        if(e2.c + ro.getWeight() <= truckCapacity){
                            //Does route already contain BOTH these nodes?
                            if(!ro.vertices.contains(e2)){
                                //no, but is it in another route already?
                                boolean istaken = false;
                                for(Route rr :routes)
                                {
                                    if(rr.vertices.contains(e2)){
                                        istaken = true;
                                        break;
                                    }
                                }
                                if(!istaken){
                                    //No other route have this, add to route
                                    if(e1 == cr1){
                                        ro.addVertex(e2, true);
                                    }else{
                                        ro.addVertex(e2, false);
                                    }
                                }
                            }
                            abandoned.remove(e2);
                            pairs.remove(r);
                            i--;
                            continue outerloop;
                        }
                    }
                }

                //If we reach here, the pair hasn't been added to any routes
                boolean a = false;
                boolean b = false;
                for(Route rr :routes){
                    if(rr.vertices.contains(c1)){
                        a = true;
                    }
                    if(rr.vertices.contains(c2)){
                        b = true;
                    }
                }
                if(!(a||b)){
                    //no routes have any of these vertices, make new route
                    abandoned.remove(c1);
                    abandoned.remove(c2);
                    routes.add(r);
                }else{
                    //Some routes have some of these vertices already
                    if(!a){
                        abandoned.add(c1);
                    }
                    if(!b){
                        abandoned.add(c2);
                    }
                }
                pairs.remove(r);
                i--;
            }

        }

        //A Vertex can be left over due to capacity constraints
        outerloop:for(Vertex C:abandoned){
            //we could tack this onto the end of a route if it would fit
            for(Route r:routes){
                if(r.getWeight() + C.c < truckCapacity)
                {
                    //would this be more efficient than sending a new truck?
                    Vertex[] cca={r.vertices.get(r.vertices.size()-1),
                            r.vertices.get(0)};
                    for(Vertex cc:cca){
                        double X = C.x - cc.x;
                        double Y = C.y - cc.y;
                        if(Math.sqrt((X*X)+(Y*Y))
                                < Math.sqrt((C.x*C.x)+(C.y*C.y)))
                        {
                            r.addVertex(C, false);
                            break outerloop;
                        }
                    }
                }
            }

            //Send a new truck, just for this Vertex
            ArrayList<Vertex> l = new ArrayList<Vertex>();
            l.add(C);
            solution.add(l);
        }

        //output
        for(Route r:routes){
            ArrayList<Vertex> l = new ArrayList<Vertex>();
            l.addAll(r.vertices);
            solution.add(l);
        }
        return solution;
    }

    //
    //##Parallel solver##
    //

    public static ArrayList<List<Vertex>> solveP(ArrayList<Vertex> vertices){
        ArrayList<List<Vertex>> solution = new ArrayList<List<Vertex>>();
        HashSet<Vertex> abandoned = new HashSet<Vertex>();

        //calculate the savings of all the pairs
        ArrayList<Route> pairs = new ArrayList<Route>();

        for(int i=0; i<vertices.size(); i++){
            for(int j=i+1; j<vertices.size(); j++){
                Route r = new Route(truckCapacity);
                r.addVertex(vertices.get(i),false);
                r.addVertex(vertices.get(j),false);
                pairs.add(r);
            }
        }
        //order pairs by savings
        Collections.sort(pairs);

        HashSet<Route> routes = new HashSet<Route>();
        routes.add(pairs.get(0));
        pairs.remove(0);

        //start combining pairs into routes
        outerloop: for(int j=0; j<pairs.size(); j++){
            Route r = pairs.get(j);
            Vertex c1 = r.vertices.get(0);
            Vertex c2 = r.vertices.get(r.vertices.size()-1);

            for(Route ro :routes)
            {
                Vertex cr1 = ro.vertices.get(0);
                Vertex cr2 = ro.vertices.get(ro.vertices.size()-1);
                boolean edge = false;
                for(int a=0; a<2;a++)
                {
                    edge = !edge;
                    Vertex e1 = (!edge) ? c1 : c2;
                    Vertex e2 = (edge) ? c1 : c2;
                    //do they have any common nodes?
                    if(e1 == cr1 || e1 == cr2){
                        //could we combine these based on weight?
                        if(e2.c + ro.getWeight() <= truckCapacity){
                            //Does route already contain BOTH these nodes?
                            if(!ro.vertices.contains(e2)){
                                //no, but is it in another route already?
                                boolean istaken = false;
                                for(Route rr :routes){
                                    if(rr.vertices.contains(e2)){
                                        istaken = true;
                                        break;
                                    }
                                }
                                if(!istaken){
                                    //No other route have this, add to route.
                                    if(c1 == cr1){
                                        ro.addVertex(e2, true);
                                    }else{
                                        ro.addVertex(e2, false);
                                    }
                                }
                            }
                            abandoned.remove(e2);
                            pairs.remove(r);
                            j--;
                            continue outerloop;
                        }
                    }
            }

            }

            //If we reach here, the pair hasn't been added to any routes
            boolean a = false;
            boolean b = false;
            for(Route ro :routes){
                if(ro.vertices.contains(c1)){
                    a = true;
                }
                if(ro.vertices.contains(c2)){
                    b = true;
                }
            }
            if(!(a||b)){
                //no routes have any of these vertices, make new route
                abandoned.remove(c1);
                abandoned.remove(c2);
                routes.add(r);
            }else{
                //Some routes have some of these vertices already
                if(!a){
                    abandoned.add(c1);
                }
                if(!b){
                    abandoned.add(c2);
                }
            }
            pairs.remove(r);
            j--;

        }

        //A Vertex can be left over due to capacity constraints
        outerloop:for(Vertex C:abandoned){
            //we could tack this onto the end of a route if it would fit
            for(Route r:routes){
                if(r.getWeight() + C.c < truckCapacity)
                {
                    //would this be more efficient than sending a new truck?
                    Vertex[] cca={r.vertices.get(r.vertices.size()-1),
                            r.vertices.get(0)};
                    for(Vertex cc:cca){
                        double X = C.x - cc.x;
                        double Y = C.y - cc.y;
                        if(Math.sqrt((X*X)+(Y*Y))
                                < Math.sqrt((C.x*C.x)+(C.y*C.y)))
                        {
                            r.addVertex(C, false);
                            break outerloop;
                        }
                    }
                }
            }

            //Send a new truck, just for this Vertex
            ArrayList<Vertex> l = new ArrayList<Vertex>();
            l.add(C);
            solution.add(l);
        }

        //output
        for(Route r:routes){
            ArrayList<Vertex> l = new ArrayList<Vertex>();
            l.addAll(r.vertices);
            solution.add(l);
        }
        return solution;
    }
}

}
