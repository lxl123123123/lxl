package com.atguigu;

import java.util.*;

public class test1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[][] arr = new int[n][n];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j <= i; j++) {
                arr[i][0] = 1;
                arr[i][i] = 1;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (arr[i][j] == 0){
                    arr[i][j] = arr[i-1][j-1]+arr[i-1][j];
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 0){
                    count++;
                    if (arr[i][j] == n){
                        System.out.println(count);
                        return;
                    }
                }
            }
        }
    }
}