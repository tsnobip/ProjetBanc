package mesSources.model;

public class Point { 
    private double x;   // Cartesian
    private double y;   // coordinates
   

    // create and initialize a point with given (x, y)
    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    // accessor methods  
    public double x() { return x; }
    public double y() { return y; }

    // return Euclidean distance between this point and that point
    public double distanceTo(Point that) {
        double dx = this.x - that.x;
        double dy = this.y - that.y;
        return Math.sqrt(dx*dx + dy*dy);
    }


    // return string representation of this point
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

