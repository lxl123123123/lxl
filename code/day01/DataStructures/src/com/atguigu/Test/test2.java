package com.atguigu.Test;

//找出第三大的数
import java.util.Arrays;

public class test2 {
    public static void main(String[] args) {
        int[] arr = {3,2,1,53,24,-54,-1,243};
//        int[] arr = {1,67};
        System.out.println(test(arr));
    }
    public static int test(int[] arr){
        int temp = 0;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-i-1; j++) {
                if (arr[j] < arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
        int count = 0;
        boolean flag = true;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j <i; j++) {
                if (arr[i] == arr[j]){
                    flag = false;
                    break;
                }
            }
            if (flag){
                count++;
            }
            if (count == 3){
                return arr[i];
            }
            flag = true;
        }
        if (count < 3) {
            return arr[0];
        }
        return -1;
    }
}
