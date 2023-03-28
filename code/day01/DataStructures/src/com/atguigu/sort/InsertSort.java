package com.atguigu.sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101,76,23,34,-5,-1,119,1};
        //测试插入排序
        insert(arr);
    }

    //插入排序
    public static void insert(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            int insertVal = arr[i+1];
            int insertIndex = i;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]){
                arr[insertIndex +1] = arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex + 1] = insertVal;
        }
        System.out.println(Arrays.toString(arr));
    }
}
