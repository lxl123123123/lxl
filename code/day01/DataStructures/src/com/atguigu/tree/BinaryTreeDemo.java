package com.atguigu.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        //先需要创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建结点
        HeroNode root = new HeroNode(1,"宋江");
        HeroNode node2 = new HeroNode(2,"吴用");
        HeroNode node3 = new HeroNode(3,"卢俊义");
        HeroNode node4 = new HeroNode(4,"林冲");
        HeroNode node5 = new HeroNode(5,"关胜");

        //说明 现在我们先手动创建二叉树 以后我们学习递归的方式创建
        binaryTree.setRoot(root);
        root.setLeft(node2);
        root.setRight(node3);
        node3.setLeft(node5);
        node3.setRight(node4);
        //测试一把
//        System.out.println("前序遍历");
//        binaryTree.preOrder();
//        System.out.println("中序遍历");
//        binaryTree.infixOrder();
//        System.out.println("后序遍历");
//        binaryTree.postOrder();

//       //前序遍历
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
        binaryTree.delNode(3);
        System.out.println("删除后，前序遍历");
        binaryTree.preOrder();
    }
}

//定义一个BinaryTree 二叉树
class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //删除结点
    public void delNode(int no){
        if (root != null){
            if (root.getNo() == no){
                root = null;
            }else {
                //递归删除
                root.delNode(no);
            }
        }else {
            System.out.println("空树不能删除");
        }
    }

    //前序遍历
    public void preOrder(){
        if (this.root != null){
            this.root.preOrder();
        }else {
            System.out.println("二叉树为空无法遍历");
        }
    }

    //中序遍历
    public void infixOrder(){
        if (this.root != null){
            this.root.infixOrder();
        }else {
            System.out.println("二叉树为空无法遍历");
        }
    }

    //后序遍历
    public void postOrder(){
        if (this.root != null){
            this.root.postOrder();
        }else {
            System.out.println("二叉树为空无法遍历");
        }
    }

    //前序查找
    public HeroNode preOrderSearch(int no){
        if (root != null){
            return this.root.preOrderSearch(no);
        }else {
            return null;
        }
    }

    //中序查找
    public HeroNode infixOrderSearch(int no){
        if (root != null){
            return this.root.infixOrderSearch(no);
        }else {
            return null;
        }
    }

    //后序查找
    public HeroNode postOrderSearch(int no){
        if (root != null){
            return this.root.postOrderSearch(no);
        }else {
            return null;
        }
    }
}

//先创建HeroNode结点
class HeroNode{
    private int no;
    private String name;
    private HeroNode left; //默认为null
    private HeroNode right; //默认为null

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

    //编写删除方法
    public void delNode(int no){
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
            this.left.delNode(no);
        }
        //向右递归删除
        if (this.right != null){
            this.right.delNode(no);
        }
    }

    //编写前序遍历的方法
    public void preOrder(){
        System.out.println(this); //先输出父节点
        //递归向左子树前序遍历
        if (this.left != null){
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if (this.right != null){
            this.right.preOrder();
        }
    }

    //编写中序遍历
    public void infixOrder(){
        if (this.left != null){ //先判断当前结点的左子树为不为空
            this.left.infixOrder(); //递归向左子树中序遍历
        }
        System.out.println(this); //输出当前节点
        if (this.right != null){ //先判断当前结点的右子树为不为空
            this.right.infixOrder(); //递归向右子树中序遍历
        }
    }

    //编写后序遍历
    public void postOrder(){
        if (this.left != null){ //先判断当前结点的左子树为不为空
            this.left.postOrder(); //递归向左子树中序遍历
        }
        if (this.right != null){ //先判断当前结点的右子树为不为空
            this.right.postOrder(); //递归向右子树中序遍历
        }
        System.out.println(this); //输出当前节点
    }

    //编写前序查找
    public HeroNode preOrderSearch(int no){
        //比较当前节点
        if (this.no == no){
            return this;
        }
        HeroNode resNode = null;
        if (this.left != null){
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null){ //说明我们在左子节点找到了
            return resNode;
        }
        if (this.right != null){
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //编写中序查找
    public HeroNode infixOrderSearch(int no){
        HeroNode resNode = null;
        if (this.left != null){
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        if (this.no == no){
            return this;
        }
        if (this.right != null){
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //编写后序查找
    public HeroNode postOrderSearch(int no){
        HeroNode resNode = null;
        if (this.left != null){
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        if (this.right != null){
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        if (this.no == no){
            return this;
        }
        return resNode;
    }
}
