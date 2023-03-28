package com.atguigu.Algorithm.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

//算法十 --> (马踏棋盘算法) 骑士周游问题
public class HorseChessboard {
    private static int X; //棋盘的列数
    private static int Y; //棋盘的行数
    private static boolean visited[]; //创建一个数组 标记棋盘上的各个位置是否被访问过
    private static boolean finished; //如果为true 表示成功
    public static void main(String[] args) {
        //测试骑士周游算法是否正确
        X = 8;
        Y = 8;
        int row = 1; //马儿初始位置的行 从1开始编写
        int column = 1; //马儿初始位置的列 从1开始编写
        //创建棋盘
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X * Y];
        long start = System.currentTimeMillis();
        traversalChessboard(chessboard,row-1,column-1,1);
        long end = System.currentTimeMillis();
        System.out.println("共耗时" + (end-start) + "毫秒");
        //输出棋盘
        for (int[] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 完成骑士周游算法
     * @param chessboard 棋盘
     * @param row 马儿当前位置的行 从0开始
     * @param column 马儿当前位置的列 从0开始
     * @param step 是第几步 初始位置就是第一步
     */
    public static void traversalChessboard(int[][] chessboard,int row,int column,int step){
        chessboard[row][column] = step; //设置当前位置为第一步
        visited[row * X + column] = true; //设置当前位置为已访问
        //获取当前位置可以走的下一个位置的集合
        ArrayList<Point> ps = next(new Point(column, row));
        //对ps进行排序,排序的规则就是对ps的所有的Point对象的下一步的位置的数目,进行非递减排序
        sort(ps);
        //遍历ps
        while (!ps.isEmpty()){
            Point p = ps.remove(0); //取出下一个可以走的位置
            //判断这个位置是否访问过
            if (!visited[p.y * X + p.x]){ //说明还没有被访问过
                traversalChessboard(chessboard,p.y,p.x,step+1);
            }
        }
        //判断马儿是否完成任务 如果没有完成 将整个棋盘置为0
        if (step < X * Y && !finished){
            chessboard[row][column] = 0;
            visited[row * X + column] = false;
        }else {
            finished = true;
        }
    }
    //根据当前位置(Point对象 就是一个点对象 对应棋盘上的一个点) 计算马儿还能走哪些位置(Point) 并放入到一个集合中(ArrayList) 最多有8个位置
    public static ArrayList<Point> next(Point curPoint){
        ArrayList<Point> ps = new ArrayList<>();
        Point p1 = new Point();
        //判断马儿能否走5这个位置  注意这里面x为列 y为行
        if ((p1.x = curPoint.x-2) >= 0 && (p1.y = curPoint.y-1) >= 0){
            ps.add(new Point(p1));
        }
        //判断马儿能否走6这个位置
        if ((p1.x = curPoint.x-1) >= 0 && (p1.y = curPoint.y-2) >= 0){
            ps.add(new Point(p1));
        }
        //判断马儿能否走7这个位置
        if ((p1.x = curPoint.x+1) < X && (p1.y = curPoint.y-2) >= 0){
            ps.add(new Point(p1));
        }
        //判断马儿能否走0这个位置
        if ((p1.x = curPoint.x+2) < X && (p1.y = curPoint.y-1) >= 0){
            ps.add(new Point(p1));
        }
        //判断马儿能否走1这个位置
        if ((p1.x = curPoint.x+2) < X && (p1.y = curPoint.y+1) < Y){
            ps.add(new Point(p1));
        }
        //判断马儿能否走2这个位置
        if ((p1.x = curPoint.x+1) < X && (p1.y = curPoint.y+2) < Y){
            ps.add(new Point(p1));
        }
        //判断马儿能否走3这个位置
        if ((p1.x = curPoint.x-1) >= 0 && (p1.y = curPoint.y+2) < Y){
            ps.add(new Point(p1));
        }
        //判断马儿能否走4这个位置
        if ((p1.x = curPoint.x-2) >= 0 && (p1.y = curPoint.y+1) < Y){
            ps.add(new Point(p1));
        }
        return ps;
    }
    //根据当前这一步的所有的下一步的选择位置 进行非递减排序
    public static void sort(ArrayList<Point> ps){
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                //获取到o1的下一步的所有位置个数
                 int count1 = next(o1).size();
                //获取到o2的下一步的所有位置个数
                int count2 = next(o2).size();
                if (count1 < count2){
                    return -1;
                }else if (count1 == count2){
                    return 0;
                }else {
                    return 1;
                }
            }
        });
    }
}
