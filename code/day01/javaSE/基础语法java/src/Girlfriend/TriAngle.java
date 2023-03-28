package Girlfriend;

public class TriAngle {
    public static void main(String[] args) {
        Tri p = new Tri();
        p.setBase(2.8);
        p.setHeight(1.5);
        System.out.println("边长为"+p.getBase()+",高为"+p.getHeight());
        Tri p1 = new Tri(2.3,2.6);
        System.out.println("边长为"+p1.getBase()+",高为"+p1.getHeight());
    }
}
class Tri{
    private double base;
    private double height;
    public Tri(){

    }
    public Tri(double b,double h){
        base = b;
        height = h;
    }
    public void setBase(double basePerson){
        base = basePerson;
    }
    public double getBase(){
        return base;
    }
    public void setHeight(double heightPerson){
        height = heightPerson;
    }
    public double getHeight(){
        return height;
    }
}
