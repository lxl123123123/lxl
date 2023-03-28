package com.atguigu.sort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {53,3,542,748,14,214};
        radixSort(arr);
    }
    //基数排序(桶排序升级版)
    public static void radixSort(int[] arr){

        int max = arr[0];
        //得到数组中 位数最大的数
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max){
                max = arr[i];
            }
        }
        int maxLength = (max+"").length(); //最大有几位

        int[][] bucket = new int[10][arr.length]; //定义10个桶
        //bucketElementCounts[0]表示0号桶中一共放了多少个元素
        int[] bucketElementCounts = new int[10];

        for (int i = 0,n=1; i <maxLength; i++,n*=10) {
            for (int j = 0; j < arr.length; j++) {
                int digitOfElement = arr[j] / n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            int index = 0; //按照这个桶的顺序(一维数组的下标取出数据 放入原来的数组中)
            for (int k = 0; k < bucketElementCounts.length; k++) { //k为每一个桶
                if (bucketElementCounts[k] != 0){
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        arr[index++] = bucket[k][l];
                    }
                }
                bucketElementCounts[k] = 0;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
