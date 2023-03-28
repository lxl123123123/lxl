package com.atguigu.Algorithm.kmp;

//暴力匹配算法
public class ViolenceMatch {
    public static void main(String[] args) {
        //测试一把
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";
        int index = violenceMatch(str1, str2);
        System.out.println("index="+index);
    }
    //暴力匹配算法
    public static int violenceMatch(String str1,String str2){
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int str1Len = str1.length();
        int str2Len = str2.length();
        int i = 0; //i索引指向s1
        int j = 0; //j索引指向s2
        while (i < str1Len && j < str2Len){
            if (s1[i] == s2[j]){ //匹配成功
                i++;
                j++;
            }else {
                i=i-j+1;
                j=0;
            }
        }
        //判断是否匹配成功
        if (j == str2Len){
            return i - j;
        }else {
            return -1;
        }
    }
}
