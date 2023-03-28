package IO;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//使用FileReader和FileWriter实现文本文件的复制
public class Test1 {
    public static void main(String[] args) {
//        1.提供file类的对象，指明写到的文件
        File file = new File("基础语法java\\hello.txt");
        File file1 = new File("基础语法java\\hello2.txt");
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
//        2.提供FileReader和FileWriter的对象，用于数据的写入写出
            fileReader = new FileReader(file);
            fileWriter = new FileWriter(file1);
//        3.写入写出的具体操作
            char [] chars = new char[5];
            int read = fileReader.read(chars);
            while (read != -1){
                fileWriter.write(chars,0,read);
                read = fileReader.read(chars);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //  4.关闭流
            try {
                if (fileReader != null)
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fileWriter != null)
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
