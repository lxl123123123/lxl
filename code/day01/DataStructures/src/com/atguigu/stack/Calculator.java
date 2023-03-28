package com.atguigu.stack;


public class Calculator {

    public static void main(String[] args) {

        //根据前面老师的思路完成表达式的运算
        String expression = "70+201*6-4";
        //创建两个栈，数栈，符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operaStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int opera = 0;
        int res = 0;
        char ch = ' '; //将每次扫描得到char保存到ch
        String keepNum = "";//用于拼接多位数的
        //开始while循环扫描
        while (true){
            //依次得到expression的每一个字符
            ch = expression.substring(index,index+1).charAt(0);
            //判断ch是什么，然后做相应的逻辑
            if (operaStack.isOpera(ch)){ //如果是运算符
                if (!operaStack.isEmpty()){ //如果符号栈不为空，即符号栈中有元素
                    if (operaStack.priority(ch) <= operaStack.priority(operaStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        opera = operaStack.pop();
                        res = numStack.cal(num1, num2, opera);
                        //把运算的结果入数栈 把当前操作符入符号栈
                        numStack.push(res);
                        operaStack.push(ch);
                    }else {//如果优先级大 那就直接加
                        operaStack.push(ch);
                    }
                }else { //如果符号栈为空，那就直接进
                    operaStack.push(ch);
                }
            }else { //如果是数
//                numStack.push(ch - 48);
                //思路分析
                //1.当处理多位数时，不能发现是一个数字就直接入栈，因为他可能是多位数
                //2.在处理数时，需要向expression表达式的index后再看一位，如果是数就扫描，是符号才入栈
                //3.因此我们要定义一个字符串变量用于拼接
                //处理多位数
                keepNum += ch;

                //如果ch已经是expression的最后一位了 就直接入栈
                if (index == expression.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                    //判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则入栈
                    if (operaStack.isOpera(expression.substring(index+1,index+2).charAt(0))){
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = ""; //KeepNum置空
                    }
                }

            }
            //让index + 1,并判断是否扫描到字符串最后
            index++;
            if (index == expression.length()){
                break;
            }
        }
        //循环退出，扫描完毕，现在就顺序的从数栈和符号栈中pop出元素，并运行
        while (true){
            //如果符号栈为空，则表示计算到最后结果,此时数栈中也就只有一个结果
            if (operaStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            opera = operaStack.pop();
            res = numStack.cal(num1, num2, opera);//把运算的结果入数栈
            numStack.push(res);
        }
        //把最终的结果pop出栈
        System.out.printf("表达式%s的结果为%d\n",expression,numStack.pop());
    }

}

//定义一个ArrayStack2 表示栈
class ArrayStack2{
    private int maxSize; //栈的大小
    private int[] stack; //数组，数组模拟栈，数据就放在该数组中
    private int top = -1; //top表示栈顶，初始化为-1

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //增加一个方法 可以返回当前栈顶元素的值 但不是真正的pop
    public int peek(){
        return stack[top];
    }

    //栈满
    public boolean isFull(){
        return top == maxSize-1;
    }
    //栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈
    public void push(int value){
        if (isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈,将栈顶的数据返回
    public int pop(){
        if (isEmpty()){
            //抛出异常
            throw new RuntimeException("栈空，没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈
    public void list(){
        if (isEmpty()){
            System.out.println("栈空，没有数据");
            return;
        }
//        int k = 0;
//        k = top;
        for (int i=top;i>-1;i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
//        while (true){   //不能直接用top而用k的原因是top的值会被改变
//            if (k == -1){
//                break;
//            }
//            System.out.printf("stack[%d]=%d\n",k,stack[k]);
//            k--;
//        }
    }

    //返回运算符的优先级，优先级是程序员来确定，优先级使用数字表示
    //数字越大，则优先级就越高
    public int priority(int opera){
        if (opera == '*' || opera == '/'){
            return 1;
        }else if (opera == '+' || opera == '-'){
            return 0;
        }else {
            return -1; //假定当前运算式中只有+ - * /
        }
    }

    //判断是不是一个运算符
    public boolean isOpera(char val){
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1,int num2,int opera){
        int res = 0;//用于存放计算的结果
        switch (opera){
            case '+':
                res = num1+num2;
                break;
            case '-':
                res = num2-num1;
                break;
            case '*':
                res = num1*num2;
                break;
            case '/':
                res = num2/num1;
                break;
            default:
                break;
        }
        return res;
    }
}