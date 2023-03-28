package com.atguigu.hashtab;

import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {

        //创建一个哈希表
        HashTab hashTab = new HashTab(7);

        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("del: 删除雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key){
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findById(id);
                    break;
                case "del":
                    System.out.println("输入要删除的id");
                    id = scanner.nextInt();
                    hashTab.delete(id);
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }
}

//创建HashTab 管理多条链表
class HashTab{
    private EmpLinkedList[] empLinkedListArray;
    private int size; //表示哈希表中一共有多少条链表

    public HashTab(int size) {
        this.size = size;
        //初始化empLinkedLists
        empLinkedListArray = new EmpLinkedList[size];
        for (int i = 0; i <size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp){
        //根据员工id的值 得到该员工应当添加到哪条链表
        int empLinkedListNo = hashFun(emp.id);
        //将emp加入到对应的链表中
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    //查找雇员信息
    public void findById(int id){
        //使用散列函数确定哪条链表
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNo].findEmpById(id);
        if (emp != null){
            System.out.printf("在第%d条链表中找到雇员id=%d\n",(empLinkedListNo+1),id);
        }else {
            System.out.println("在哈希表中，没有找到该雇员");
        }
    }

    //删除指定id的雇员
    public void delete(int id){
        //使用散列函数确定哪条链表
        int empLinkedListNo = hashFun(id);
        empLinkedListArray[empLinkedListNo].delete(id);
    }

    //遍历所有的链表 遍历哈希表
    public void list(){
        for (int i = 0; i <size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    //编写散列函数 使用简单的取模法
    public int hashFun(int id){
        return id % size;
    }

}

//表示一个雇员
class Emp{
    public int id;
    public String name;
    public Emp next; //next默认为空

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

//创建EmpLinkedList 表示链表
class EmpLinkedList{
    //头指针 指向第一个雇员 因此我们这个链表的head 是直接指向第一个Emp(没有头结点)
    private Emp head; //默认为null

    //添加雇员到链表
    //假定添加雇员时 id是自增的 因此我们将该雇员直接添加到本链表的最后
    public void add(Emp emp){
        //如果是添加第一个雇员
        if (head == null){
            head = emp;
            return;
        }
        //如果不是 定义一个辅助指针 帮助定位到最后
        Emp temp = head;
        while (true){
            if (temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = emp;
    }

    //遍历链表的雇员信息
    public void list(int no){
        if (head == null){
            System.out.println("第"+(no+1)+"链表为空");
            return;
        }
        System.out.print("第"+(no+1)+"条链表信息为");
        Emp temp = head;
        while (true){
            System.out.printf("=> id=%d name=%s\t",temp.id,temp.name);
            if (temp.next == null){ //说明此时temp已经是最后一个了
                break;
            }
            temp = temp.next;
        }
        System.out.println();
    }

    //根据id查雇员
    //如果查到 就返回emp 如果没有找到就返回null
    public Emp findEmpById(int id){
        //先判断链表是否为空
        if (head == null){
            System.out.println("链表为空");
            return null;
        }
        Emp temp = head;
        while (true){
            if (temp.id == id){
                break;
            }
            if (temp.next == null){
                temp = null;
                break;
            }
            temp = temp.next;
        }
            return temp;
    }

    //根据雇员id删除结点
    public void delete(int id){
        if (head == null){
            System.out.println("链表为空");
            return;
        }
        Emp temp = head;
        boolean flag = false;
        while (true){
            if (temp.id == id){
                head = head.next;
                return;
            }
            if (temp.next == null){
                break;
            }
            if (temp.next.id == id){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.next = temp.next.next;
        }else {
            System.out.println("没有找到");
        }
    }

}
