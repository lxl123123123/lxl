package Girlfriend;

public class PersonTest {
    public static void main(String[] args) {
        Person b = new Person();
        b.setAge(18);
//        int val= b.getAge();
//        System.out.println("用户输入的年龄是:"+val);
        System.out.println(b.getAge());
    }
}
class Person{
    private int age;
    public Person(){
        age = 18;
    }
    public void setAge(int agePerson){
        if (agePerson>=0 && agePerson<=130){
            age = agePerson;
        }
        else{
            throw new RuntimeException("传入的数据非法!");
        }
    }
    public int getAge() {
        return age;
    }
}
