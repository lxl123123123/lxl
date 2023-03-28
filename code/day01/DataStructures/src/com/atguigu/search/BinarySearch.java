package com.atguigu.search;

import java.util.ArrayList;
import java.util.List;

//注意使用二分查找的前提是数组必须有序
public class BinarySearch {
    public static void main(String[] args) {
//        int[] arr = {1,8,10,89,1000,1234};
        int[] arr = {1,8,10,89,1000,1000,100,1000,1234};
        System.out.println(binarySearch(arr, 0, arr.length - 1, 1000));
        List<Integer> list = binarySearch2(arr, 0, arr.length - 1, 1000);
        System.out.println(list);
    }

    /**
     *
     * @param arr 数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param findVal 要查找的值
     * @return 如果找到就返回下标 如果没有找到 就返回-1
     */
    //二分查找算法
    public static int binarySearch(int[] arr,int left,int right,int findVal){
        if (left > right){ //如果left大于了right 就结束递归 说明没找到
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal){ //向右递归
            return binarySearch(arr,mid+1,right,findVal);
        }else if (findVal < midVal){ //向左递归
            return binarySearch(arr,left,mid-1,findVal);
        }else {
            return mid;
        }
    }

    //思考题  int[] arr = {1,8,10,89,1000,1000,1000,1234} 当一个数组中 有多个相同数值时
    //如何将所有的数值都查找到 比如这里的1000
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal){
        if (left > right){ //如果left大于了right 就结束递归 说明没找到
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal){ //向右递归
            return binarySearch2(arr,mid+1,right,findVal);
        }else if (findVal < midVal){ //向左递归
            return binarySearch2(arr,left,mid-1,findVal);
        }else {
            List<Integer> list = new ArrayList<>();
            int temp = mid - 1;
            while (true){
                if (temp < 0){
                    break;
                }
                if (arr[temp] == findVal){
                    list.add(temp);
                }
                temp-=1;
            }
            list.add(mid);
            temp = mid + 1;
            while (true){
                if (temp > arr.length-1){
                    break;
                }
                if (arr[temp] == findVal){
                    list.add(temp);
                }
                temp+=1;
            }
            return list;
        }
    }

}
