package IO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//给文档中写入数据内容---->输出流
public class testFileWriter {
    public static void main(String[] args){
        File file = new File("基础语法java\\hello1.txt");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write("lyt is a dog!\n");
            fileWriter.write("lyt is a sbt!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter!=null)
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
