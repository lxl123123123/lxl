package com.atguigu.Test;


import java.util.*;

public class test1 {
    public static void main(String[] args) {
        System.out.println(ch("asdfghjkllkjhgfdsa"));
        int[] arr = {3,3};
        System.out.println(sum(arr, 6));
        int[] arr1 = {4,1,2,1,2};
        System.out.println(find(arr1));
        int[] arr2 = {7,1,5,3,6,4};
        System.out.println(sell(arr2));
    }
    public static boolean ch(String str){
        String[] arr = str.split("");
        System.out.println(Arrays.toString(arr));
        boolean flag = true;
        for (int i = 0,j= arr.length-1; i < j; i++,j--) {
            if (!arr[i].equals(arr[j])){
                flag = false;
                break;
            }
        }
        if (!flag){
            return false;
        }
        return true;
    }
    public static Map<Integer,Integer> sum(int[] arr, int target){
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i] + arr[j] == target){
                    map.put(i,j);
                }
            }
        }
        return map;
    }
    public static int find(int[] arr){
        Map<Integer,Integer> map = new HashMap<>();
        for (int i : arr) {
            Integer count = map.get(i);
            if (count == null){
                map.put(i,1);
            }else {
                map.put(i,count+1);
            }
        }
        for (Map.Entry<Integer,Integer> entry : map.entrySet()){
            if (map.get(entry.getKey()) == 1){
                return entry.getKey();
            }
        }
        return -1;
    }
    public static int sell(int[] arr){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j] - arr[i] > 0){
                    list.add(arr[j]-arr[i]);
                }
            }
        }
        Collections.sort(list,Collections.reverseOrder());
        System.out.println(list);
        if (list.size() == 0){
            return 0;
        }
        return list.get(0);
    }
}
