package com.atguigu;
//letCode三数之和四数之和 不能暴力求解否则超时 排序+双指针+去重

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test {
    public static void main(String[] args) {
        test t1 = new test();
        int[] nums = {0,1,1};
        int[] nums1 = {2,2,2,2,2};
        System.out.println(t1.threeSum(nums));
        System.out.println(t1.fourSum(nums1, 8));
    }
    //三数之和
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            int left = i+1;
            int right = nums.length-1;
            while (left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0){
                    right--;
                }else if (sum < 0){
                    left++;
                }else {
                    list.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    while (left < right && nums[left] == nums[left+1]){
                        left++;
                    }
                    while (left < right && nums[right] == nums[right-1]){
                        right--;
                    }
                    left++;
                    right--;
                }
            }
        }
        return list;
    }
    //四数之和
    public List<List<Integer>> fourSum(int[] nums,int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-3; i++) {
            //去重i
            if (i>0 && nums[i] == nums[i-1]){
                continue;
            }
            if ((long) nums[i] + nums[i+1]+nums[i+2]+nums[i+3] > target){
                break;
            }
            if ((long) nums[i] + nums[nums.length-1]+nums[nums.length-2]+nums[nums.length-3] <target){
                continue;
            }
            for (int j = i+1; j < nums.length-2; j++) {
                //去重j
                if (j>i+1 && nums[j] == nums[j-1]){
                    continue;
                }
                if ((long) nums[i]+nums[j]+nums[j+1]+nums[j+2] >target){
                    break;
                }
                if ((long) nums[i]+nums[j]+nums[nums.length-1]+nums[nums.length-2] < target){
                    continue;
                }
                int left = j+1;
                int right = nums.length-1;
                while (left<right){
                    long sum = (long)nums[i]+nums[j]+nums[left]+nums[right];
                    if (sum>target){
                        right--;
                    } else if (sum < target) {
                        left++;
                    }else {
                        list.add(Arrays.asList(nums[i],nums[j],nums[left],nums[right]));
                        //去重left
                        while (left<right && nums[left] == nums[left+1]){
                            left++;
                        }
                        //去重right
                        while (left<right && nums[right] == nums[right-1]){
                            right--;
                        }
                        left++;
                        right--;
                    }
                }
            }
        }
        return list;
    }
}
