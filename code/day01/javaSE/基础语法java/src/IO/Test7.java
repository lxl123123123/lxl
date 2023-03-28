package IO;
//对象流的应用------>重点在学会序列化和反序列化，而对象流用的却不一定多

//注意点：1：要想自定义类是可序列化的，那么这个类必须实现Serializable接口
// 并且声明private static final long serialVersionUID = -68497470754667710L常量
//同时，此自定义类的所有属性也必须为可序列化的(基本数据类型已经默认为可序列化的了，
// 那么如果还有private Account acct，那么这个Account类也要声明为可序列化的，
// 因为他是作为属性出现的)
//2：ObjectInputStream和ObjectOutputStream不能序列化static,transient的变量

import java.io.*;

public class Test7 {
    public static void main(String[] args) {
////        序列化，写到文件中保存起来
//        ObjectOutputStream oos = null;
//        try {
//            File file = new File("基础语法java\\hello2.txt");
//            FileOutputStream fos = new FileOutputStream(file);
//            oos = new ObjectOutputStream(fos);
//            oos.writeObject(new Person("Jerry",18));
//            oos.writeObject(new String("LXL"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                oos.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        反序列化，从文件中读出来
        Object o = null;
        Object o1 = null;
        ObjectInputStream ois = null;
        try {
             ois = new ObjectInputStream(new FileInputStream("基础语法java\\hello2.txt"));
            o = ois.readObject();
//             Person p = (Person)o;
            o1 = ois.readObject();
//             String p1 = (String)o1;
            System.out.println(o);
            System.out.println(o1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
class Person implements Serializable{

    private static final long serialVersionUID = -68497470754667710L;
    private String name;
    private int age;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
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

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }
}
