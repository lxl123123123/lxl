package Girlfriend;

public class inheritTest {
    public static void main(String[] args) {
        student s = new student(18, "李霄龙", "计科");
        s.show();
    }
}
class person{
    private int  age;
    private String name;

    public person(){
        super();
    }
    public person(int age,String  name){
        super();
        this.age=age;
        this.name=name;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String  getName() {
        return name;
    }
    public void show(){
        System.out.print("姓名:"+name+",年龄"+age);
    }
}
class student extends person{
    private String major;

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMajor() {
        return major;
    }
    public student(){
        super();
    }
    public student(int age,String name,String major){
        super(age,name);
        this.major=major;
    }
    public void show(){
        super.show();
        System.out.println("专业为:"+major);
    }
}
