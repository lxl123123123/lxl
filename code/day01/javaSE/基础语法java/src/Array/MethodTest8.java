package Array;

public class MethodTest8 {
    public static void main(String[] args) {
        PassObject test = new PassObject();
        Circle1 c = new Circle1();
        test.printAreas(c,5);
    }
}
class Circle1{
    double radius;
    public double findArea(){
        double ji = Math.PI*radius*radius;
        return ji;
    }
}
class PassObject{
    public void printAreas(Circle1 c,int time){
        for (int i=1;i<=time;i++){
            c.radius=i;
            double mian=c.findArea();
            System.out.println(c.radius+"\t\t"+mian);
        }
    }
}
