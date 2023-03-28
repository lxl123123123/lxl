package IO;

//转换流的应用

import java.io.*;

public class Test5 {
    public static void main(String[] args) {
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        try {
            File file = new File("基础语法java\\kobe.txt");
            File file1 = new File("基础语法java\\bryant1.txt");
            FileInputStream fis = new FileInputStream(file);
            FileOutputStream fos = new FileOutputStream(file1);
            isr = new InputStreamReader(fis,"UTF-8");
            osw = new OutputStreamWriter(fos,"gbk");
            char[] chars = new char[10];
            int read = isr.read(chars);
            while (read != -1){
                osw.write(chars,0,read);
                read = isr.read(chars);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (isr != null)
                    isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (osw != null)
                    osw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
