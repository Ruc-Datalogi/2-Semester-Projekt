import java.util.ArrayList;
import java.util.List;

class TestLoader {

    /*
    Using this class as a way to test other classes and how to use them in combination

     */
    private int minCord = 0;
    private int maxCord = 100;
    private int range = maxCord - minCord + 1;
    private int numRandomLocs = 10;
    private List<Location> locationList;
    private List<CustomerTimeWindow> costumerTimeWindowList;
    private DepotTimeWindowed testDepot;

    public TestLoader() {
        locationList = new ArrayList(numRandomLocs);
        costumerTimeWindowList = new ArrayList<>();
        testDepot = new DepotTimeWindowed();

        Location depot = new Location();

        depot.setX((int) (Math.random() * range) + minCord);
        depot.setY((int) (Math.random() * range) + minCord);
        testDepot.setLocation(depot);
        testDepot.setReadyTime(0);
        testDepot.setServiceTime(0);
        testDepot.setDueTime(12000);
        for (int i = 0; i < numRandomLocs; i++) {
            Location randomLoc = new Location();

            randomLoc.setX((int) (Math.random() * range) + minCord);
            randomLoc.setY((int) (Math.random() * range) + minCord);
            CustomerTimeWindow costumer = new CustomerTimeWindow();
            costumer.setLocation(randomLoc);
            costumer.setServiceTime(30);
            costumer.setReadyTime(0);
            costumer.setDueTime(9001);

            locationList.add(randomLoc);
            costumerTimeWindowList.add(costumer);
        }
    }

}
