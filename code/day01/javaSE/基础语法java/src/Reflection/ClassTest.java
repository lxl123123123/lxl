package Reflection;

//获取Class实例的四种方式
public class ClassTest {
    public static void main(String[] args) throws ClassNotFoundException {
//        方式一
        Class<person> clazz1 = person.class;
        System.out.println(clazz1);
//        方式二
        person p = new person();
        Class<? extends person> clazz2 = p.getClass();
        System.out.println(clazz2);
//        方式三
        Class<?> clazz3 = Class.forName("Reflection.person");
        System.out.println(clazz3);

        System.out.println(clazz1 == clazz2);
        System.out.println(clazz2 == clazz3);
//        方式四
        ClassLoader classLoader = ClassTest.class.getClassLoader();
        Class<?> clazz4 = classLoader.loadClass("Reflection.person");
        System.out.println(clazz4);
        System.out.println(clazz1 == clazz4);

        Class<Object> objectClass = Object.class;
        System.out.println(objectClass);
        Class<String> stringClass = String.class;
        System.out.println(stringClass);
        Class<Void> voidClass = void.class;
        System.out.println(voidClass);
        Class<Class> classClass = Class.class;
        System.out.println(classClass);
    }
}