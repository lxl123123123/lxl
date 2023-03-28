package com.atguigu.Algorithm.binarySearchNoRecursion;
//算法一 --> (二分查找非递归算法)
import java.util.ArrayList;
import java.util.List;

public class BinarySearchRecur {
    public static void main(String[] args) {
        //测试一把
        int[] arr = {1,3,8,10,11,11,11,67,100};
        System.out.println(binarySearch1(arr, 11));
    }

    //二分查找的非递归实现
    public static int binarySearch(int[] arr,int findVal){
        int left = 0;
        int right = arr.length-1;
        while (left <= right){
            int mid = (left + right) / 2;
            if (findVal > arr[mid]){
                left = mid+1; //需要向右边查找
            }else if (findVal < arr[mid]){
                right = mid-1; //需要向左边查找
            }else {
                return mid;
            }
        }
        return -1;
    }

    //二分查找的非递归实现 返回全部
    public static List<Integer> binarySearch1(int[] arr,int findVal){
        int left = 0;
        int right = arr.length-1;
        while (left <= right){
            int mid = (left+right)/2;
            if (findVal > arr[mid]){
                left = mid+1;
            }else if (findVal < arr[mid]){
                right = mid-1;
            }else {
                List<Integer> list = new ArrayList<>();
                int temp = mid-1;
                while (true){
                    if (temp<0){
                        break;
                    }
                    if (arr[temp] == findVal){
                        list.add(temp);
                    }
                    temp--;
                }
                list.add(mid);
                temp = mid+1;
                while (true){
                    if (temp>arr.length-1){
                        break;
                    }
                    if (arr[temp] == findVal){
                        list.add(temp);
                    }
                    temp++;
                }
                return list;
            }
        }
        return new ArrayList<Integer>();
    }

}
