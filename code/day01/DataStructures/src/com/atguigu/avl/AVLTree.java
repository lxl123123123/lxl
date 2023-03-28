package com.atguigu.avl;

public class AVLTree {
    public static void main(String[] args) {
//        int[] arr = {4,3,6,5,7,8};
//        int[] arr = {10,12,8,9,7,6};
        int[] arr = {10,11,7,6,8,9};
        //创建一个avl树
        avl avlTree = new avl();
        //添加节点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        //遍历一把
        avlTree.infixOrder();
        System.out.println("平衡处理后~~");
        System.out.println("树的高度"+avlTree.getRoot().height());
        System.out.println("树的左子树的高度"+avlTree.getRoot().leftHeight());
        System.out.println("树的右子树的高度"+avlTree.getRoot().rightHeight());
    }
}
//创建AVL树
class avl{
    private Node root;

    public Node getRoot() {
        return root;
    }

    //添加结点
    public void add(Node node){
        if (root == null){
            root = node;
        }else {
            root.add(node);
        }
    }
    //查找要删除的结点
    public Node search(int value){
        if (this.root == null){
            return null;
        }else {
            return root.search(value);
        }
    }
    //查找父节点
    public Node searchParent(int value){
        if (this.root == null){
            return null;
        }else {
            return root.searchParent(value);
        }
    }
    //返回以node为根节点的二叉排序树的最小节点的值  同时删除最小节点
    public int delRightTreeMin(Node node){
        Node target = node;
        //循环查找左子节点 就会找到最小值
        while (target.left != null){
            target = target.left;
        }
        delNode(target.value);
        return target.value;
    }
    //删除结点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            Node targetNode = search(value);
            if (targetNode == null) {
                return;
            }
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            Node parent = searchParent(value);
            //如果要删除的是叶子结点
            if (targetNode.left == null && targetNode.right == null) {
                //判断targetNode是父节点parent的左子节点还是右子节点
                if (parent.left != null && parent.left == targetNode) {
                    parent.left = null;
                } else if (parent.right != null && parent.right == targetNode) {
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {
                //表示要删除的是有两棵子树的结点
                int minValue = delRightTreeMin(targetNode.right); //传入的是右子树(右子树中找最小值)
                targetNode.value = minValue;
            } else { //表示要删除的是只有一棵子树的结点
                if (targetNode.left != null) {
                    if (parent != null){
                        if (parent.left != null && parent.left == targetNode) {
                            parent.left = targetNode.left;
                        } else if (parent.right != null && parent.right == targetNode) {
                            parent.right = targetNode.left;
                        }
                    }else {
                        root = targetNode.left;
                    }
                } else if (targetNode.right != null) {
                    if (parent != null){
                        if (parent.left != null && parent.left == targetNode) {
                            parent.left = targetNode.right;
                        } else if (parent.right != null && parent.right == targetNode) {
                            parent.right = targetNode.right;
                        }
                    }else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }
    //中序遍历
    public void infixOrder(){
        if (this.root != null){
            this.root.infixOrder();
        }else {
            System.out.println("树为空 无法遍历");
        }
    }
}
//创建结点
class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
    //返回左子树高度
    public int leftHeight(){
        if (left == null){
            return 0;
        }
        return left.height();
    }
    //返回右子树高度
    public int rightHeight(){
        if (right == null){
            return 0;
        }
        return right.height();
    }
    //返回当前结点的高度,以该结点为根节点的数的高度
    public int height(){
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }
    //左旋转方法
    private void leftRotate(){
        //创建新的结点 以当前根结点的值
        Node newNode = new Node(this.value);
        //把新的结点的左子树设置成当前节点的左子树
        newNode.left = this.left;
        //把新的节点的右子树设置成当前节点的右子树的左子树
        newNode.right = this.right.left;
        //把当前节点的值替换成右子树的值
        this.value = this.right.value;
        //把当前节点的右子树设置成当前结点右子树的右子树
        this.right = this.right.right;
        //把当前节点的左子节点设置成新的结点
        this.left = newNode;
    }
    //右旋转方法
    private void rightRotate(){
        Node newNode = new Node(this.value);
        newNode.right = this.right;
        newNode.left = this.left.right;
        this.value = this.left.value;
        this.left = this.left.left;
        this.right = newNode;
    }


    //查找要删除的结点(value是你希望删除的结点的值 找到返回Node 否则返回null)
    public Node search(int value){
        if (value == this.value){
            return this;
        }else if(value < this.value){ //向左子树递归查找
            if (this.left == null){
                return null;
            }
            return this.left.search(value);
        }else { //向右子树递归查找
            if (this.right == null){
                return null;
            }
            return this.right.search(value);
        }
    }
    //查找要删除节点的父节点
    public Node searchParent(int value){
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)){
            return this;
        }else {
            if (value < this.value && this.left != null){
                return this.left.searchParent(value);
            }else if (value >= this.value && this.right != null){
                return this.right.searchParent(value);
            }else {
                return null;
            }
        }
    }

    //添加结点
    //递归的方式添加结点 注意满足二叉排序树的要求
    public void add(Node node){
        if (node == null){
            return;
        }
        //判断传入的节点的值和当前子树根节点的值关系
        if (node.value < this.value){
            if (this.left == null){
                this.left = node;
            }else {
                //递归的向左子树添加
                this.left.add(node);
            }
        }else {
            if (this.right == null){
                this.right = node;
            }else {
                //递归的向右子树添加
                this.right.add(node);
            }
        }

        //当添加完一个结点后，如果：(右子树的高度 - 左子树的高度) > 1 ，则左旋转
        if (rightHeight() - leftHeight() > 1){
            //考虑双旋转 如果当前节点的右子树的左子树的高度大于它的右子树的高度
            if (right != null && right.leftHeight() > right.rightHeight()){
                //先对当前结点的右结点进行右旋转 然后再对当前结点进行左旋转
                right.rightRotate();
                leftRotate(); //左旋转
            }else {
                //直接进行左旋转即可
                leftRotate();
            }
            return;
        }
        //当添加完一个结点后，如果：(左子树的高度 - 右子树的高度) > 1 ，则右旋转
        if (leftHeight() - rightHeight() > 1){
            //考虑双旋转 如果当前节点的左子树的右子树的高度大于它的左子树的高度
            if (left != null && left.rightHeight() > left.leftHeight()){
                //先对当前结点的左结点进行左旋转 然后再对当前结点进行右旋转
                left.leftRotate();
                rightRotate(); //右旋转
            }else {
                //直接进行右旋转即可
                rightRotate();
            }
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
}
