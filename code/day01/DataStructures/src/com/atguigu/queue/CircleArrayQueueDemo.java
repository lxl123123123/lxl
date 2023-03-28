package com.atguigu.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        //测试一把
        System.out.println("测试数组模拟环形队列的案例~~");

        //创建一个队列
        CircleArray queue = new CircleArray(4); //说明：设置4，其实队列的有效最大数据是3
        char key = ' ';//接收用户收入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g': //取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': //查看队列头的数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n",res);
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~");

    }

}

//使用数组模拟队列-编写一个ArrayQueue类
class CircleArray{
    private int maxSize; //表示数组的最大容量
    private int front; //队列头  指向队列的第一个元素
    private int rear; //队列尾  指向队列尾部最后一个元素的后一个元素
    private int[] arr; //该数组用于存放数据，模拟队列

    //创建队列的构造器
    public CircleArray(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = 0; //指向队列头部,分析出front是指向队列头
        rear = 0; //指向队列尾部，指向队列尾部数据(即就是队列最后一个数据的后一个数据)
    }

    //判断队列是否满
    public boolean isFull() {
        return (rear+1) % maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n) {
        //先判断队列是否满
        if (isFull()) {
            System.out.println("队列满，不能加入数据");
            return;
        }
        //直接将数据加入
        arr[rear] = n;
        //将rear后移，这里必须考虑取模
        rear = (rear+1) % maxSize;
    }

    //获取队列的数据,出队列
    public int getQueue() {
        //判断队列是否空
        if (isEmpty()) {
            //通过抛出异常
            throw new RuntimeException("队列空，不能取数据");
        }
        int value = arr[front];
        front = (front+1) % maxSize; //front后移
        return value;
    }

    //显示队列所有数据
    public void showQueue() {
        //遍历
        if (isEmpty()) {
            System.out.println("队列为空，没有数据");
            return;
        }
        for (int i = front;i< front + size(); i++){
            System.out.printf("arr[%d]=%d\n",i % maxSize,arr[i%maxSize]);
        }
    }

    //求出当前队列有效数据个数
    public int size(){
        return (rear-front+maxSize) % maxSize;
    }


    //显示队列的头数据,注意不是取数据
    public int headQueue() {
        //判断
        if (isEmpty()) {
            System.out.println("队列空的，没有数据");
            throw new RuntimeException("队列空的，没有数据");
        }
        return arr[front];
    }

}
