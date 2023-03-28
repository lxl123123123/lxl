package Reflection.java8;

//Stream API的说明
//实例化(三种方式)，中间操作(筛选与切片，映射，排序)，终止操作(匹配与查找，归约，收集)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Test1 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(2);
        list.add(4);
        list.add(1);
        Object[] objects = list.toArray();
        Arrays.sort(objects);
        for (Object o : objects){
            System.out.println(o);
        }
        System.out.println();

        Stream<Integer> stream = list.stream();
//        从流中排除某些元素 filter
        stream.filter(e -> e>2).forEach(System.out::println);
        System.out.println();
//        截断流，使其元素不超过给定数量 limit
        list.stream().limit(3).forEach(System.out::println);
        System.out.println();
//        跳过元素，返回跳过了前n个元素的流，如果不足n个，则返回一个空流
        list.stream().skip(2).forEach(System.out::println);
        System.out.println();
//      筛选，去掉流中重复的元素
        Arrays.stream(objects).distinct().forEach(System.out::println);
        System.out.println();
//        max是终止操作之一，现在返回最大的值
        Optional<Integer> max = list.stream().max(Integer::compareTo);
        System.out.println(max);
    }
}
