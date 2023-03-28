package IO;

import java.io.*;

public class Me {
    public static void main(String[] args) {
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        try {
            File file = new File("基础语法java\\kobe.txt");
            File file1 = new File("基础语法java\\kobe1.txt");
            FileInputStream fis = new FileInputStream(file);
            FileOutputStream fos = new FileOutputStream(file1);
            BufferedInputStream bis = new BufferedInputStream(fis);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            isr = new InputStreamReader(bis,"utf-8");
            osw = new OutputStreamWriter(bos,"utf-8");
            char[] chars = new char[1024];
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
