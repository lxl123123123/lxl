package IO;
//将文件中的数据内容读入到程序中，并输出到控制台---->输入流
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class testFileReader {
    public static void main(String[] args)  {
        File file = new File("D:\\code\\day01\\javaSE\\基础语法java\\hello.txt");

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            char [] chars = new char[5];
            int read = fileReader.read(chars);//第二种构造器，每次取出好几个，返回取的个数
//            int read = fileReader.read();    第一种构造器，每次取出一个
            while (read != -1){
//                System.out.print((char) read);第一种方式
//                for (int i = 0; i < read; i++) {
//                    System.out.print(chars[i]);
//                }
                String str = new String(chars,0,read);
                System.out.println(str.toString());
//                read = fileReader.read();第一种方式
                read = fileReader.read(chars);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileReader!=null)
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
