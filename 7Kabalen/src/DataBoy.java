import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import processing.core.PVector;

//this fucker imports 900 datas a minute.
public class DataBoy {
    String fileName;


    DataBoy(String fileName){
        this.fileName = fileName;
    }

    private ArrayList<Vertex> importedSolomonData(String fileName) {
        ArrayList<Vertex> vertexArrayList = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {

            // read the first line from the text file
            String line = br.readLine();

            // loop until all lines are read
            while (line != null) {

                // use string.split to load a string array with the values from
                // each line of
                // the file, using a comma as the delimiter
                String[] attributes = line.split(",");

                Vertex vertex = createVertex(attributes);

                // adding vertex into ArrayList
                vertexArrayList.add(vertex);

                // read next line before looping
                // if end of file reached, line would be null
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return vertexArrayList;
    }

    private static Vertex createVertex(String[] metadata) {
        System.out.println("you never even got here lol");
        int costumerNumber = Integer.parseInt(metadata[0]);
        int x              = Integer.parseInt(metadata[1]);
        int y              = Integer.parseInt(metadata[2]);
        int demand         = Integer.parseInt(metadata[3]);
        int readyTime      = Integer.parseInt(metadata[4]);
        int dueDate        = Integer.parseInt(metadata[5]);
        int serviceTime    = Integer.parseInt(metadata[6]);
        PVector xy         = new PVector(x,y);

        // create and return vertex of this metadata
        return new Vertex(xy,readyTime,dueDate,demand,serviceTime);
    }
    //stolen from https://www.java67.com/2015/08/how-to-load-data-from-csv-file-in-java.html
}
