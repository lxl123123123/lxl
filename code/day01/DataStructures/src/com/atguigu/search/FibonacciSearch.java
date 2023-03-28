package com.atguigu.search;

import java.util.Arrays;

public class FibonacciSearch {

    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1234};
        System.out.println(fibSearch(arr,88));
    }

    //斐波那契查找
    //因为后面mid=low+F(k-1)-1 需要使用到斐波那契数列 所以我们先获取到一个斐波那契数列
    //非递归方式得到一个斐波那契数列
    public static int[] fib(){
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i <maxSize; i++) {
            f[i] = f[i-1] + f[i-2];
        }
        return f;
    }

    //编写斐波那契查找算法
    public static int fibSearch(int[] arr,int findVal){
        int left = 0;
        int right = arr.length-1;
        int k = 0; //表示斐波那契分割数值的下标
        int mid = 0; //存放mid值
        int f[] = fib(); //获取斐波那契数列
        while (right > f[k] - 1){
            k++;
        }
        int[] temp = Arrays.copyOf(arr,f[k]);

        for (int i = right+1; i <temp.length; i++) {
            temp[i] = arr[right];
        }
        while (left <= right){
            mid = left + f[k-1] - 1;
            if (findVal < temp[mid]){
                right = mid - 1;
                k--;
            }else if (findVal > temp[mid]){
                left = mid + 1;
                k-=2;
            }else {
                if (mid <= right){
                    return mid;
                }else {
                    return right;
                }
            }
        }
        return -1;
    }
}
