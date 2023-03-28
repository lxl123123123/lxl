package com.atguigu.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {

    public static void main(String[] args) {

        //进行测试
        //先创建结点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode hero5 = new HeroNode(5, "花荣", "小李广");
        HeroNode hero6 = new HeroNode(6, "李逵", "黑旋风");
        HeroNode hero7 = new HeroNode(7, "杨雄", "病关索");
        //创建链表
        SingleLinkedList linkedList = new SingleLinkedList();
        //添加到链表最末尾
//        linkedList.add(hero1);
//        linkedList.add(hero2);
//        linkedList.add(hero3);
//        linkedList.add(hero4);
        //加入按照编号顺序
        linkedList.addByOrder(hero1);
        linkedList.addByOrder(hero4);
        linkedList.addByOrder(hero2);
        linkedList.addByOrder(hero7);
        linkedList.addByOrder(hero5);
        linkedList.addByOrder(hero6);
        linkedList.addByOrder(hero3);

        System.out.println("修改前的链表情况~~");
        linkedList.list();

        //测试一把 单链表反转
        SingleLinkedListDemo.reverseList(linkedList.getHead());
        System.out.println("单链表反转后的情况~~");
        linkedList.list();

        //测试一把 单链表逆序打印
        System.out.println("单链表逆序后的情况,没有改变链表的结构~~");
        SingleLinkedListDemo.reversePrint(linkedList.getHead());


        //测试修改节点的代码
        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
        linkedList.update(newHeroNode);

        System.out.println("修改后的链表情况~~");
        linkedList.list();

        //删除一个结点
        linkedList.delete(1);
        System.out.println("删除后的链表情况~~");
        linkedList.list();

        //测试一把，统计单链表有效结点个数
        int length = SingleLinkedListDemo.getLength(linkedList.getHead());
        System.out.printf("有效结点个数为%d\n",length);

        //测试一把，测试查询单链表倒数第k个结点
        HeroNode heroNode = SingleLinkedListDemo.findIndexNode(linkedList.getHead(), 2);
        System.out.printf("倒数第2个结点为"+heroNode);

    }

    //查询单链表的倒数第k个结点(新浪面试题)
    public static HeroNode findIndexNode(HeroNode head,int k){
        HeroNode cur = head.next;
        int count = 0;
        if (head.next == null){
            System.out.println("链表为空");
            return null;
        }
        int lengthSize = SingleLinkedListDemo.getLength(head);
        if (k<0 || k>lengthSize){
            return null;
        }
        while (true){
            if (count == lengthSize-k){
                return cur;
            }
            cur = cur.next;
            count++;
        }
    }

    //将单链表进行反转(腾讯面试题)
    public static void reverseList(HeroNode head){
        //如果当前链表为空或只有一个结点，则无需反转，直接返回
        if (head.next == null || head.next.next == null){
            return;
        }
        //定义一个辅助指针(变量)，帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null;//指向当前节点[cur]的下一个节点
        HeroNode reverseHead = new HeroNode(0,"","");
        //遍历原来的链表，每遍历一个结点，就将其取出，并放在新的链表reverseHead 的最前端
        while (cur != null){
            next = cur.next;//先暂时保存当前节点的下一个结点，因为后面需要用
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;//让cur后移
        }
        //将head.next = reverseHead.next,实现单链表反转
        head.next = reverseHead.next;
    }

    //单链表逆序输出(百度面试题)
    //方式一：单链表反转然后打印，但破坏了链表的原有结构，如果别人要用你又要反转回去，浪费空间，不建议
    //方式二：将各个结点压入栈中，然后利用栈的后进先出的特点，就实现了逆序打印的效果
    public static void reversePrint(HeroNode head){
        if (head.next == null){
            return;//空链表无法打印
        }
        //不是空，创建一个栈，将各个结点压入栈中
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        while (cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }


    //获取到单链表的结点的个数(如果是带头结点的链表，需求不统计头结点)
    /**
     *
     * @param head head就是链表的头结点
     * @return  返回的是有效结点的个数
     */
    public static int getLength(HeroNode head){
        if (head.next == null){ //空链表
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next; //这里我们没有统计头结点 直接让cur等于head.next
        while (cur != null){
            length++;
            cur = cur.next;
        }
        return length;
    }

}

//定义一个SingleLinkedList 管理我们的英雄
class SingleLinkedList{
    //先初始化一个头结点,头结点不要动,不存放具体数据
    private HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead() { //返回头结点
        return head;
    }

    //添加结点到单向链表
    //思路： 当不考虑编号顺序时
    //1. 找到当前链表的最后结点
    //2. 将最后这个结点的next 指向新的节点
    public void add(HeroNode heroNode){
        //因为head结点不能动,因此我们需要一个辅助变量temp
        HeroNode temp = head;
        while (true){
            if (temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    //第二种添加英雄方式，根据英雄排名插入到指定位置
    //(如果有这个排名，则给出提示，添加失败)
    public void addByOrder(HeroNode heroNode){
        //因为头结点不能动 所以我们仍然通过一格辅助指针temp来遍历操作
        //因为单链表，所以我们找的temp位于添加位置的前一个位置，否则插入不了
        HeroNode temp = head;
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
        }

    }

    //修改结点信息,根据no来修改，即编号不能改
    //根据newHeroNode的no来修改
    public void update(HeroNode newHeroNode){
        //判断是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的位置
        HeroNode temp = head;
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

    //删除结点
    //思路
    //1.head 不能动 因此需要一个辅助结点temp找到待检测的前一个结点
    //2.
    public void delete(int no){
        HeroNode temp = head;
        boolean flag = false;
        //先判断是否为空
        if (temp.next == null){
            System.out.println("链表为空");
            return;
        }
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



    //显示链表[遍历]
    public void list(){
        //先判断链表是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //因为头结点不能动，因此需要一个辅助变量来遍历
        HeroNode temp = head;
        while (true){
            if (temp.next == null){
                break;
            }
            System.out.println(temp.next);
            temp = temp.next;
        }
    }

}


//定义一个HeroNode 每个HeroNode对象就是一个结点
class HeroNode{

    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int hNo,String hName,String hNickName){
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