package com.atguigu.Algorithm.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

//算法五 --> (贪心算法)
//有5个广播台 要求选取最少的广播台 使得覆盖所有的地区
public class GreedyAlgorithm {
    public static void main(String[] args) {
        //创建广播电台，放入到map
        HashMap<String, HashSet<String>> broadCast = new HashMap<>();
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");
        broadCast.put("k1",hashSet1);
        broadCast.put("k2",hashSet2);
        broadCast.put("k3",hashSet3);
        broadCast.put("k4",hashSet4);
        broadCast.put("k5",hashSet5);

        //allAreas存放所有的地区
        HashSet<String> allAreas = new HashSet<>();
        for (HashSet<String> value : broadCast.values()) {
            allAreas.addAll(value);
        }
        //创建ArrayList 存放我们的电台集合
        ArrayList<String> list = new ArrayList<>();
        //定义一个临时的集合 存放在遍历过程中电台和allAreas的交集
        HashSet<String> tempSet = new HashSet<>();
        //定义maxKey 保存在一次遍历中 能够覆盖的最多的未覆盖地区的key
        String maxKey;
        //对应(之前)最大的交集中的城市个数
        int maxSize;
        while (allAreas.size() > 0) { //如果我们的allAreas不为0 则表示还没有覆盖到所有地区
            maxKey = null; //重置maxKey
            maxSize = 0; //重置maxSize
            for (String key : broadCast.keySet()) {
                tempSet.clear(); //清空临时交集集合
                HashSet<String> areas = broadCast.get(key);
                tempSet.addAll(areas);
                tempSet.retainAll(allAreas);
                //tempSet.size() > maxSize --> 体现贪心算法的地方 每次选取最优解
                //本题中每次都选取交集数最多的k值 这样就可以用最少的次数满足遍历完所有的城市
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > maxSize)) {
                    maxKey = key;
                    maxSize = tempSet.size();
                }
            }
            if (maxKey != null) {
                list.add(maxKey);
                allAreas.removeAll(broadCast.get(maxKey));
            }
        }
        System.out.println("得到的选择结果是"+list);
    }
}
