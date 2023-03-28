package com.atguigu.recursion;

public class Queue8 {
    //定义一个max 表示有多少个皇后
    int max = 8;
    //定义一个数组，保存皇后放置的位置的结果 ，比如arr = [0,4,7,5,2,6,1,3]
    int[] array = new int[max];
    static int count = 0;
    static int judgeCount = 0;
    public static void main(String[] args) {
        //测试一把，8皇后是否正确
        Queue8 queue = new Queue8();
        queue.check(0);
        System.out.printf("一共有%d种解法\n",count);
        System.out.printf("一共判断冲突%d次",judgeCount);
    }

    //编写一个方法放置第n个皇后
    //特别注意，每一次调用check都会有for循环
    // 也即存在回溯(静下心来仔细想 分析循环代码 如果第8行也放好了（即一种摆法 直接打印），
    // 并且return 到第7行，第7行好比说原来在i=2找到了，现在i继续++，再看i=3的时候咋样 ，再找到
    // 其他摆法，也即回溯)
    private void check(int n){
        if (n == max){ //n=8 因为n从0开始 实际上已经放置了8个皇后了，那么直接打印
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后n，放到该行的第一列
            array[n] = i;
            //判断当放置第n个皇后到第1列时，是否冲突
            if (judge(n)){
                //接着放n+1个皇后，即开始递归
                check(n+1);
            }
            //如果冲突 那么就把这个皇后往后移一下，即i++(列++)继续执行循环
        }
    }

    //查看当我们放置第n个皇后，就去检测该皇后是否和前面已经摆放的皇后冲突 其中n表示第n个皇后
    private boolean judge(int n){
        judgeCount++;
        //说明
        //1.array[i]==array[n] 判断是否在同一列
        //2.底下的绝对值式子实际上是一个等腰直角三角形，来判断是否在一个对角线上，如果在那么两条直角边肯定相等
        //3.没有必要判断是否在同一行，因为我们规定8个皇后不在同一行 n也是人为传进去的 且n一直在递增
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }

    //写一个方法，可以将皇后摆放的位置输出
    private void print(){
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }
}
