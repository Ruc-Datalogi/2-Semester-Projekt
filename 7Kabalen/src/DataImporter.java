
import processing.core.PApplet;
import processing.core.PVector;
import processing.data.Table;
import processing.data.TableRow;

import java.util.ArrayList;

//this fucker can import 1000 datas a minute.
public class DataImporter {
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(final Table table) {
        this.table = table;
    }

    public TableRow getRow() {
        return row;
    }

    public void setRow(final TableRow row) {
        this.row = row;
    }

    public PApplet getParent() {
        return parent;
    }

    public ArrayList<Vertex> getVertexArrayList() {
        return vertexArrayList;
    }

    public void setVertexArrayList(final ArrayList<Vertex> vertexArrayList) {
        this.vertexArrayList = vertexArrayList;
    }

    private String name;
    private Table table;
    private TableRow row;
    private PApplet parent;
    private ArrayList<Vertex> vertexArrayList;

    /**
     * She handles all the data be nice to her.
     *
     * @param name takes the name of your .csv file and reads it
     *             Make sure that the .csv file is in the same folder or
     *             in a subfolder to \src.
     */
    DataImporter(final String name, PApplet p) {
        this.name = name;
        this.parent = p;
    }

    /**
     * Make sure when testing solomons 100, that you correctly save the csv.
     * not using excels ";" but using actual commas. otherwise
     * it can't be read.
     *
     * @return Returns an arraylist of all the vertexes in the solomon vrptw
     */
    public ArrayList<Vertex> generateVertice() {
        //load data
        table = parent.loadTable(name, "header");
        row = table.getRow(0);

        if (table != null) {
            System.out.println("Data has sucessfully been loaded.");
        }

        //ArrayList constructor
        ArrayList<Vertex> tempVertexArrayList = new ArrayList<>();

        //sorts the imported data, and constucts them as vertices in an arrayList.
        for (int i = 0; i < table.getRowCount(); i++) {
            PVector xy = new PVector(table.getInt(i, 1), table.getInt(i, 2));
            Vertex vertex = new Vertex(xy, table.getInt(i, 4), table.getInt(i, 5), table.getInt(i, 3), table.getInt(i, 6));
            tempVertexArrayList.add(vertex);
        }
        return tempVertexArrayList;
    }
}