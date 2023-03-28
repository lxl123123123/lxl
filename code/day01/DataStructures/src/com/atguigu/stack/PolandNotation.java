package com.atguigu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {

    public static void main(String[] args) {

        //中缀转后缀  存入ArrayList中
        String expression = "1+((2+3)*4)-5";
        List<String> list = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的list="+list);
        List<String> list1 = parseSuffixExpressionList(list);
        System.out.println("最终的后缀表达式为list="+list1);
        System.out.printf("expression表达式最终结果为%d\n",calculate(list1));


//        //先定义一个后缀表达式
//        //(3+4)*5-6
//        //说明，为了方便，后缀表达式的数字和符号使用空格隔开
//        String suffixExpression = "3 4 + 5 * 6 -";
////        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";//76
//        //先将后缀表达式放在ArrayList中
//        //然后将ArrayList传给一个方法 遍历ArrayList配合栈 完成计算
//        List<String> list = getListString(suffixExpression);
//        System.out.println("list="+list);
//
//        int res = calculate(list);
//        System.out.printf("最终的结果为%d\n",res);

    }

    //将一个中缀表达式，依次将数据和运算符放入ArrayList中
    public static List<String> toInfixExpressionList(String expression){
        List<String> list = new ArrayList<>();
        int i = 0; //索引指针，遍历中缀表达式
        String str; //做对多位数的拼接
        char c; //每遍历到一个字符，就放入到c中
        do {
            //如果c是一个非数字，我需要加入到list     (0~9 对应AsCall码48~57)
            if ((c=expression.charAt(i)) < 48 || (c=expression.charAt(i)) > 57){
                list.add(""+c);
                i++; //i指针后移
            }else {//如果是一个数,需要考虑多位数
                str = "";//先将str置成空串
                while (i<expression.length() && (c=expression.charAt(i)) >=48 && (c=expression.charAt(i)) <=57 ){
                    str+=c;
                    i++;
                }
                list.add(str);
            }
        }while (i < expression.length());
        return list;
    }

    //将中缀表达式的list转换为后缀表达式的list
    public static List<String> parseSuffixExpressionList(List<String> ls){
        //定义两个栈
        Stack<String> s1 = new Stack<>();
        List<String> list = new ArrayList<>();
        //遍历传进来的ls集合
        for (String item : ls) {
            //如果是一个数，就加入到list集合中
            if (item.matches("\\d+")){
                list.add(item);
            }else if (item.equals("(")){
                s1.push(item);
            }else if (item.equals(")")){
                while (!s1.peek().equals("(")){
                    list.add(s1.pop());
                }
                s1.pop();//将（ 弹出s1栈,即括号抵消
            }else {
                //当item的优先级小于等于栈顶运算符
                while (s1.size() !=0 && Operation.getValue(item) <= Operation.getValue(s1.peek())){
                    list.add(s1.pop());
                }
                s1.push(item);
            }
        }

        //将s1中剩余的的元素加入到list中
        while (s1.size() != 0){
            list.add(s1.pop());
        }

        return list;//此时的list即为最终的后缀表达式(由中缀转为的最终结果)

    }

    //将一个后缀表达式，依次将数据和运算符放入ArrayList中
    public static List<String> getListString(String suffixExpression){
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    //完成对后缀表达式的运算
    public static int calculate(List<String> list){
        //创建一个栈
        Stack<String> stack = new Stack<>();
        //遍历list
        for (String item : list) {
            //这里使用正则表达式来取出数
            if (item.matches("\\d+")){//匹配的是多位数
                //入栈
                stack.push(item);
            }else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")){
                    res = num1 + num2;
                }else if (item.equals("-")){
                    res = num1 - num2;
                }else if (item.equals("*")){
                    res = num1 * num2;
                }else if (item.equals("/")){
                    res = num1 / num2;
                }else {
                    throw new RuntimeException("运算符有误");
                }
                //将res入栈
                stack.push(res + "");
            }
        }
        //最后留在stack中的就是最后的结果
        return Integer.parseInt(stack.pop());
    }

}

class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法，返回对应的优先级
    public static int getValue(String operation){
        int res = 0;
        switch (operation){
            case "+":
                res = ADD;
                break;
            case "-":
                res = SUB;
                break;
            case "*":
                res = MUL;
                break;
            case "/":
                res = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return res;
    }
}