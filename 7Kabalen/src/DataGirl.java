import processing.core.*;
import processing.data.*;
import processing.core.PVector;

import java.util.ArrayList;

public class DataGirl {
    String name;
    Table table;

    DataGirl(String name){
        this.name = name;
    }

    public ArrayList<Vertex> generateVertice(){
        ArrayList<Vertex> vertexArrayList = new ArrayList<>();

        for (int i = 0; i==table.getRowCount();i++ ) {
            PVector xy = new PVector(table.getInt(i, 1), table.getInt(i, 2));
            Vertex vertex = new Vertex(xy, table.getInt(i, 4), table.getInt(i, 5), table.getInt(i, 3), table.getInt(i, 6));
            vertexArrayList.add(vertex);
        }
        return vertexArrayList;
    }


}
