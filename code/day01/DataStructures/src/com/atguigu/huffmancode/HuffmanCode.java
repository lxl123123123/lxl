package com.atguigu.huffmancode;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {

//        String content = "i like like like java do you like a java";
        //从文件中读(IO流)
        File file = new File("D:\\code\\day01\\DataStructures\\hello.txt");
        FileReader fileReader = null;
        String content = null;
        try {
            fileReader = new FileReader(file);
            char[] chars = new char[1024];
            int read = fileReader.read(chars);
            while (read != -1){
                content = new String(chars, 0, read);
                read = fileReader.read(chars);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileReader!=null)
                    fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        byte[] bytes = content.getBytes();
        System.out.println(Arrays.toString(bytes));
        System.out.println("压缩前数组长度为:"+bytes.length); //40
        byte[] zip = huffmanZip(bytes);
        System.out.println("压缩后的结果是:"+Arrays.toString(zip));
        System.out.println("压缩后数组长度为:"+zip.length); //17
        System.out.println("压缩率为:"+(bytes.length- zip.length)*1.0 / bytes.length);
        byte[] decode = decode(huffmanCodes, zip);
//        System.out.println("原来的字符串"+new String(decode));

        //译码后写入文件
        File file1 = new File("D:\\code\\day01\\DataStructures\\hello1.txt");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file1);
            fileWriter.write(new String(decode));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter!=null)
                    fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //显示每一个字符对应的哈夫曼编码
        byte[] bytes1 = new byte[12];
        String[] strEnd = new String[12];
        int index = 0;
        int index1 = 0;
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            bytes1[index++] = entry.getKey();
            strEnd[index1++] = entry.getValue();
        }
        String end = new String(bytes1);
        String[] split = end.split("");
        Map<String,String> mapEnd = new HashMap<>();
        int k = 0;
        for (String s : split) {
            mapEnd.put(s,strEnd[k]);
            k++;
        }
        System.out.println("======每一个字符对应的赫夫曼编码如下======");
        for (Map.Entry<String, String> entry : mapEnd.entrySet()) {
            System.out.println(entry.getKey()+"->"+entry.getValue());
        }
        //分步过程
