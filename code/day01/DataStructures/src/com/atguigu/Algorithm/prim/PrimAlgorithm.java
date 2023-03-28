package com.atguigu.Algorithm.prim;

import java.util.Arrays;

//算法六 --> (Prim普利姆算法)  解决最小生成树
//修路问题
public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = {'A','B','C','D','E','F','G'};
        int vertex = data.length;
        //邻接矩阵的关系使用二维数组表示 10000这个大数 表示两个点不连通
        int[][] weight = new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000},
        };
        MGraph graph = new MGraph(vertex);
        MinTree tree = new MinTree();
        tree.createGraph(graph,vertex,data,weight);
        tree.showGraph(graph);
        //测试普利姆算法
        tree.prim(graph,0);
    }
}
//创建最小生成树->村庄图
class MinTree{
    //创建图的邻接矩阵
    public void createGraph(MGraph graph,int vertex,char[] data,int[][] weight){
        for (int i = 0; i <vertex; i++) {
            graph.data[i] = data[i];
            for (int j = 0; j <vertex; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }
    public void showGraph(MGraph graph){
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }
    //编写prim算法 得到最小生成树 v:表示从哪个顶点开始构造最小生成树
    public void prim(MGraph graph,int v){
        //visited[]数组用来标记顶点是否被访问过 初始化默认值全为0
        int[] visited = new int[graph.vertex];
        //标记当前顶点为已访问过
        visited[v] = 1;
        //定义h1和h2来记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000; //将minWeight初始成一个很大的数 后面在遍历过程中 会被替换
        for (int k = 1; k <graph.vertex; k++) { //这个for循环目的是 遍历n-1条边 也因为这个 k从1开始
            for (int i = 0; i < graph.vertex; i++) { //这个for循环目的是 依次找到i个顶点
                for (int j = 0; j < graph.vertex; j++) { //这个for循环目的是 依次找到i个顶点下分别对应的j 也就是AB AC AD AE...
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight){
                        //替换minWeight(寻找已经访问的和未访问的结点间的权值最小的边)
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            System.out.println("边<"+graph.data[h1]+","+graph.data[h2]+">权值:"+minWeight);
            //将这个结点设置为已访问
            visited[h2] = 1;
            //重置minWeight
            minWeight = 10000;
        }
    }
}
class MGraph{
    int vertex; //表示图的结点数
    char[] data; //存放结点数据
    int[][] weight; //存放边 就是我们的临接矩阵

    public MGraph(int vertex) {
        this.vertex = vertex;
        data = new char[vertex];
        weight = new int[vertex][vertex];
    }
}
