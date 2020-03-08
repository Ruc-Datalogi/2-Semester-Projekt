public class Location {
    /*
        Location class simply contains location information and has a function for calculating the distance to another location
     */
    private int xPos, yPos;

    public Location(int x, int y) {
        this.xPos = x;
        this.yPos = y;
    }

    Location() {

    }

    /*
        Euclidean distance (Pythagorean theorem) for calculating distance between points
     */
    double dist(Location otherLocation) {
        int xDiff = xPos - otherLocation.xPos;
        int yDiff = yPos - otherLocation.yPos;
        return Math.sqrt((xDiff * xDiff) + (yDiff * yDiff));
    }

    void setX(int xPos) {
        this.xPos = xPos;
    }

    void setY(int yPos) {
        this.yPos = yPos;
    }

    public int getX() {
        return xPos;
    }

    public int getY() {
        return yPos;
    }
}
