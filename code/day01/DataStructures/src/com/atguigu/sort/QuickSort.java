package com.atguigu.sort;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {-9,78,0,23,-567,70};
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr,int left,int right){
        int l = left;//左下标
        int r = right;//右下标
        //pivot 中轴值
        int pivot = arr[(left + right) / 2];
        int temp = 0; //临时变量 用于交换
        //while循环目的是让比pivot值小的放到左边 比pivot值大的放到右边
        while (l < r){

            //在pivot左边一直找 直到找到比pivot大的(或相等)才退出
            while (arr[l] < pivot){
                l++;
            }
            //在pivot右边一直找 直到找到比pivot小的(或相等)才退出
            while (arr[r] > pivot){
                r--;
            }
            //如果l>=r 说明pivot的左右两边的值 已经按照左边全部是小于等于pivot的值
            //右边全部都是大于等于pivot的值
            if (l>=r){
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            if (arr[l] == pivot){
                r--;
            }
            if (arr[r] == pivot){
                l++;
            }

        }

        //如果l==r 必须l++ r-- 否则出现栈溢出
        if (l == r){
            l++;
            r--;
        }
        //向左递归
        if (left < r){
            quickSort(arr,left,r);
        }
        //向右递归
        if (right > l){
            quickSort(arr,l,right);
        }

    }
}
