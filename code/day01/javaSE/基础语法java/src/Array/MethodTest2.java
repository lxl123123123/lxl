package Array;

import java.util.concurrent.Callable;

public class MethodTest2 {
    public static void main(String[] args) {
        Circle a=new Circle();
        a.r=2;
        System.out.println(a.ji());
    }
}
class Circle{
    double r;
    public double ji(){
        return Math.PI*r*r;
    }
}
