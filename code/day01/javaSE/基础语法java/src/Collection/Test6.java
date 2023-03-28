package Collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Test6 {
    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        hashMap.put("AA",123);
        hashMap.put("DD",345);
        hashMap.put("FF",129);
        hashMap.put("YY",245);
        System.out.println(hashMap);
        Set keySet = hashMap.keySet();
        Iterator iterator = keySet.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        Collection values = hashMap.values();
        Iterator iterator1 = values.iterator();
        while (iterator1.hasNext()){
            System.out.println(iterator1.next());
        }
        Set set = hashMap.entrySet();
        Iterator iterator2 = set.iterator();
        while (iterator2.hasNext()){
            System.out.println(iterator2.next());
        }
    }
}
