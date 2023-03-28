package IO;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

//客户端发送一个文件给服务器端，并保存在服务器端本地文件中
//客户端
public class Test11 {
    public static void main(String[] args) {
        Socket socket = null;
        OutputStream outputStream = null;
        FileInputStream fis = null;
        InputStream inputStream= null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"),6789);
            outputStream = socket.getOutputStream();
            fis = new FileInputStream(new File("基础语法java\\liyixin.jpg"));
            byte[] bytes = new byte[10];
            int read = fis.read(bytes);
            while (read != -1){
                outputStream.write(bytes,0,read);
                read = fis.read(bytes);
            }
            socket.shutdownOutput();
//            接收服务器端发来的反馈
             inputStream = socket.getInputStream();
             byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bytes1 = new byte[10];
            int read1 = inputStream.read(bytes1);
            while (read1!=-1){
                byteArrayOutputStream.write(bytes1,0,read1);
                read1 = inputStream.read(bytes1);
            }
            System.out.println(byteArrayOutputStream);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
