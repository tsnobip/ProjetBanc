package mesSources.model;


public class Circle { 
    private Point center;
    private double radius;
   
    public Circle(Point center, float radius) {
        this.center = center;
        this.radius = radius;
    }

    public boolean contains(Point p) {  return p.distanceTo(center) <= radius; }

    public double area()      { return Math.PI * radius * radius; }
    public double perimeter() { return 2 * Math.PI * radius;      }

    public boolean intersects(Circle c) {
        return center.distanceTo(c.center) <= radius + c.radius;
    }


}