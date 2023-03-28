package com.atguigu.sort;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4,6,8,5,9};
        heap(arr);
    }
    //编写一个堆排序的方法
    public static void heap(int[] arr){
        int temp = 0;
        System.out.println("堆排序!!");

        //把刚开始传进来的无序数组先变成一个大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr,i, arr.length);
        }
        //将第一个值和最后一个值交换 将最大值(第一个)交换到最后
        for (int j = arr.length-1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            //每次交换完之后再次将数组构建成一个大顶堆
            adjustHeap(arr,0,j);
        }
        System.out.println(Arrays.toString(arr));
    }
    //将一个数组(二叉树) 调整成一个大顶堆
    /**
     *
     * @param arr 待调整的数组
     * @param i 表示非叶子节点在数组中索引
     * @param length 表示对多少个元素继续调整 length在逐渐减少
     */
    public static void adjustHeap(int[] arr,int i,int length){
        int temp = arr[i]; //先取出当前非叶子节点的值 保存在临时变量中
        for (int k = i*2+1; k <length; k=k*2+1) {
            if (k+1<length && arr[k] < arr[k+1]){ //判断左子节点是否小于右子节点
                k++; //k指向右子节点
            }
            if (arr[k] > temp){ //如果子节点大于父节点
                arr[i] = arr[k];  //把子节点的值交给父节点
                i = k; //同时将i置为k的值
            }else {
                break;
            }
        }
        //当for循环结束之后再交换
        arr[i] = temp; //把父节点的值交给子节点
    }

}
