package com.atguigu.Algorithm.dac;
//算法二 --> (分治算法,分而治之)
public class HanOiTower {
    public static void main(String[] args) {
        hanoiTower(5,'A','B','C');
    }

    //汉诺塔的移动方法
    //使用分治算法
    public static void hanoiTower(int num,char a,char b,char c){
        //如果只有一个盘
        if (num == 1){
            System.out.println("第1个盘从" + a + "->" + c);
        }else {
            //如果我们有n>=2的情况 我们总是可以看作是两个盘 1.最下面的一个盘 2.上面的所有盘
            //1.先把最上面的所有盘由A->B 移动过程会使用到C
            hanoiTower(num-1,a,c,b);
            //把最下面的盘由A->C
            System.out.println("第" + num + "个盘从" + a + "->" + c);
            //3.把B塔的所有盘由B->C
            hanoiTower(num-1,b,a,c);
        }
    }
}
