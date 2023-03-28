package Collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Test2 {
    public static void main(String[] args) {
        ArrayList collection = new ArrayList();
        collection.add(123);
        collection.add(456);
        collection.add("AA");
        collection.add("aa");
        collection.add(new Person("Tom",12));
        collection.add(new Person("Jack",18));
        collection.add(2,"lxl");
//        collection.remove("AA");
//        collection.remove(2);
//        collection.set(2,"BB");
//        System.out.println(collection.get(2));
//        System.out.println(collection.size());
//        方式一
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        //方式二
//        for (Object obj : collection){
//            System.out.println(obj);
//        }
        //方式三
//        for (int i = 0; i < collection.size(); i++) {
//            System.out.println(collection.get(i));
//        }
    }
}
class Person{
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
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
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
