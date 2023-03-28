package Array;

import java.nio.charset.StandardCharsets;

public class kaohe {
    public static void main(String[] args) {
        Test t= new Test();
        int a = t.strStr("hello","ll");
        System.out.println(a);
    }
}
class Test {
    public int strStr(String haystack, String needle) {
        int a = haystack.length();
        int b = needle.length();
        for (int i = 0; i < a - b + 1; i++) {
            if (haystack.substring(i, i + b).equals(needle)) {
                return i;
            }
        }
        return -1;
    }
}
