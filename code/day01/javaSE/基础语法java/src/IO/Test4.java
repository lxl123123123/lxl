package IO;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Test4 {
    public static void main(String[] args) {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            File file = new File("基础语法java\\kobe.txt");
            Map<Character,Integer> hashMap = new HashMap<>();
            FileReader reader = new FileReader(file);
            br = new BufferedReader(reader);
            int read = br.read();
            while (read != -1){
             char ch = (char) read;
             if (hashMap.containsKey(ch) == false){
                 hashMap.put(ch,1);
             }else {
                 hashMap.put(ch,hashMap.get(ch) + 1);
             }
             read = br.read();
            }
            bw = new BufferedWriter(new FileWriter(new File("基础语法java\\bryant2.txt")));
            Set<Map.Entry<Character, Integer>> entries = hashMap.entrySet();
//            方式一:iterator迭代器的方式
            Iterator<Map.Entry<Character, Integer>> iterator = entries.iterator();
            while (iterator.hasNext()){
                Map.Entry<Character, Integer> entry = iterator.next();
                switch (entry.getKey()){
                        case '\t':
                            bw.write("tab键="+entry.getValue());
                            break;
                        case '\r':
                            bw.write("回车="+entry.getValue());
                            break;
                        case '\n':
                            bw.write("换行="+entry.getValue());
                            break;
                        default:
                            bw.write(entry.getKey()+"="+entry.getValue());
                            break;
                    }
                    bw.newLine();
                }
//            做法二:增强for循环的方式
//            for (Map.Entry<Character,Integer> entry:entries){
//                switch (entry.getKey()){
//                    case '\t':
//                        bw.write("tab键="+entry.getValue());
//                        break;
//                    case '\r':
//                        bw.write("回车="+entry.getValue());
//                        break;
//                    case '\n':
//                        bw.write("换行="+entry.getValue());
//                        break;
//                    default:
//                        bw.write(entry.getKey()+"="+entry.getValue());
//                        break;
//                }
//                bw.newLine();
//            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bw !=null)
                    bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
