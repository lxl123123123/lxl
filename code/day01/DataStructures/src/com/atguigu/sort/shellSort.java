package com.atguigu.sort;

import java.util.Arrays;

public class shellSort {
    public static void main(String[] args) {
        int[] arr = {8,9,1,7,2,3,5,4,6,0};
        //测试希尔排序
        shell(arr);
        shellSort2(arr);
    }

    //希尔排序 --> 交换法
    public static void shell(int[] arr){

        int temp = 0;
        for (int gap = arr.length / 2; gap > 0; gap/=2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >=0 ; j-=gap) {
                    if (arr[j] > arr[j+gap]){
                        temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    //希尔排序优化 --> 移位法
    public static void shellSort2(int[] arr){
        for (int gap = arr.length / 2; gap > 0 ; gap/=2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j-gap]){
                    while (j-gap >= 0 && temp<arr[j-gap]){
                        //移动
                        arr[j] = arr[j-gap];
                        j-=gap;
                    }
                    //当退出while循环后 就给temp找到插入的为位置
                    arr[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
