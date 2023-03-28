package com.atguigu.tree.ThreadedBinaryTree;


public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //测试一把
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        //二叉树，后面会递归创建 先手动创建
        ThreadedBinaryTree binaryTree = new ThreadedBinaryTree();
        binaryTree.setRoot(root);
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        binaryTree.threadedNode(root);
        //以10号结点做测试
        HeroNode leftNode = node5.getLeft();
        HeroNode rightNode = node5.getRight();
        System.out.println("10号节点的前驱结点是=" + leftNode);
        System.out.println("10号节点的后继结点是=" + rightNode);

        //遍历线索化二叉树
        System.out.println("使用线索化遍历二叉树");
        binaryTree.threadedList();
    }
}

//定义一个ThreadedBinaryTree 实现了线索化功能的二叉树
class ThreadedBinaryTree{
    private HeroNode root;
    //为了实现线索化，需要创建一个指向当前需要线索化结点的前驱结点的指针
    //在递归进行线索化时 pre总是保留前一个节点
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //遍历线索化二叉树的方法
    public void threadedList(){
        //定义一个变量 存储当前遍历的结点 从root开始
        HeroNode node = root;
        while (node != null){

            while (node.getLeftType() == 0){
                node = node.getLeft();
            }

            //打印这个节点
            System.out.println(node);

            while (node.getRightType() == 1){
                node = node.getRight();
                System.out.println(node);
            }

            node = node.getRight();

        }
    }

    //编写对二叉树进行线索化的方法(以中序遍历为例)
    /**
     *
     * @param node node就是当前需要线索化的结点
     */
    public void threadedNode(HeroNode node){
        //如果node为空 无法线索化 直接返回
        if (node == null){
            return;
        }
        //(一)先线索化左子树
        threadedNode(node.getLeft());
        //(二)线索化当前节点
        if (node.getLeft() == null){
            //让前驱结点的右指针指向当前节点
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
        //(三)再线索化右子树
        threadedNode(node.getRight());
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

    //说明
    //1. 如果leftType == 0 表示指向的是左子树 如果leftType == 1 表示指向的是前驱结点
    //2. 如果rightType == 0 表示指向的是右子树 如果rightType == 1 表示指向的是后继结点
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

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
