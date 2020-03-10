public class CustomerTimeWindow extends Customer {
    private int serviceTime; //Time it takes for the task to be performed at given node.
    private int readyTime; //The earliest time the costumer is ready to receive the delivery.
    private int dueTime; //The latest time the costumer will accept the delivery

    public int getServiceTime() {
        return serviceTime;
    }

    void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getReadyTime() {
        return readyTime;
    }

    void setReadyTime(int readyTime) {
        this.readyTime = readyTime;
    }

    public int getDueTime() {
        return dueTime;
    }

    void setDueTime(int dueTime) {
        this.dueTime = dueTime;
    }
}
