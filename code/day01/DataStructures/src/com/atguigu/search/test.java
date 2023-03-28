package com.atguigu.search;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1000,100,1000,1234};
        System.out.println(seqSearch(arr,1234));
        System.out.println(seqSearch1(arr,1000));
        System.out.println(binarySearch(arr, 0, arr.length - 1, 1234));
        System.out.println(binarySearch2(arr, 0, arr.length - 1, 1000));
        System.out.println(insertValueSearch(arr, 0, arr.length - 1, 89));
        System.out.println(insertValueSearch2(arr, 0, arr.length - 1, 1000));
    }

    //线性查找
    public static int seqSearch(int[] arr,int findVal){
        for (int i = 0; i <arr.length; i++) {
            if (arr[i] == findVal){
                return i;
            }
        }
        return -1;
    }

    //线性查找
    public static List<Integer> seqSearch1(int[] arr, int findVal){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <arr.length; i++) {
            if (arr[i] == findVal){
                list.add(i);
            }
        }
        return list;
    }

    //二分查找
    public static int binarySearch(int[] arr,int left,int right,int findVal){
        if (left > right){
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal){
            return binarySearch(arr,mid + 1,right,findVal);
        }else if (findVal < midVal){
            return binarySearch(arr,left,mid - 1,findVal);
        }else {
            return mid;
        }
    }

    //二分查找
    public static List<Integer> binarySearch2(int[] arr,int left,int right,int findVal){
        if (left > right){
            return new ArrayList<>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal){
            return binarySearch2(arr,mid + 1,right,findVal);
        }else if (findVal < midVal){
            return binarySearch2(arr,left,mid - 1,findVal);
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

    //插值查找
    public static int insertValueSearch(int[] arr,int left,int right,int findVal){
        if (left > right || findVal < arr[0] || findVal > arr[arr.length-1]){
            return -1;
        }
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal){
            return insertValueSearch(arr,mid + 1,right,findVal);
        }else if (findVal < midVal){
            return insertValueSearch(arr,left,mid - 1,findVal);
        }else {
            return mid;
        }
    }

    //插值查找
    public static List<Integer> insertValueSearch2(int[] arr,int left,int right,int findVal){
        if (left > right || findVal < arr[0] || findVal > arr[arr.length-1]){
            return new ArrayList<>();
        }
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal){
            return insertValueSearch2(arr,mid + 1,right,findVal);
        }else if (findVal < midVal){
            return insertValueSearch2(arr,left,mid - 1,findVal);
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
