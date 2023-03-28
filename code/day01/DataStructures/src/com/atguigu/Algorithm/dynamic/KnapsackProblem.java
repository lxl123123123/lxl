package com.atguigu.Algorithm.dynamic;
//算法三 --> (动态规划算法)
//背包问题 将n个物品(重量为weight 价格为value)加入到承重为m的背包中 想办法求得最优解(在不超过总承重m的情况下使得value最大)
//01背包(每个物品只能放一次) 完全背包(一件物品可以放多次)
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1,4,3}; //保存物品的重量
        int[] val = {1500,3000,2000}; //保存物品的价值
        int m = 4; //背包的容量
        int n = val.length; //物品的个数
        //创建二维数组
        int[][] v = new int[n+1][m+1];
        //为了记录放入商品的情况，我们定一个二维数组
        int[][] path = new int[n+1][m+1];

        //初始化第一行和第一列 这里可以不用处理 在本程序中 默认就是0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0; //将第一列设置为0
        }
        for (int i = 0; i <v[0].length; i++) {
            v[0][i] = 0; //将第一行设置为0
        }
        //根据前面得到的公式来动态规划处理
        for (int i = 1; i <v.length; i++) { //不处理第一行
            for (int j = 1; j <v[0].length; j++) { //不处理第一列
                if (w[i-1] > j){ //因为我们程序i是从1开始的 因此原来公式中的w[i] 修改为w[i-1]
                    v[i][j] = v[i-1][j];
                }else {
                    //原公式为v[i][j] = Math.max(v[i-1][j],val[i]+v[i-1][j-w[i]]) 原理同上 i是从1开始的 所以原公式要-1
//                    v[i][j] = Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
                    if (v[i-1][j] < val[i-1]+v[i-1][j-w[i-1]]){
                        v[i][j] = val[i-1]+v[i-1][j-w[i-1]];
                        path[i][j] = 1;
                    }else {
                        v[i][j] = v[i-1][j];
                    }
                }
            }
        }
        //输出一把二维数组 看看情况
        for (int i = 0; i <v.length; i++) {
            for (int j = 0; j <v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("===============================");
        //遍历path
        int i = path.length-1; //行的最大下标
        int j = path[0].length-1; //列的最大下标
        while (i > 0 && j > 0){
            if (path[i][j] == 1){
                System.out.printf("第%d个商品放入背包\n",i);
                j-=w[i-1];
            }
            i--;
        }
    }
}
