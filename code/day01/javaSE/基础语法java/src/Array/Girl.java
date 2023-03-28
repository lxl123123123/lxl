package Array;
/*注解
    尽管radius是在父类中私密的，但仍然有多种赋值方法；
    1：在父类中定义变量时直接声明radius=几
    2：在空参构造器中声明radius=几
    3：写一个全参构造器，然后在其子类的构造器中使用super(radius),然后造子类对象，造对象时
    刚好就赋值写好了
    4：造子类对象后，因为继承的原因，直接调用  对象.setradius(几)
 */
public class Girl {
    public static void main(String[] args) {
        Cylinder c = new Cylinder(2,1);
        /*方式1*/
//        c.setRadius(2);
        System.out.println("圆柱的表面积为:"+c.findArea()+"体积为:"+c.findVolume());
    }
}

/*父类圆*/
class Circle0{
    private double radius;
    public Circle0(){

    }
    public Circle0(double radius){
        this.radius=radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
    public double findArea(){
        return Math.PI*radius*radius;
    }
}

/*子类*/
class Cylinder extends Circle0{
    private double length;
    public Cylinder(){

    }
    public Cylinder(double radius,double length){
        super(radius);
        this.length=length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getLength() {
        return length;
    }
    public double findArea(){
        return Math.PI*getRadius()*getRadius()*2+2*Math.PI*getRadius()*getLength();
    }
    public double findVolume(){
        return super.findArea()*length;
    }
}