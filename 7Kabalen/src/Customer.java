public class Customer {
    /*
        Each node contains information such as: location, stopping time,
        (Consider travel time between each node as data)?
     */
    private Location ourLocation;
    Customer(){
        
    }

    public Location getLocation() {
        return ourLocation;
    }

    void setLocation(Location ourLocation) {
        this.ourLocation = ourLocation;
    }


}
