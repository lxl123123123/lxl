package Collection;

import java.util.Iterator;
import java.util.Objects;
import java.util.TreeSet;

public class Test3 {
    public static void main(String[] args) {
//        HashSet hashSet = new HashSet();
//        LinkedHashSet hashSet = new LinkedHashSet();
        TreeSet hashSet = new TreeSet();
////        hashSet.add("AA");
////        hashSet.add("CC");
        hashSet.add(new Student("Jack",34));
        hashSet.add(new Student("Tom",12));
        hashSet.add(new Student("Lxl",2));
        hashSet.add(new Student("Jack",56));
        hashSet.add(new Student("Amy",76));
        hashSet.add(new Student("Rose",7));
        Iterator iterator = hashSet.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
class Student implements Comparable{
    private String name;
    private int age;
    public Student(){
    }
    public Student(String name,int age){
        this.name = name;
        this.age = age;
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

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Student){
            Student student = (Student)o;
            int comp = -this.name.compareTo(student.name);
            if (comp != 0){
                return comp;
            }else{
                return Integer.compare(this.age,student.age);
//                if (this.age>student.age){
//                    return 1;
//                }else if (this.age < student.age){
//                    return -1;
//                }else {
//                    return 0;
//                }
            }
        }else {
            throw new RuntimeException("您输入的类型不匹配");
        }
    }
}
