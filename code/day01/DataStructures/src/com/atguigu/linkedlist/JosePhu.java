package com.atguigu.linkedlist;

/**
 * 约瑟夫问题
 */
public class JosePhu {
    public static void main(String[] args) {

        //测试一把看看构建环形链表，和遍历是否OK
        CircleSingleLinkedList linkedList = new CircleSingleLinkedList();
        linkedList.addBoy(5);//加入5个小孩结点
        linkedList.showBoy();
        //测试一把小孩出圈是否正确
        linkedList.countBoy(1,2,5);
    }
}

//创建一个单向环形链表
class CircleSingleLinkedList{
    //创建一个first结点,当前没有编号
    private Boy first = null;
    //添加结点，构建环形链表
    public void addBoy(int nums){
        //对nums值做一个简单地数据校验
        if (nums < 1){
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null;//辅助指针，帮助构建环形链表
        //使用for循环创建我们的环形链表
        for (int i=1;i<=nums;i++){
            //根据编号创建小孩结点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1){
                first = boy;
                first.setNext(first);
                curBoy = first;
            }else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历
    public void showBoy(){
        //判断链表是否为空
        if (first == null){
            System.out.println("链表为空,没有任何小孩");
            return;
        }
        //因为first不能动 因此仍然使用辅助指针完成遍历
        Boy curBoy = first;
        while (true){
            System.out.printf("小孩的编号%d\n",curBoy.getNo());
            if (curBoy.getNext() == first){
                break;
            }
            curBoy = curBoy.getNext();
        }

    }

    //根据用户的输入，计算出小孩出圈的顺序

    /**
     *
     * @param startNo 表示从第几个小孩开始数
     * @param countNum 表示数几下
     * @param nums 表示最初一共有多少小孩
     */
    public void countBoy(int startNo,int countNum,int nums){
        //先对数据校验
        if (first == null || startNo < 1 || startNo > nums){
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        Boy helper = first;
        //事先让helper指向单向环形链表的最后一个元素
        while (true){
            if (helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让first和helper移动k-1次到指定位置（从第k个开始报数）
        for (int j=0;j<startNo-1;j++){
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，让first和helper指针同时移动m-1次，然后出圈（m是每次数到m的人出圈）
        //这里是一个循环操作，直到圈中只有一个结点
        while (true){
            if (helper == first){
                break;
            }
            //否则让first和helper移动countNum-1次，并出圈
            for (int j=0;j<countNum-1;j++){
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的就是要出圈的小孩结点
            System.out.printf("小孩%d出圈\n",first.getNo());
            //这时让first指向的小孩结点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号为%d\n",first.getNo());

    }

}

//创建一个boy类，表示一个节点
class Boy{
    private int no; //编号
    private Boy next; //指向下一个结点 默认为空

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}