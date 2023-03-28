package com.atguigu.tree;

//顺序存储二叉树
//即二叉树可以转换为数组 数组也可以转换为二叉树
//应用 --> 堆排序
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder(0);
        System.out.println();
        arrBinaryTree.infixOrder(0);
        System.out.println();
        arrBinaryTree.postOrder(0);
    }
}
//实现顺序存储二叉树
class ArrBinaryTree{
    private int[] arr; //存储二叉树数据结点的数组

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //编写一个方法 完成顺序存储二叉树的前序遍历

    /**
     *
     * @param index 数组下标
     */
    public void preOrder(int index){
        if (arr == null || arr.length == 0){
            System.out.println("数组为空 不能按照二叉树前序遍历");
        }
        System.out.print(arr[index]);
        //向左递归遍历
        if ((index * 2 + 1) < arr.length){
            preOrder(index*2+1);
        }
        //向右递归遍历
        if ((index * 2 + 2) < arr.length){
            preOrder(index*2+2);
        }
    }

    public void infixOrder(int index){
        if (arr == null || arr.length == 0){
            System.out.println("数组为空 不能按照二叉树前序遍历");
        }
        //向左递归遍历
        if ((index * 2 + 1) < arr.length){
            infixOrder(index*2+1);
        }
        System.out.print(arr[index]);
        //向右递归遍历
        if ((index * 2 + 2) < arr.length){
            infixOrder(index*2+2);
        }
    }

    public void postOrder(int index){
        if (arr == null || arr.length == 0){
            System.out.println("数组为空 不能按照二叉树前序遍历");
        }
        //向左递归遍历
        if ((index * 2 + 1) < arr.length){
            postOrder(index*2+1);
        }
        //向右递归遍历
        if ((index * 2 + 2) < arr.length){
            postOrder(index*2+2);
        }
        System.out.print(arr[index]);
    }
}
