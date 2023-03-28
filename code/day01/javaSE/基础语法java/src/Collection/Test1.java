package Collection;

import java.util.*;

public class Test1 {
    public static void main(String[] args) {
        Collection coll = new ArrayList();
        coll.add("123");
        coll.add("ABC");
        coll.add(new String("CDE"));
        coll.add("false");
        List strings = Arrays.asList(new String("lxl,lxh"));
        System.out.println(strings);

        List integers = Arrays.asList(124,578);
        Iterator iterator1 = integers.iterator();
        while (iterator1.hasNext()){
            System.out.println(iterator1.next());
        }
        System.out.println(integers.size());

        Object[] objects = coll.toArray();
        for (int i = 0; i < objects.length; i++) {
            System.out.println(objects[i]);
        }
        System.out.println("****");
        Iterator iterator = coll.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}