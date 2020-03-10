public class DepotTimeWindowed extends Depot {
    /*
       Difference between depot and DepotTW will be that the TW variant has a ready and a due time which is the time window that the VRPTW can be completed in.
       Very much akin to
     */
    private int readyTime;
    private int dueTime;
    private int serviceTime;

    public int getReadyTime() {
        return readyTime;
    }

    public void setReadyTime(int readyTime) {
        this.readyTime = readyTime;
    }

    public int getDueTime() {
        return dueTime;
    }

    public void setDueTime(int dueTime) {
        this.dueTime = dueTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }
}
