package com.atguigu.sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int arr[] = {4,3,2,1};
        //测试冒泡排序
        bubble(arr);
    }

    //将前面的冒泡排序算法封装成一个方法
    public static void bubble(int[] arr){
        //第一趟排序，就是将最大的数排在最后
        //第二趟排序，就是将倒数第二大的数排在倒数第二个
        //第三趟排序，就是将倒数第三大的数排在倒数第三个
        //第四趟排序，就是将倒数第四大的数排在倒数第四个
        //冒泡排序的时间复杂度为O(n平方)
        int temp = 0;
        boolean flag = false;
        int count = 0;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-i-1; j++) {
                if (arr[j]>arr[j+1]){
                    flag = true;
                    count++;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            if (!flag){
                break;
            }else {
                flag = false; //重置flag 进行下次判断
            }
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(count);
    }
}
