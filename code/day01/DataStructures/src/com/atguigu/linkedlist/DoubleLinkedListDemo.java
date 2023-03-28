package com.atguigu.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //测试一把
        System.out.println("双向链表的测试");
        //先创建结点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        HeroNode2 hero5 = new HeroNode2(5, "花荣", "小李广");
        HeroNode2 hero6 = new HeroNode2(6, "李逵", "黑旋风");
        HeroNode2 hero7 = new HeroNode2(7, "杨雄", "病关索");

        //创建一个双向链表
        DoubleLinkedList linkedList = new DoubleLinkedList();
//        linkedList.add(hero1);
//        linkedList.add(hero2);
//        linkedList.add(hero3);
//        linkedList.add(hero4);
//        linkedList.add(hero5);
//        linkedList.add(hero6);
//        linkedList.add(hero7);
        linkedList.addByOrder(hero1);
        linkedList.addByOrder(hero4);
        linkedList.addByOrder(hero2);
        linkedList.addByOrder(hero7);
        linkedList.addByOrder(hero5);
        linkedList.addByOrder(hero6);
        linkedList.addByOrder(hero3);


        //输出初始化链表
        linkedList.list();

        //修改
        HeroNode2 newHeroNode = new HeroNode2(4,"公孙胜","入云龙");
        linkedList.update(newHeroNode);
        System.out.println("修改后的双向链表为~~");
        linkedList.list();

        //删除
        linkedList.delete(3);
        System.out.println("删除后的双向链表为~~");
        linkedList.list();
    }
}

class DoubleLinkedList{

    //先初始化一个头结点,头结点不要动,不存放具体数据
    private HeroNode2 head = new HeroNode2(0,"","");

    public HeroNode2 getHead() { //返回头结点
        return head;
    }

    //遍历双向链表
    public void list(){
        //先判断链表是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //因为头结点不能动，因此需要一个辅助变量来遍历
        HeroNode2 temp = head;
        while (true){
            if (temp.next == null){
                break;
            }
            System.out.println(temp.next);
            temp = temp.next;
        }
    }

    //添加 默认添加到链表最后
    public void add(HeroNode2 heroNode){
        //因为head结点不能动,因此我们需要一个辅助变量temp
        HeroNode2 temp = head;
        while (true){
            if (temp.next == null){
                break;
            }
            temp = temp.next;
        }
        //形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //第二种添加英雄方式，根据英雄排名插入到指定位置
    //(如果有这个排名，则给出提示，添加失败)
    public void addByOrder(HeroNode2 heroNode){
        //因为头结点不能动 所以我们仍然通过一格辅助指针temp来遍历操作
        //因为单链表，所以我们找的temp位于添加位置的前一个位置，否则插入不了
        HeroNode2 temp = head;
        boolean flag = false;//标志添加的编号是否存在,默认为false
        while (true){
            if (temp.next == null){//说明已经到链表的最后了
                break;//不管找到不找到，都要break
            }
            if (temp.next.no > heroNode.no){//说明temp位置已经找到了
                break;
            }else if (temp.next.no == heroNode.no){//说明编号已经存在
                flag = true;
                break;
            }
            temp = temp.next;//后移遍历链表
        }
        //判断flag的值
        if (flag){
            System.out.printf("准备插入的英雄的编号%d已经存在,不能加入\n",heroNode.no);
        }else {
            //说明可以插入到链表中
            heroNode.next = temp.next;
            temp.next = heroNode;
            temp.next.pre = heroNode;
            heroNode.pre = temp;
        }

    }

    //修改结点信息,根据no来修改，即编号不能改
    //根据newHeroNode的no来修改
    public void update(HeroNode2 newHeroNode){
        //判断是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的位置
        HeroNode2 temp = head;
        boolean flag = false;//表示是否找到了
        while (true){
            if (temp.next == null){
                break;
            }
            if (temp.no == newHeroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        }else {//没有找到
            System.out.printf("没有找到编号为%d的结点,不能修改\n",newHeroNode.no);
        }

    }

    public void delete(int no){

        HeroNode2 temp = head.next;
        boolean flag = false;
        //先判断是否为空
        if (temp.next == null){
            System.out.println("链表为空");
            return;
        }
        while (true){
            if (temp == null){
                break;
            }
            if (temp.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.pre.next = temp.next;
            //如果是最后一个结点,就不需要执行下面这句话
            if (temp.next != null){
                temp.next.pre = temp.pre;
            }
        }else {
            System.out.printf("没有找到编号为%d的结点，无法删除",no);
        }
    }



}

//定义一个HeroNode 每个HeroNode对象就是一个结点
class HeroNode2{

    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;//指向后一个节点,默认为null
    public HeroNode2 pre;//指向前一个结点,默认为null

    public HeroNode2(int hNo,String hName,String hNickName){
        this.no = hNo;
        this.name = hName;
        this.nickName = hNickName;
    }

    //为了显示方便，重写toString
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName +
                '}';
    }
}