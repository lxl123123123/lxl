package com.atguigu.sort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {101,34,-4,646,2,56,119,1};
        //测试选择排序
        selectSort(arr);
    }
    //选择排序
    //时间复杂度为O(n平方)
    public static void selectSort(int[] arr){
        //使用逐步推导的方式来讲解
        for (int i = 0; i < arr.length-1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i+1; j < arr.length; j++) {
                if (min > arr[j]){
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
