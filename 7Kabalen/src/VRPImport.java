import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class VRPImport {
    /*
    Let's actually import the format that is presented to us :)
    Depots have demand of 0
    Customers have demand above 0

     */
    VRPImport(String path) {


        try {
           File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    public void readVrpWebFormat(){

    }
}