//        List<Node> list = getNodes(bytes);
//        System.out.println("list"+list);
//
//        //测试一把，创建的赫夫曼树
//        System.out.println("赫夫曼树");
//        Node huffmanTreeRoot = createHuffmanTree(list);
//        System.out.println("前序遍历");
//        huffmanTreeRoot.preOrder();
//
//        //测试一把 是否生成了对应的赫夫曼编码
//        getCodes(huffmanTreeRoot,"",stringBuilder);
//        System.out.println("生成的赫夫曼表"+huffmanCodes);
//
//        //测试一把
//        byte[] zip = zip(bytes, huffmanCodes);
//        System.out.println("zip="+Arrays.toString(zip));
//
//        //发送压缩处理过后的byte[]数组

    }

    private static List<Node> getNodes(byte[] bytes){
        //创建一个ArrayList
        List<Node> list = new ArrayList<>();
        //存储每个byte出现的次数
        Map<Byte,Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null){ // Map中还没有这个字符数据 说明第一次
                counts.put(b,1);
            }else {
                counts.put(b,count+1);
            }
        }
        for (Map.Entry<Byte,Integer> entry : counts.entrySet()){
            list.add(new Node(entry.getKey(), entry.getValue()));
        }
        return list;
    }

    private static Node createHuffmanTree(List<Node> list){
        while (list.size() > 1){
            //排序 从小到大排
            Collections.sort(list);
            //取出第一棵最小的二叉树
            Node leftNode = list.get(0);
            Node rightNode = list.get(1);
            Node parent = new Node(null,leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            list.remove(leftNode);
            list.remove(rightNode);
            list.add(parent);
        }
        return list.get(0);
    }

    //将赫夫曼编码表存放在Map<Byte,String>中
    //32->01 97->100 100->11000等等形式
    static Map<Byte,String> huffmanCodes = new HashMap<>();
    //在生成赫夫曼编码时，需要去拼接路径，定义StringBuilder存储某个叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder();
    //生成赫夫曼树对应的赫夫曼编码
    /**
     *
     * @param node 传入的结点（默认为根节点root）
     * @param code 路径： 左子节点为 0 右子节点为 1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node,String code,StringBuilder stringBuilder){
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (node != null){ //如果node == null不处理
            if (node.data == null){ //非叶子节点
                //向左递归
                getCodes(node.left,"0",stringBuilder2);
                //向右递归
                getCodes(node.right,"1",stringBuilder2);
            }else { //叶子结点
                huffmanCodes.put(node.data,stringBuilder2.toString());
            }
        }
    }

    //编写一个方法 将字符串对应的byte[]数组 通过生成的赫夫曼编码表 返回一个赫夫曼编码压缩后的byte[]
    /**
     *
     * @param bytes 这是原始的字符串对应的byte[]
     * @param huffmanCodes 这是赫夫曼编码表 是一个Map
     * @return 返回赫夫曼编码处理后的byte[]
     */
    private static byte[] zip(byte[] bytes,Map<Byte,String> huffmanCodes){
        //利用HuffmanCodes(编码表)将byte[]转成赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        //统计返回byte[] HuffmanCodeBytes长度
        int len;
        if (stringBuilder.length() % 8 ==0){
            len = stringBuilder.length() / 8;
        }else {
            len = stringBuilder.length() / 8 + 1;
        }
        //创建 存储压缩后的byte数组
        byte[] HuffmanCodeBytes = new byte[len];
        int index = 0; //记录新的压缩数组下标 因为不能用i表示了 i每次+8
        for (int i = 0; i < stringBuilder.length(); i+=8) { //因为每8位对应一个byte
            String strByte;
            if (i+8 > stringBuilder.length()){ //说明最后一个byte不够8位了
                strByte = stringBuilder.substring(i);
            }else {
                strByte = stringBuilder.substring(i,i+8);
            }
            //strByte转成一个byte数 放入到压缩后的新byte数组HuffmanCodeBytes中去
            HuffmanCodeBytes[index] = (byte) Integer.parseInt(strByte,2);
            index++;
        }
        //for循环结束后 我们就拿到了压缩后的byte[]数组
        return HuffmanCodeBytes;
    }

    //使用一个方法，将前面的方法封装起来,便于我们调用
    //传进来的参数是初始的字节数组 返回的是经过赫夫曼编码处理后的字节数组(压缩后的数组)
    private static byte[] huffmanZip(byte[] bytes){
        List<Node> list = getNodes(bytes);
        Node huffmanTreeRoot = createHuffmanTree(list);
        getCodes(huffmanTreeRoot,"",stringBuilder);
        byte[] zip = zip(bytes, huffmanCodes);
        return zip;
    }

    //完成数据的解码(将一个byte转成二进制字符串 然后根据赫夫曼编码还原出原来的字符串)
    /**
     *
     * @param b 将一个byte转成一个二进制字符串
     * @return
     */
    private static String byteToBitString(boolean flag,byte b){
        //使用变量保存b
        int temp = b; //将b转成int
        //如果是正数还存在补高位
        if (flag){
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if (flag){
            return str.substring(str.length() - 8);
        }else {
            return str;
        }
    }

    //编写一个方法 完成对压缩数据的解码
    private static byte[] decode(Map<Byte,String> huffmanCodes,byte[] huffmanBytes){
        //先得到huffmanBytes对应的二进制字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag,b));
        }
        //把字符串按照指定的赫夫曼编码进行解码
        //将赫夫曼编码表进行调换 因为反向查询 100->a
        Map<String,Byte> map = new HashMap<>();
        for (Map.Entry<Byte,String> entry : huffmanCodes.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }
        List<Byte> list = new ArrayList();
        for (int i = 0; i < stringBuilder.length();) {
            int count = 1;
            boolean flag = true;
            Byte b = null;

            while (flag){
                String key = stringBuilder.substring(i,i+count);
                b = map.get(key);
                if (b == null){ //没查到
                    count++;
                }else {
                    flag = false; //匹配到了
                }
            }
            list.add(b);
            i+=count;
        }
        //当for循环结束后 我们list中就存放了所有的字符 i like like like java do you like a java的AsCall码
        //把list中的数据放入byte[]中并返回
        byte b[] = new byte[list.size()];
        for (int i = 0; i <b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

}
//创建Node 带数据和权值
class Node implements Comparable<Node>{
    Byte data; //存放数据本身 比如‘a’ ==> 97  ' ' ==> 32
    int weight; //存放权值 表示字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        //从小到大排
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }
}
