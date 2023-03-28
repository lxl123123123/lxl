package IO;
//使用缓冲流，BufferedInputStream,BufferedOutputStream,
// BufferedReader,BufferedWriter,将节点流包装起来，从而加快流的效率，加快文件操作的速度
import java.io.*;

public class Test3 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            File file = new File("基础语法java\\liyixin.jpg");
            File file1 = new File("基础语法java\\lxh.jpg");

            FileInputStream fis = new FileInputStream(file);
            FileOutputStream fos = new FileOutputStream(file1);

            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            byte[] bytes = new byte[5];
            int read = bis.read(bytes);
            while (read != -1){
                bos.write(bytes,0,read);
                read = bis.read(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null)
                    bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bos != null)
                    bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        long sum = end-start;
        System.out.println("复制成功耗时为"+ sum);
    }
}
