package Collection;
/*
关于TreeSet集合的经典例题，其重点在于重写compareTo方法的逻辑，以及嵌套属性为一个类
 */
import java.util.Iterator;
import java.util.TreeSet;

public class Test4 {
    public static void main(String[] args) {
        TreeSet<Employee> treeSet = new TreeSet<Employee>();
        Employee e1 = new Employee("libingshan",53,new MyDate(1969,12,13));
        Employee e2 = new Employee("wangyafeng",52,new MyDate(1970,10,18));
        Employee e3 = new Employee("lixiaolong",24,new MyDate(1998,6,29));
        Employee e4 = new Employee("lixiaolong",24,new MyDate(1998,6,28));
        Employee e5 = new Employee("huanhuan",6,new MyDate(2010,6,1));
        treeSet.add(e1);
        treeSet.add(e2);
        treeSet.add(e3);
        treeSet.add(e4);
        treeSet.add(e5);
        Iterator<Employee> iterator = treeSet.iterator();
        while (iterator.hasNext()){
            Employee next = iterator.next();
            System.out.println(next);
        }

    }
}
class Employee implements Comparable<Employee>{
    private String name;
    private int age;
    private MyDate birthday;

    public Employee() {
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public MyDate getBirthday() {
        return birthday;
    }

    public void setBirthday(MyDate birthday) {
        this.birthday = birthday;
    }

    public Employee(String name, int age, MyDate birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }

    @Override
    public int compareTo(Employee o) {
        int com = this.name.compareTo(o.name);
        if (com !=0){
            return com;
        }else {
            int age1 = Integer.compare(this.age,o.age);
            if (age1!=0){
                return age1;
            }else {
                return this.birthday.compareTo(o.birthday);
            }
        }
    }

//    @Override
//    public int compareTo(Object o) {
//        if (o instanceof Employee){
//            Employee employee = (Employee)o;
//
//            int com = this.name.compareTo(employee.name);
//            if (com != 0){
//                return com;
//            }else {
//                int age1 = Integer.compare(this.age,employee.age);
//                if (age1 != 0){
//                    return age1;
//                }else {
//                   return this.birthday.compareTo(employee.birthday);
//                }
//            }
//        }
//            throw new RuntimeException("您输入的数据类型不匹配");
//    }
}
class  MyDate implements Comparable<MyDate>{
    private int year;
    private int month;
    private int day;

    public MyDate() {
    }

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "MyDate{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }

    @Override
    public int compareTo(MyDate o) {
        int year1 = Integer.compare(this.year,o.year);
        if (year1 != 0){
            return year1;
        }else {
            int month1 = Integer.compare(this.month,o.month);
            if (month1 != 0){
                return month1;
            }else {
                return Integer.compare(this.day,o.day);
            }
        }
    }

//    @Override
//    public int compareTo(Object o) {
//        if (o instanceof MyDate){
//            MyDate myDate = (MyDate)o;
//            int year1 = Integer.compare(this.year,myDate.year);
//            if (year1 != 0){
//                return year1;
//            }
//            int month1 = Integer.compare(this.month,myDate.month);
//            if (month1 != 0){
//                return month1;
//            }
//            return Integer.compare(this.day,myDate.day);
//        }
//        throw new RuntimeException("您输入的数据类型不匹配");
//    }
}
