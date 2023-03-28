package com.atguigu.graph;

//深度搜索 dfs算法
//广度搜索 bfs算法
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class graph {
    private ArrayList<String> vertexList; //存放顶点的集合
    private int[][] edges; //存放图的矩阵
    private int numOfEdges; //表示边的数目
    private boolean[] isVisited; //表示顶点是否被访问

    public static void main(String[] args) {
        //测试一把图是否创建完毕
        int n = 5;
        graph graph = new graph(n);
        String[] vertex = {"A","B","C","D","E"};
        for (String s : vertex) {
            graph.insertVertex(s);
        }
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        graph.showGraph();

        //测试一把
        System.out.println("深度搜索dfs");
//        graph.dfs();
        System.out.println();
        System.out.println("广度优先bfs");
        graph.bfs();
    }

    public graph(int n) { //一共有多少个结点
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        isVisited = new boolean[n];
        numOfEdges = 0;
    }
    //返回当前结点的第一个邻接结点的下标w
    public int getFirstNeighbor(int index){
        for (int i = 0; i <vertexList.size(); i++) {
            if (edges[index][i] > 0){
                return i;
            }
        }
        return -1;
    }
    //跟据前一个结点的下标获取下一个邻接节点
    public int getNextNeighbor(int v1,int v2){
        for (int i = v2+1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0){
                return i;
            }
        }
        return -1;
    }
    //深度优先遍历算法
    //i 第一次就是0
    private void dfs(boolean[] isVisited,int i){
        //首先我们访问该结点 输出
        System.out.print(getValueByIndex(i)+"->");
        //将结点设置为已访问
        isVisited[i] = true;
        //查找当前节点的到第一个邻接节点
        int w = getFirstNeighbor(i);
        while (w != -1){ //说明查到了
            if (!isVisited[w]){ //未访问
                dfs(isVisited,w);
            }
            //如果已经被访问过了
            w = getNextNeighbor(i,w);
        }
    }
    //对dfs进行重载 遍历我们所有的结点 并进行dfs 回溯(如果当前这个结点找完了 就回溯到上一个结点)
    public void dfs(){
        //遍历所有的结点 进行dfs
        for (int i = 0; i <getNumOfVertex(); i++) {
            if (!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }

    //对一个结点进行广度优先遍历的方法
    private void bfs(boolean[] isVisited,int i){
        int u; //表示队列的头结点对应下标
        int w; //邻接结点w
        //队列,记录结点访问的顺序
        LinkedList queue = new LinkedList();
        //访问结点 输出结点信息
        System.out.print(getValueByIndex(i)+"->");
        //标记为已访问
        isVisited[i] = true;
        //将结点加入队列
        queue.addLast(i);
        while (!queue.isEmpty()){
            //取出队列的头结点下标
            u = (Integer)queue.removeFirst();
            //得到第一个临结点的下标w
            w = getFirstNeighbor(u);
            while (w != -1){ //找到了
                if (!isVisited[w]){ //未访问
                    System.out.print(getValueByIndex(w)+"->");
                    isVisited[w] = true;
                    //入队列
                    queue.addLast(w);
                }
                //如果已经访问过了 就下一个
                w = getNextNeighbor(u,w);
            }
        }
    }
    //遍历所有的结点 都进行广度优先搜索
    public void bfs(){
        for (int i = 0; i <getNumOfVertex(); i++) {
            if (!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }

    //返回结点个数
    public int getNumOfVertex(){
        return vertexList.size();
    }
    //返回边的个数
    public int getNumOfEdges(){
        return numOfEdges;
    }
    //返回结点i对应的数据 0->A 1->B
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }
    //返回v1 v2的权值
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }
    //显示图对应的矩阵
    public void showGraph(){
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }
    //插入节点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }
    //添加边
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
