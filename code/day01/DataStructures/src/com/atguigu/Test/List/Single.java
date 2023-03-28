package com.atguigu.Test.List;


import java.util.Stack;

public class Single  {
    public static void main(String[] args) {
        Node node1 = new Node(1,"李霄龙");
        Node node2 = new Node(2,"李育腾");
        Node node3 = new Node(3,"卿南");
        Node node4 = new Node(4,"姚思远");
        Node node5 = new Node(5,"范文");
        LinkedList linkedList = new LinkedList();
//        linkedList.add(node1);
//        linkedList.add(node2);
//        linkedList.add(node3);
//        linkedList.add(node4);
//        linkedList.add(node5);
        linkedList.addByOrder(node2);
        linkedList.addByOrder(node4);
        linkedList.addByOrder(node3);
        linkedList.addByOrder(node5);
        linkedList.addByOrder(node1);
        linkedList.list();
//        System.out.println("单链表反转");
//        reverseList(linkedList.getHead());
//        linkedList.list();
//        System.out.println("逆序输出");
//        reversePrint(linkedList.getHead());
//        System.out.println("查询结点为");
//        System.out.println(FindIndexNode(linkedList.getHead(), 2));
//        System.out.println("修改后");
////        System.out.println("删除后");
////        linkedList.delete(-1);
//        Node newNode = new Node(2,"李亦欣");
//        linkedList.update(newNode);
//        linkedList.list();
    }
    //单链表反转
    public static void reverseList(Node head){
        if (head.next == null || head.next.next == null){
            return;
        }
        Node temp = head.next;
        Node next = null;
        Node reverseHead = new Node(0,"");
        while (temp!=null){
            next = temp.next;
            temp.next = reverseHead.next;
            reverseHead.next = temp;
            temp = next;
        }
        head.next = reverseHead.next;
    }
    //单链表逆序输出
    public static void reversePrint(Node head){
        if (head.next == null){
            return;
        }
        Node temp = head.next;
        Stack<Node> stack = new Stack<>();
        while (temp != null){
            stack.push(temp);
            temp = temp.next;
        }
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }
    //查询单链表倒数第k个结点
    public static Node FindIndexNode(Node head,int k){
        if (head.next == null){
            System.out.println("链表为空,无法查询");
            return null;
        }
        Node temp = head.next;
        int count = 0;
        boolean flag = false;
        int lengthSize = getLength(head);
        while (temp != null){
            if (count == lengthSize - k){
                flag = true;
                break;
            }
            temp = temp.next;
            count++;
        }
        if (flag){
            return temp;
        }else {
            return null;
        }
    }
    //返回单链表一共有多少个结点
    public static int getLength(Node head){
        if (head.next == null){
            return 0;
        }
        int length = 0;
        Node temp = head.next;
        while (temp != null){
            length++;
            temp = temp.next;
        }
        return length;
    }
}
//创建单链表
class LinkedList{
    private Node head = new Node(0,"");

    public Node getHead() {
        return head;
    }

    //添加到最后
    public void add(Node node){
        Node temp = head;
        while (true){
            if (temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
    }
    //按序号添加
    public void addByOrder(Node node){
        Node temp = head;
        while (true){
            if (temp.next == null){
                break;
            }
            if (temp.next.no > node.no){
                break;
            }
            temp = temp.next;
        }
            node.next = temp.next;
            temp.next = node;
    }
    //删除
    public void delete(int no){
        if (head.next == null){
            System.out.println("链表为空 无法删除");
            return;
        }
        Node temp = head;
        boolean flag = false;
        while (true){
            if (temp.next == null){
                break;
            }
            if (temp.next.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.next = temp.next.next;
        }else {
            System.out.printf("没有找到编号为%d的结点，无法删除",no);
        }
    }
    //修改
    public void update(Node node){
        if (head.next == null){
            System.out.println("链表为空,无法删除");
        }
        Node temp = head;
        boolean flag = false;
        while (true){
            if (temp.next == null){
                break;
            }
            if (temp.no == node.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.name = node.name;
        }else {
            System.out.printf("没有找到编号为%d的结点，无法修改",node.no);
        }
    }
    //遍历
    public void list(){
        if (head.next == null){
            System.out.println("链表为空,无法遍历");
            return;
        }
        Node temp = head.next;
        while (true){
            System.out.println(temp);
            if (temp.next == null){
                break;
            }
            temp = temp.next;
        }
    }
}
//创建结点
class Node{
    public int no;
    public String name;
    public Node next;

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
