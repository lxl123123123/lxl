package Reflection;

//使用ClassLoader加载配置文件

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Test1 {
    public static void main(String[] args) throws IOException {
        Properties pro = new Properties();
//        FileInputStream fis = new FileInputStream("基础语法java\\src\\jdbc.properties");
//        pro.load(fis);
        ClassLoader classLoader = Test1.class.getClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream("jdbc.properties");
        pro.load(resourceAsStream);
        String user = pro.getProperty("user");
        String password = pro.getProperty("password");
        System.out.println("user=" + user + "  " + "password=" + password);
    }
}
