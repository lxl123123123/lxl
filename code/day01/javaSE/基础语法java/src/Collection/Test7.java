package Collection;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class Test7 {
    public static void main(String[] args) {
        TreeMap treeMap = new TreeMap(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof User && o2 instanceof User){
                    User user1 = (User)o1;
                    User user2 = (User)o2;
                    return Integer.compare(user1.getAge(),user2.getAge());
                }
                throw new RuntimeException("您输入的数据有误");
            }
        });
        User u1 = new User("Tom",56);
        User u2 = new User("Jerry",32);
        User u3 = new User("Jerry",28);
        User u4 = new User("Mom",14);
        treeMap.put(u1,98);
        treeMap.put(u2,89);
        treeMap.put(u3,76);
        treeMap.put(u4,100);
        Set set = treeMap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
class User{
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User() {
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
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

//    @Override
//    public int compareTo(Object o) {
//        if (o instanceof User){
//            User User = (User)o;
//            int com = this.name.compareTo(User.name);
//            if (com != 0){
//                return -com;
//            }else {
//                return Integer.compare(this.age,User.age);
//            }
//        }
//        throw new RuntimeException("您输入的的数据不匹配");
//    }
}
