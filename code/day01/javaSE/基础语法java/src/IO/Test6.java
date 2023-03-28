package IO;
//键盘输入，转换为大写

import java.util.Scanner;

public class Test6 {
    public static void main(String[] args) {
//        方式一:
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入字符串");
            String next = scanner.next();
            if ("e".equalsIgnoreCase(next) || "exit".equalsIgnoreCase(next)) {
                System.out.println("程序结束");
                break;
            }
            String s1 = next.toUpperCase();
            System.out.println(s1);
        }
//        方式二:
//        BufferedReader br = null;
//        try {
//             br = new BufferedReader(new InputStreamReader(System.in));
//            while (true){
//                System.out.println("请输入字符串");
//                String s = br.readLine();
//                if ("e".equalsIgnoreCase(s)||"exit".equalsIgnoreCase(s)){
//                    System.out.println("程序结束");
//                    break;
//                }
//                String s1 = s.toUpperCase();
//                System.out.println(s1);
//        }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (br!=null)
//                br.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
