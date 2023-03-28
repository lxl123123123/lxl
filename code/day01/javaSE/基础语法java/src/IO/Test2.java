package IO;
//图片的复制，用字节流来处理
//.txt .c .java这样的文件类型用字符流(FileReader FileWriter来处理)
//.jpg .avi .mp3 .mp4这样的文件用字节流(FileInputStream FileOutputStream来处理)
//这四个统称为节点流
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test2 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            File file = new File("D:\\code\\day01\\javaSE\\基础语法java\\liyixin.jpg");
            File file1 = new File("D:\\code\\day01\\javaSE\\基础语法java\\lxl.jpg");

            inputStream = new FileInputStream(file);
            outputStream = new FileOutputStream(file1);

            byte[] bytes = new byte[5];
            int read = inputStream.read(bytes);
            while (read != -1){
//                加密操作
//                for (int i = 0; i <read; i++) {
//                    bytes[i] = (byte) (bytes[i]^5);
//                }
                outputStream.write(bytes,0,read);
                read = inputStream.read(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (outputStream != null)
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        long sum = end-start;
        System.out.println("复制成功耗时为"+ sum);
    }
}
