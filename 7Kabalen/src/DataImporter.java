import processing.core.*;
import processing.data.*;
import processing.core.PVector;

import java.util.ArrayList;

//this fucker can import 1000 datas a minute.
public class DataImporter {
    String name;
    Table table;
    TableRow row;
    PApplet parent;
    ArrayList<Vertex> vertexArrayList;

    /**
     * She handles all the data be nice to her.
     * @param name takes the name of your .csv file and reads it
     * Make sure that the .csv file is in the same folder or
     * in a subfolder to \src.
     */
    DataImporter(String name){
        this.name = name;
    }

    /**
     * Make sure when testing solomons 100, that you correctly save the csv.
     * not using excels ";" but using actual commas. otherwise
     * it can't be read.
     * @return Returns an arraylist of all the vertexes in the solomon vrptw
     */
    public ArrayList<Vertex> generateVertice(){
        //load data
        table = parent.loadTable(name,"header");
        row   = table.getRow(0);

        if(table != null){System.out.println("Data has sucessfully been loaded.");}

        //ArrayList constructor
        ArrayList<Vertex> tempVertexArrayList = new ArrayList<>();

        /*
        System.out.println("Data row count: " + table.getRowCount());
        System.out.println("Data column count: " + table.getColumnCount());
        System.out.println("test" + row.getInt(1));
        */

        //sorts the imported data, and constucts them as vertices in an arrayList.
        for (int i = 0; i<table.getRowCount();i++ ) {
            PVector xy = new PVector(table.getInt(i, 1), table.getInt(i, 2));

            Vertex vertex = new Vertex(xy, table.getInt(i, 4), table.getInt(i, 5), table.getInt(i, 3), table.getInt(i, 6));
            tempVertexArrayList.add(vertex);
            System.out.println(xy);
        }
        //debating removing this
        tempVertexArrayList.get(0).setDepot();
        vertexArrayList = tempVertexArrayList;
        return tempVertexArrayList;
    }

    void setParent(PApplet p) {
        parent = p;
    }


}
