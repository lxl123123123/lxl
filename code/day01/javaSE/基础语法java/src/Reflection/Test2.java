package Reflection;

//创建运行时类的对象

import java.lang.reflect.Field;

public class Test2 {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
//        Class<person> clazz = person.class;
        Class<?> clazz = Class.forName("Reflection.person");
        person o = (person) clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields){
            System.out.println(f);
        }
        o.setAge(12);
        System.out.println(o);
    }
}
