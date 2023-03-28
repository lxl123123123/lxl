package com.atguigu.Test;

public class trees {
    public static void main(String[] args) {
        //先需要创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建结点
        HeroNode root = new HeroNode(1,"宋江");
        HeroNode node2 = new HeroNode(2,"吴用");
        HeroNode node3 = new HeroNode(3,"卢俊义");
        HeroNode node4 = new HeroNode(4,"林冲");
        HeroNode node5 = new HeroNode(5,"关胜");
        binaryTree.setRoot(root);
        root.setLeft(node2);
        root.setRight(node3);
        node3.setLeft(node5);
        node3.setRight(node4);
        //测试一把
//        System.out.println("前序遍历");
//        binaryTree.preOrder();
//        System.out.println("中序遍历");
//        binaryTree.InfixOrder();
//        System.out.println("后序遍历");
//        binaryTree.postOrder();
//        //前序遍历
//        System.out.println("前序遍历查找");
//        HeroNode resNode = binaryTree.preOrderSearch(5);
//        if (resNode != null){
//            System.out.printf("找到了，信息为no=%d name=%s",resNode.getNo(),resNode.getName());
//        }else {
//            System.out.printf("没有找到no=%d的英雄",5);
//        }
//        System.out.println();
//        //中序遍历
//        System.out.println("中序遍历查找");
//        HeroNode resNode1 = binaryTree.infixOrderSearch(5);
//        if (resNode1 != null){
//            System.out.printf("找到了，信息为no=%d name=%s",resNode1.getNo(),resNode1.getName());
//        }else {
//            System.out.printf("没有找到no=%d的英雄",5);
//        }
//        System.out.println();
//        //后序遍历
//        System.out.println("后序遍历查找");
//        HeroNode resNode2 = binaryTree.preOrderSearch(5);
//        if (resNode2 != null){
//            System.out.printf("找到了，信息为no=%d name=%s",resNode2.getNo(),resNode2.getName());
//        }else {
//            System.out.printf("没有找到no=%d的英雄",5);
//        }
        System.out.println("删除前，前序遍历");
        binaryTree.preOrder();
        binaryTree.delete(3);
        System.out.println("删除后，前序遍历");
        binaryTree.preOrder();
    }
}
//创建二叉树
class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }
    //前序遍历
    public void preOrder(){
        if (this.root != null){
            this.root.preOrder();
        }else {
            System.out.println("树为空 无法遍历");
        }
    }
    //中序遍历
    public void InfixOrder(){
        if (this.root != null){
            this.root.infixOrder();
        }else {
            System.out.println("树为空 无法遍历");
        }
    }
    //后序遍历
    public void postOrder(){
        if (this.root != null){
            this.root.postOrder();
        }else {
            System.out.println("树为空 无法遍历");
        }
    }
    //前序查找
    public HeroNode preOrderSearch(int no){
        if (this.root != null){
            return this.root.preOrderSearch(no);
        }else {
            return null;
        }
    }
    //中序查找
    public HeroNode infixOrderSearch(int no){
        if (this.root != null){
            return this.root.infixOrderSearch(no);
        }else {
            return null;
        }
    }
    //后序查找
    public HeroNode postOrderSearch(int no){
        if (this.root != null){
            return this.root.postOrderSearch(no);
        }else {
            return null;
        }
    }
    //删除
    public void delete(int no){
        if (this.root != null){
            if (root.getNo() == no){
                root = null;
            }else {
                root.delete(no);
            }
        }else {
            System.out.println("树为空 无法遍历");
        }
    }
}
//创建结点
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }
    //中序遍历
    public void infixOrder(){
        if (this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null){
            this.right.infixOrder();
        }
    }
    //后序遍历
    public void postOrder(){
        if (this.left != null){
            this.left.postOrder();
        }
        if (this.right != null){
            this.right.postOrder();
        }
        System.out.println(this);
    }
    //前序查找
    public HeroNode preOrderSearch(int no){
        if (this.no == no){
            return this;
        }
        HeroNode res = null;
        if (this.left != null){
            res = this.left.preOrderSearch(no);
        }
        if (res != null){
            return res;
        }
        if (this.right != null){
            res = this.right.preOrderSearch(no);
        }
        return res;
    }
    //中序查找
    public HeroNode infixOrderSearch(int no){
        HeroNode res = null;
        if (this.left != null){
            res = this.left.infixOrderSearch(no);
        }
        if (res != null){
            return res;
        }
        if (this.no == no){
            return this;
        }
        if (this.right != null){
            res = this.right.infixOrderSearch(no);
        }
        return res;
    }
    //后序查找
    public HeroNode postOrderSearch(int no){
        HeroNode res = null;
        if (this.left != null){
            res = this.left.postOrderSearch(no);
        }
        if (res != null){
            return res;
        }
        if (this.right != null){
            res = this.right.postOrderSearch(no);
        }
        if (res != null){
            return res;
        }
        if (this.no == no){
            return this;
        }
        return res;
    }
    //删除
    public void delete(int no){
        if (this.left != null && this.left.no == no){
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no){
            this.right = null;
            return;
        }
        //向左递归删除
        if (this.left != null){
            this.left.delete(no);
        }
        //向右递归删除
        if (this.right != null){
            this.right.delete(no);
        }
    }
}
