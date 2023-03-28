package Girlfriend;

public class PersonTest1 {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.name="li";
//        s1.age=13;
        System.out.println(s1.name+","+s1.getAge()+","+s1.major);


        Student s2 = new Student("tom",11);
        System.out.println(s2.name+","+s2.age);


        Student s3 = new Student("mark",12,"shiYan");
        System.out.println(s3.name+","+s3.age+","+s3.school);


        Student s4 = new Student("long",18,"youDian","ai");
        System.out.println(s4.name+","+s4.age+","+s4.school+","+s4.major);
    }
}
class Student{
    String name;
    int age;
    String school;
    String major;
    public Student(){

    }
    public Student(String n,int a){
        name=n;
        age=a;
    }
    public Student(String n,int a,String s){
        name=n;
        age=a;
        school=s;
    }
    public Student(String n,int a,String s,String m){
        name=n;
        age=a;
        school=s;
        major=m;
    }
    public void setAge(int agePerson){
        age = agePerson;
    }
    public int getAge(){
        return age;
    }
}