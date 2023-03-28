package com.atguigu.sort;

import java.util.Arrays;

public class test {
    public static void main(String[] args) {
//        int[] arr = {101,76,23,34,-5,-1,119,1};
        int[] arr = {101,76,23,34,119,1};
        Bubble(arr);
        select(arr);
        insert(arr);
        shell1(arr);
        shell2(arr);
        quick(arr,0,arr.length-1);
        System.out.println("quick"+Arrays.toString(arr));
        mergeSort(arr,0, arr.length-1,new int[arr.length]);
        System.out.println("merge"+Arrays.toString(arr));
        radixSort(arr);
    }
//    int[] arr = {101,76,23,34,-5,-1,119,1};
    public static void Bubble(int[] arr){
        int temp = 0;
        boolean flag = false;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-i-1; j++) {
                if (arr[j]>arr[j+1]){
                    flag =true;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            if (flag == false){
                break;
            }else {
                flag = false;
            }
        }
        System.out.println("bubble"+Arrays.toString(arr));
    }
    //    int[] arr = {101,76,23,34,-5,-1,119,1};
    public static void select(int[] arr){
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
        System.out.println("select"+Arrays.toString(arr));
    }
    //    int[] arr = {101,76,23,34,-5,-1,119,1};
    public static void insert(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            int insertIndex = i;
            int insertVal = arr[i+1];
            while (insertIndex >= 0 && insertVal < arr[insertIndex]){
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex + 1] = insertVal;
        }
        System.out.println("insert"+Arrays.toString(arr));
    }
    //    int[] arr = {101,76,23,34,-5,-1,119,1};
    public static void shell1(int[] arr){
        int temp = 0;
        for (int gap = arr.length/2; gap>0; gap/=2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i-gap; j >=0; j-=gap) {
                    if (arr[j] > arr[j+gap]){
                        temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }
            }
        }
        System.out.println("shell1"+Arrays.toString(arr));
    }
    //    int[] arr = {101,76,23,34,-5,-1,119,1};
    public static void shell2(int[] arr){
        for (int gap = arr.length/2; gap >0; gap/=2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j-gap]){
                    while (j-gap >=0 && temp<arr[j-gap]){
                        arr[j] = arr[j-gap];
                        j-=gap;
                    }
                    arr[j] = temp;
                }
            }
        }
        System.out.println("shell2"+Arrays.toString(arr));
    }

//    int[] arr = {101,76,23,34,-5,-1,119,1};
    public static void quick(int[] arr,int left,int right){
        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2];
        int temp = 0;
        while (l < r){

            while (arr[l] < pivot){
                l++;
            }

            while (arr[r] > pivot){
                r--;
            }

            if (l>=r){
                break;
            }

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
        if (l==r){
            l++;
            r--;
        }
        //递归
        if (left<r){
            quick(arr,left,r);
        }
        if (l<right){
            quick(arr,l,right);
        }
    }
    public static void mergeSort(int[] arr,int left,int right,int[] temp){
        if (left<right){
            int mid = (left+right)/2;
            mergeSort(arr,left,mid-1,temp);
            mergeSort(arr,mid+1,right,temp);
            merge(arr,left,mid,right,temp);
        }
    }

    //    int[] arr = {101,76,23,34,-5,-1,119,1};
    public static void merge(int[] arr,int left,int mid,int right,int[] temp){
        int i = left;
        int j = mid+1;
        int t = 0;
        while (i <= mid && j <= right){
            if (arr[i]<=arr[j]){
                temp[t] = arr[i];
                t++;
                i++;
            }else {
                temp[t] = arr[j];
                t++;
                j++;
            }
        }
        while (i <= mid){
            temp[t] = arr[i];
            t++;
            i++;
        }
        while (j <= right){
            temp[t] = arr[j];
            t++;
            j++;
        }
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right){
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }
    //    int[] arr = {101,76,23,34,-5,-1,119,1};
    public static void radixSort(int[] arr){
        int max = arr[0];
        for (int i = 1; i <arr.length; i++) {
            if (arr[i] > max){
                max = arr[i];
            }
        }
        int maxLength = (max+"").length();
        int[][] bucket = new int[10][arr.length];
        int[] bucketElementCounts = new int[10];

        for (int i = 0,n=1; i <maxLength; i++,n*=10) {
            for (int j = 0; j <arr.length; j++) {
                int digit = arr[j] / n % 10;
                bucket[digit][bucketElementCounts[digit]] = arr[j];
                bucketElementCounts[digit]++;
            }
            int index = 0;
            for (int k = 0; k <bucketElementCounts.length; k++) {
                if (bucketElementCounts[k] != 0){
                    for (int l = 0; l <bucketElementCounts[k]; l++) {
                        arr[index++] = bucket[k][l];
                    }
                }
                bucketElementCounts[k] = 0;
            }
        }
        System.out.println("radixSort"+Arrays.toString(arr));
    }

}
