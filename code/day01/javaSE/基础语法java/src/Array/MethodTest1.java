package Array;

public class MethodTest1 {
    public static void main(String[] args) {
        Person p1=new Person();
        p1.sex=1;
        p1.age=18;
        p1.study();
        p1.showAge();
        int newAge=p1.addAge(2);
        System.out.println("新年龄为:"+newAge);
        Person p2=new Person();
        p2.age=10;
        p2.showAge();
        int newOneAge=p2.addAge(10);
        System.out.println("新年龄为:"+newOneAge);
    }
}
class Person{
    String name;
    int age;
    int sex;
    public void study(){
        System.out.println("Studying");
    }
    public void showAge(){
        System.out.println(age);
    }
    public int addAge(int i){
        age+=i;
        return age;
    }
}