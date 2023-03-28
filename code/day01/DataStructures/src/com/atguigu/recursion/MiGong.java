package com.atguigu.recursion;

public class MiGong {
    public static void main(String[] args) {
        //1.创建迷宫（二维数组）
        //2.规定 map数组的元素值：0 表示可以走 1 表示有障碍物
        int[][] map = new int[8][7];
        //3.将上面和最下面的一行全设为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //4.将最右边一列和最左边一列设为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;

        //输出当前的迷宫
        System.out.println("======当前地图======");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");//输出一行
            }
            System.out.println();
        }
        //使用findWay 老鼠出迷宫
        Path p = new Path();
        p.findWay(map,1,1);
        System.out.println("\n======找路情况如下=====\n");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");//输出一行
            }
            System.out.println();
        }
    }
}

class Path{
    //使用递归回溯解决老鼠出迷宫
    //1.findWay方法 确定出迷宫的路径
    //2.如果找到，就返回true, 否则返回false
    //3.map 就是二维数组，即表示迷宫
    //4.i,j 就是老鼠的位置，初始化位置为（1,1）
    //5.map数组的各个值的含义 1 表示障碍物 2 表示可以走 3 表示走过但走不通的路
    //6.当 map（6,5）= 2 时表示找到通路 结束找路 否则则一直找
    //7.老鼠走迷宫的策略 下 -> 右 -> 上 -> 左
    public boolean findWay(int[][] map , int i, int j){
        if( map[6][5] == 2 )//说明已经找到：递归终止条件
        {
            return true;
        }else{
            if(map[i][j] == 0){//当前位置为零，可走
                //我们假定可以走通
                map[i][j] = 2;
                System.out.println( "(" + i + "," + j + ")");
                //使用策略开始测试，此道路是否真的可以走通
                //下 -> 右 -> 上 -> 左
                if(findWay(map, i + 1, j)){//先尝试走下
                    return true;
                }else if(findWay(map, i, j + 1)){//走右
                    return true;
                }else if(findWay(map, i - 1, j)){//走上
                    return true;
                }else if(findWay(map, i, j - 1)){//走左
                    return true;
                }else {
                    map[i][j] = 3;
                    return false;
                }
            }else{//map[i][j] = 1 , 2 , 3
                return false;
            }
        }
    }
}