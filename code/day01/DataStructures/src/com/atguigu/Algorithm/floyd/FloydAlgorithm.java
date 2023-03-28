package com.atguigu.Algorithm.floyd;

import java.util.Arrays;

//算法九 --> (Floyd弗洛伊德算法)  解决最短路径(计算每一个结点到其他结点的最短路径 与迪杰斯特拉的区别在于迪杰斯特拉求某一个 弗洛伊德求每一个)
public class FloydAlgorithm {
    public static void main(String[] args) {
        //测试看看图是否创建成功
        char[] vertex = {'A','B','C','D','E','F','G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{0,5,7,N,N,N,2};
        matrix[1] = new int[]{5,0,N,9,N,N,3};
        matrix[2] = new int[]{7,N,0,N,8,N,N};
        matrix[3] = new int[]{N,9,N,0,N,4,N};
        matrix[4] = new int[]{N,N,8,N,0,5,4};
        matrix[5] = new int[]{N,N,N,4,5,0,6};
        matrix[6] = new int[]{2,3,N,N,4,6,0};
        Graph graph = new Graph(vertex.length,matrix,vertex);
        graph.floyd();
        graph.show();
    }
}
//创建图
class Graph{
    private char[] vertex; //存放顶点的数组
    private int[][] dis; //保存从各个顶点出发到其他顶点的距离 最后的结果 也是保留在该数组中的
    private int[][] pre; //保存到达目标顶点的前驱顶点

    public Graph(int length,int[][] matrix,char[] vertex) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
        //对pre数组进行初始化 注意存放的是前驱顶点的下标 而不是直接的A B C D
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i],i);
        }
    }
    //显示dis数组和pre数组
    public void show(){
        System.out.println("pre数组如下");
        for (int i = 0; i < dis.length; i++) {
            for (int j = 0; j < dis.length; j++) {
                System.out.print(vertex[pre[i][j]]+" ");
            }
            System.out.println();
        }
        System.out.println("dis数组如下");
        for (int i = 0; i < dis.length; i++) {
            for (int j = 0; j < dis.length; j++) {
                System.out.print(vertex[i]+"到"+vertex[j]+"的最短距离为"+dis[i][j]+"   ");
            }
            System.out.println();
        }
    }

    //弗洛伊德算法
    public void floyd(){
        int len = 0; //变量保存距离
        for (int i = 0; i < dis.length; i++) { //对中间顶点进行遍历 i就是中间顶点的下标
            for (int j = 0; j < dis.length; j++) { //对出发顶点进行遍历 j就是出发顶点的下标
                for (int k = 0; k < dis.length; k++) { //对终点进行遍历 k就是终点的下标
                    len = dis[j][i] + dis[i][k]; //从出发顶点出发->中间顶点->终点
                    if (len < dis[j][k]){
                        dis[j][k] = len; //更新距离
                        pre[j][k] = pre[i][k]; //更新前驱顶点
                    }
                }
            }
        }
    }
}