package Collection;

import java.io.File;
import java.io.IOException;

public class Test10 {
    public static void main(String[] args) throws IOException {
        File file = new File("hello.txt");

//        boolean newFile = file.createNewFile();
//
//        System.out.println(file.getAbsoluteFile());
//        System.out.println(file.getPath());
//        System.out.println(file.getParent());
//        System.out.println(file.getName());
//        System.out.println(file.length());
//        System.out.println(file.lastModified());
//        System.out.println();
//        System.out.println(file.isDirectory());
//        System.out.println(file.isFile());
//        System.out.println(file.exists());
//        System.out.println(file.canRead());
//        System.out.println(file.canWrite());
//        System.out.println(file.isHidden());
////
//        file.delete();

        boolean mkdirs = file.mkdirs();
        if (mkdirs){
            System.out.println("创建成功");
        }else {
            System.out.println("创建失败");
        }

    }
}
