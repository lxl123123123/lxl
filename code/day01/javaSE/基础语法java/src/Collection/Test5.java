package Collection;

import java.util.HashMap;
/*
Map接口中的一些常用方法的测试
 */
public class Test5 {
    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        hashMap.put("AA",123);
        hashMap.put("DD",345);
        hashMap.put("FF",129);
        hashMap.put("YY",245);
        System.out.println(hashMap);
        HashMap hashMap1 = new HashMap();
        hashMap1.put("lxl",129);
        hashMap1.put("lxh",245);
        hashMap.putAll(hashMap1);
        System.out.println(hashMap);
        Object dd = hashMap.remove("DD");
        System.out.println(dd);
        System.out.println(hashMap);
//        hashMap.clear();
//        System.out.println(hashMap.size());
//        System.out.println(hashMap);
        System.out.println(hashMap.get("AA"));
        boolean isExit = hashMap.containsKey("AA");
        System.out.println(isExit);
        isExit = hashMap.containsValue(245);
        System.out.println(isExit);
        hashMap.clear();
        System.out.println(hashMap.isEmpty());
        boolean equals = hashMap.equals(hashMap1);
        System.out.println(equals);
    }
}
