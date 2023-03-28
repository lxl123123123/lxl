package Array;

public class MethodTest5 {
    public static void main(String[] args) {
        MyDate me=new MyDate();
        MyDate her=new MyDate();
        me.year=2003;
        me.month=2;
        me.day=28;
        her.year=2003;
        her.month=8;
        her.day=18;
        System.out.println(me.info());
        System.out.println(her.info());
    }
}
class MyDate{
    int year;
    int month;
    int day;
    public String info(){
        return "我的生日是"+year+"年"+month+"月"+day;
    }
}
