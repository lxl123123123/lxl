package Collection;

import java.util.ArrayList;
import java.util.Iterator;

//通配符的使用<?>。如果集合中泛型类型不一致，但是想输出遍历的话，那么就要写很多的重载的遍历方法
//所以为了避免这种情况，就要寻找他们的父类。自此引入了通配符的使用
public class Test9 {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<Integer> arrayList1 = new ArrayList<>();

        arrayList.add("aa");
        arrayList.add("bb");
        arrayList.add("cc");
        arrayList.add("dd");

        arrayList1.add(123);
        arrayList1.add(456);
        arrayList1.add(789);
        arrayList1.add(145);

        ArrayList<?> arrayList2 = new ArrayList<>();
        arrayList2 = arrayList;
        arrayList2 = arrayList1;
        Object o = arrayList2.get(0);
        System.out.println(o);

        PersonMe personMe = new PersonMe();
        personMe.show(arrayList);
        System.out.println("*******");
        personMe.show(arrayList1);
    }
}
class PersonMe{
    public void show(ArrayList<?> list){
        Iterator<?> iterator = list.iterator();
        while (iterator.hasNext()){
//            Object next = iterator.next();
            System.out.println(iterator.next());
        }
    }
}
