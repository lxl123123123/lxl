package Reflection;

//理解反射的动态性，在编译时我也不知道造哪个类的对象，只有在运行时才知道，才能确定
//所以才出现了反射

import java.util.Random;

public class Test3 {
    public static void main(String[] args) {
        for (int i = 0; i<100; i++){
            int num = new Random().nextInt(3);
            String classPath = "";
            switch (num){
                case 0:
                    classPath = "java.util.Date";
                    break;
                case 1:
                    classPath = "java.lang.Object";
                    break;
                case 2:
                    classPath = "Reflection.person";
                    break;
            }
            try {
                Object girlFriend = Test3.getGirlFriend(classPath);
                System.out.println(girlFriend);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static Object getGirlFriend(String classPath) throws Exception {
        Class<?> clazz = Class.forName(classPath);
        return clazz.newInstance();
    }
}
