package com.atguigu.search;

import java.util.ArrayList;
import java.util.List;

public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1,9,11,-1,34,89,1};
        System.out.println(seqSearch(arr,1));
    }
    public static List<Integer> seqSearch(int[] arr, int value){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <arr.length; i++) {
            if (arr[i] == value){
                list.add(i);
            }
        }
        return list;
    }
}
