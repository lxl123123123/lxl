package com.atguigu.Algorithm.kruskal;

//算法七 --> (Kruskal克鲁斯卡尔算法)  解决最小生成树
//公交站问题
public class KruskalCase {
    private int edgeNum; //记录边的个数
    private char[] vertex; //记录顶点的集合
    private int[][] matrix; //邻接矩阵
    private static final int INF = Integer.MAX_VALUE; //使用INF表示两个顶点不能连通

    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        int[][] matrix = new int[][]{
                {0,12,INF,INF,INF,16,14},
                {12,0,10,INF,INF,7,INF},
                {INF,10,0,3,5,6,INF},
                {INF,INF,3,0,4,INF,INF},
                {INF,INF,5,4,0,2,8},
                {16,7,6,INF,2,0,9},
                {14,INF,INF,INF,8,9,0},
        };
        KruskalCase kruskalCase = new KruskalCase(vertex, matrix);
        kruskalCase.print();
        kruskalCase.kruskal();
    }

    public KruskalCase(char[] vertex,int[][] matrix) {
        this.vertex = new char[vertex.length];
        this.matrix = new int[vertex.length][vertex.length];
        for (int i = 0; i < vertex.length; i++) {
            this.vertex[i] = vertex[i];
            for (int j = 0; j <vertex.length; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        //统计边的条数
        for (int i = 0; i < vertex.length; i++) {
            for (int j = i+1; j < vertex.length; j++) {
                if (matrix[i][j] != INF){
                    edgeNum++;
                }
            }
        }
    }
    public void kruskal(){
        int index = 0; //表示最后结果数组的索引
        int[] ends = new int[edgeNum]; //保存顶点对应的终点
        //创建结果数组 保存最后的最小生成树
        EData[] rets = new EData[vertex.length-1];
        //获取所有边的集合
        EData[] edges = getEdges();
        //按照边的权值大小进行排序 从小到大
        sortEdges(edges);
        //遍历edges数组 将边添加到最小生成树中 判断准备加入的边是否形成回路 如果没有构成 就加入 否则不能加入 就下一个
        for (int i = 0; i <edgeNum; i++) {
            int p1 = getPosition(edges[i].start); //第i条边的起点下标 例如 E=4
            int p2 = getPosition(edges[i].end); //第i条边的终点下标 例如 F=5
            //获取p1 p2在最小生成树中的终点
            int m = getEnd(ends, p1); //将4 5 分别传入到保存终点的数组中 获得它们所对应的终点 下一步核对他们的终点是否一致
            int n = getEnd(ends, p2);
            if (m != n){ // 不相等 则没有构成回路
                ends[m] = n; //eg：让E的终点为F
                rets[index++] = edges[i]; //有一条边加入到rets数组
            }
        }
        //统计并打印最小生成树
        System.out.println("最小生成树为:");
        for (EData ret : rets) {
            System.out.println(ret);
        }
    }
    public void print(){
        System.out.println("邻接矩阵为: \n");
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.printf("%12d",matrix[i][j]);
            }
            System.out.println();
        }
    }
    //对边的权值进行排序处理 冒泡
    private void sortEdges(EData[] eData){
        for (int i = 0; i <eData.length-1; i++) {
            for (int j = 0; j <eData.length-i-1; j++) {
                if (eData[j].weight > eData[j+1].weight){
                    EData temp = eData[j];
                    eData[j] = eData[j+1];
                    eData[j+1] = temp;
                }
            }
        }
    }
    //给出一个顶点 返回该顶点对应的下标 否则返回-1
    private int getPosition(char ch){
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] == ch){
                return i;
            }
        }
        return -1;
    }
    //获取图中的边 存到EData[]数组里面去 最后返回一个EData数组
    private EData[] getEdges(){
        int index = 0;
        EData[] eData = new EData[edgeNum];
        for (int i = 0; i < vertex.length; i++) {
            for (int j = i+1; j < vertex.length; j++) {
                if (matrix[i][j] != INF){
                    eData[index++] = new EData(vertex[i],vertex[j],matrix[i][j]);
                }
            }
        }
        return eData;
    }
    /**
     * 获取下标为i的顶点的终点 用于后面判断是否构成回路(也即两个顶点的终点是否相同)
     * @param ends 数组记录了各个顶点对应的终点是哪个 ends数组是在遍历过程中 逐步形成的
     * @param i 表示传入的顶点对应的下标
     * @return 返回的就是 下标为i的这个顶点对应的的终点的下标
     */
    private int getEnd(int[] ends,int i){
        while (ends[i] != 0){
            i = ends[i];
        }
        return i;
    }
}
//创建边类
class EData {
    char start; //边的始端点
    char end; //边的末端点
    int weight; //边的权值

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "<" + start +
                "," + end +
                "> =" + weight +
                '}';
    }
}
