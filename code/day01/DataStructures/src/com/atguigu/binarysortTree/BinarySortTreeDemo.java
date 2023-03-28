package com.atguigu.binarysortTree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7,3,10,12,5,1,9,2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历二叉排序树");
        binarySortTree.infixOrder();
        binarySortTree.delNode(2);
        binarySortTree.delNode(5);
        binarySortTree.delNode(9);
        binarySortTree.delNode(12);
        binarySortTree.delNode(7);
        binarySortTree.delNode(3);
        binarySortTree.delNode(10);
        binarySortTree.delNode(1);
        System.out.println("删除结点后");
        binarySortTree.infixOrder();
    }
}
class BinarySortTree{
    private Node root;
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
