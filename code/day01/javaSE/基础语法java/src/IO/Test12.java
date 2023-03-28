package IO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

//客户端发送一个文件给服务器端，并保存在服务器端本地文件中
//服务器端
public class Test12 {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket accept = null;
        InputStream inputStream = null;
        FileOutputStream fos = null;
        OutputStream outputStream = null;
        try {
            serverSocket = new ServerSocket(6789);
            accept = serverSocket.accept();
            inputStream = accept.getInputStream();
            fos = new FileOutputStream(new File("基础语法java\\lbs2.jpg"));
            byte[] bytes = new byte[10];
            int read = inputStream.read(bytes);
            while (read != -1){
                fos.write(bytes,0,read);
                read = inputStream.read(bytes);
            }
//            反馈一句话给客户端
            outputStream = accept.getOutputStream();
            outputStream.write("美女您的图片我已收到，很漂亮！".getBytes());


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                accept.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
