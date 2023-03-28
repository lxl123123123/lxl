package com.atguigu.Algorithm.dijkstra;

import java.util.Arrays;

//算法八 --> (Dijkstra迪杰斯特拉算法)  解决最短路径(计算某一个结点到其他结点的最短路径 本题以G到其他结点为例求最短路径)
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535; //表示不可连接
        matrix[0] = new int[]{N,5,7,N,N,N,2};
        matrix[1] = new int[]{5,N,N,9,N,N,3};
        matrix[2] = new int[]{7,N,N,N,8,N,N};
        matrix[3] = new int[]{N,9,N,N,N,4,N};
        matrix[4] = new int[]{N,N,8,N,N,5,4};
        matrix[5] = new int[]{N,N,N,4,5,N,6};
        matrix[6] = new int[]{2,3,N,N,4,6,N};
        Graph graph = new Graph(vertex,matrix);
        graph.print();
        //测试迪杰斯特拉算法
        graph.dijkstra(6); //这里以G为出发点为例
        graph.show();
    }
}
class Graph{
    private char[] vertex; //存放顶点集合
    private int[][] matrix; //二维数组邻接矩阵
    private VisitedVertex vv; //已经访问的顶点的集合

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }
    //输出二维数组
    public void print(){
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }
    //显示最后结果
    public void show(){
        vv.show();
    }
    //迪杰斯特拉算法实现
    public void dijkstra(int index){ //index为出发顶点对应的下标
        vv = new VisitedVertex(vertex.length, index);
        update(index);
        for (int i = 1; i < vertex.length; i++) {
            index = vv.updateArr();
            update(index);
        }
    }
    //更新下标为index的顶点到周围顶点的距离和周围顶点的前驱结点
    //注意这个方法调用完之后 只是把出发顶点周围的顶点情况存放到三个数组里面了 例如出发顶点为G 则周围顶点就分别是A B C D E F
    //访问数组 000001 dis数组2,3,65535,65535,4,6,0  pre_visited数组6600660
    //所以底下又有继续选择并访问的方法
    private void update(int index){
        int len = 0;
        for (int j = 0; j < matrix[index].length; j++) {
            //len的含义是 ： 出发顶点到index顶点的距离 + 从index顶点到j顶点的距离的和
            len = vv.getDis(index) + matrix[index][j];
            if (!vv.in(j) && len < vv.getDis(j)){ //如果j顶点没有被访问过 同时 出发顶点->index->j 的距离小于 出发顶点直接到j的距离
                vv.updateDis(j,len);
                vv.updatePre(j,index);
            }
        }
    }
}
//已访问顶点集合
class VisitedVertex{
    //记录各个顶点是否访问过 1表示访问过 0未访问 会动态更新
    public int[] already_arr;
    //每个下标对应的值为前一个顶点下标 会动态更新
    public int[] pre_visited;
    //记录出发顶点到其他所有顶点的距离 比如G为出发顶点 就会记录G到其他顶点距离 会动态更新 求的最短距离就会存放到dis
    public int[] dis;
    /**
     * 构造器
     * @param length 表示顶点个数
     * @param index 表示出发顶点的下标 比如出发顶点为G 就是6
     */
    public VisitedVertex(int length,int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        //初始化dis数组
        Arrays.fill(dis,65535);
        this.dis[index] = 0; //设置出发顶点的访问距离为0
        this.already_arr[index] = 1; //设置出发顶点为已访问
    }
    //判断index顶点是否被访问过
    public boolean in(int index){
        return already_arr[index] == 1;
    }
    //更新出发顶点到index顶点的距离
    public void updateDis(int index,int len){
        dis[index] = len;
    }
    //更新index这个顶点的前驱结点为pre
    public void updatePre(int index,int pre){
        pre_visited[index] = pre;
    }
    //得到出发点到下标为index顶点的距离
    public int getDis(int index){
        return dis[index];
    }
    //继续选择并返回新的访问结点 比如这里的G完后 就是A点作为新的访问顶点
    public int updateArr(){
        int min = 65535;
        int index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min){
                min = dis[i];
                index = i;
            }
        }
        //更新index顶点为已访问过
        already_arr[index] = 1;
        return index;
    }
    //显示最后的结果
    //即将三个数组的情况输出
    public void show(){
        System.out.println("========================");
        //遍历访问数组
        for (int i : already_arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        //遍历前驱数组
        for (int i : pre_visited) {
            System.out.print(i + " ");
        }
        System.out.println();
        //遍历最终的dis数组
        char[] vertex = {'A','B','C','D','E','F','G'};
        int count = 0;
        for (int i : dis) {
            if (i != 65535){
                System.out.print(vertex[count] + "("+i+")");
            }else {
                System.out.println("N");
            }
            count++;
        }
        System.out.println();
    }
}
