package Reflection;

import java.lang.reflect.Method;

public class Test4 {
    public static void main(String[] args) throws Exception {
//        如何调用操作运行时类中的指定的属性
//        Class clazz = person.class;
//        person o = (person) clazz.newInstance();
//        Field name = clazz.getDeclaredField("name");
//        name.setAccessible(true);
//        name.set(o,"Tom");
//        System.out.println(name.get(o));

//        如何调用操作运行时类中的指定的方法
        Class clazz = person.class;
        person o = (person) clazz.newInstance();
        Method show = clazz.getDeclaredMethod("show", String.class);
        show.setAccessible(true);
        Object china = show.invoke(o, "china");
        System.out.println(china);
        System.out.println("*******调用静态方法*******");
        Method showMe = clazz.getDeclaredMethod("showMe");
        showMe.setAccessible(true);
        showMe.invoke(person.class);

//        如何调用操作运行时类中的指定的构造器

    }
}
