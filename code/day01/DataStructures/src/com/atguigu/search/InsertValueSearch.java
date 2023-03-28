package com.atguigu.search;


//注意使用插值查找的前提是数组必须有序
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i+1;
        }
        System.out.println(insertValueSearch(arr, 0, arr.length - 1, 101));
    }

    //插值查找
    public static int insertValueSearch(int[] arr,int left,int right,int findVal){
        //注意findVal < arr[0] || findVal > arr[arr.length-1]必须有
        //否则我们算出来的mid可能越界
        if (left > right || findVal < arr[0] || findVal > arr[arr.length-1]){
            return -1;
        }
        //求出mid 自适应的写法 递归时比二分查找快 会少几次递归
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal){ //向右递归
            return insertValueSearch(arr,mid+1,right,findVal);
        }else if (findVal < midVal){ //向左递归
            return insertValueSearch(arr,left,mid-1,findVal);
        }else {
            return mid;
        }
    }
}
