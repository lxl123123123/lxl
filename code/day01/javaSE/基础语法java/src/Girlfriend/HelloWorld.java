package Girlfriend;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        dog d = new dog();
        d.setAge(18);
        d.name="丽丽";
        System.out.println(d.getAge());
        System.out.println(d.name);
    }
}
class dog{
    int age;
    String name;
    String sex;
    public void soutAge(){
        System.out.println(age);
    }
    public void setAge(int age){
        this.age=age;
    }
    public int getAge(){
        return age;
    }
}
