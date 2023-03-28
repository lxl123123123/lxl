package com.atguigu.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};
        Node root = createHuffmanTree(arr);
        preOrder(root);
    }

    //编写一个前序遍历的方法
    public static void preOrder(Node root){
        if (root != null){
            root.preOrder();
        }else {
            System.out.println("树为空，无法遍历");
        }
    }

    //创建赫夫曼树的方法
    /**
     *
     * @param arr 需要创建成赫夫曼树的数组
     * @return 创建好后的赫夫曼树的root节点
     */
    public static Node createHuffmanTree(int[] arr){
        //第一步为了操作方便
        //1. 遍历arr数组
        //2. 将arr的每个元素构成一个node
        //3. 将node放入到ArrayList中
        List<Node> list = new ArrayList<>();
        for (int value : arr) {
            list.add(new Node(value));
        }

        while (list.size() > 1){
            //排序 从小到大
            Collections.sort(list);
            System.out.println("list="+list);

            //取出根节点权值最小的两棵二叉树
            //(一)取出权值最小的结点(二叉树)
            Node leftNode = list.get(0);
            //(二)取出权值第二小的结点(二叉树)
            Node rightNode = list.get(1);
            //(三)构建一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //(四)从ArrayList中删除处理过的二叉树
            list.remove(leftNode);
            list.remove(rightNode);
            //(五)将parent加入到list
            list.add(parent);
        }
        //返回赫夫曼树的根节点(while循环退出后 此时数组 集合中只剩下一个元素 也即root结点)
        return list.get(0);
    }
}

//创建结点类
//为了让Node对象支持排序Collections集合排序
//让Node实现Comparable接口
class Node implements Comparable<Node>{
    int value; //结点的权值
    Node left; //指向左子节点
    Node right; //指向右子节点

    //写一个前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}
