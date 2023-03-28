package Girlfriend;

public class inherit {
    public static void main(String[] args) {
        Cylinder c = new Cylinder();
        c.setRadius(2);
        c.setLength(3);
        System.out.println(c.findVolume());
    }
}
class Circle{
    private double radius;
    public Circle(){
        radius=1;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }
    public double getRadius() {
        return radius;
    }
    public double findArea(){
        return Math.PI*radius*radius;
    }
}
class Cylinder extends Circle{
    private double length;
    public Cylinder(){
        length=1;
    }
    public void setLength(double length) {
        this.length = length;
    }

    public double getLength() {
        return length;
    }
    public double findVolume(){
        double Volume = findArea()*length;
        return Volume;
    }
}
