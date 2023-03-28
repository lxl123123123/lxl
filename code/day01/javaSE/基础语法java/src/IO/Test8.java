package IO;
//如何实例化InetAddress   两个方法，getByName(ip地址  域名),getLocalHost()
//两个常用方法，getHostName(),getHostAddress()
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Test8 {
    public static void main(String[] args) {
        InetAddress byName = null;
        InetAddress localHost = null;
        try {
            byName = InetAddress.getByName("www.baidu.com");
            localHost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println(byName);
        System.out.println(localHost);
        System.out.println("******");
        System.out.println(byName.getHostName());
        System.out.println(byName.getHostAddress());
        System.out.println("******");
        System.out.println(localHost.getHostName());
        System.out.println(localHost.getHostAddress());
    }
}
