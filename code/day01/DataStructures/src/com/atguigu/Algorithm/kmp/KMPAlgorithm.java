package com.atguigu.Algorithm.kmp;

import java.util.Arrays;

//算法四 --> (KMP匹配算法)
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        System.out.println(Arrays.toString(kmpNext(str2)));
        System.out.println(kmpSearch(str1, str2, kmpNext(str2)));
    }

    public static int kmpSearch(String str1,String str2,int[] next){
        //遍历
        for (int i = 0,j=0; i <str1.length(); i++) {
            while (j>0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j-1];
            }
            if (str1.charAt(i) == str2.charAt(j)){
                j++;
            }
            if (j == str2.length()){
                return i-j+1;
            }
        }
        return -1;
    }

    //获取到一个字符串的(子串)部分匹配值
    public static int[] kmpNext(String dest){
        //创建一个要返回的数组 保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0;
        for (int i = 1,j=0; i <dest.length(); i++) {
            while (j>0 && dest.charAt(i) != dest.charAt(j)){
                j = next[j-1];
            }
            if (dest.charAt(i) == dest.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
