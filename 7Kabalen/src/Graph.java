import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {
    int vertexCount;
    private ArrayList<Vertex> vertices;
    /*
    a list of all vertices and which vertices are linked to them,
    see linkVertex() for deeper understanding. You can look up a vertex's connection vertices
    by giving them the vertex key. This is used to denote the vehicle routes.
    mb not useful as the vertices themselves contain a list of their neighbours and links.
    */
    public HashMap<Vertex, ArrayList<Vertex>> linkedVertices;
    /*
    A list of all the vertices bordering this vertex
     */
    public HashMap<Vertex, ArrayList<Vertex>> edgeVertices;

    /*
        The graph class should contain all nodes for a graph
        Travel distance(time) between each nodes
        Time used at each node.
        Location of node?
     */
    //Initilize graph object

    /**
     * Takes a list of vertices to generate a graph
     * to make it possible to import multiple vertex
     * data.
     * @param vertices
     */
    public Graph(ArrayList<Vertex> vertices){
        this.vertices = vertices;
    }

    void addVertex(Vertex vertex){
        this.vertices.add(vertex);
    }

    void init(){

    }


}
