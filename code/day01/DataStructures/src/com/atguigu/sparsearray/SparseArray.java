package com.atguigu.sparsearray;
//五子棋问题
//使用稀疏数组
//二维数组-》稀疏数组 节省存储空间,缩小程序规模  稀疏数组-》文件    实现文件保存存档退出
// 文件-》(实现文件复盘，就是上局没玩完的现在继续玩)   稀疏数组-》二维数组

public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始的二维数组 11*11
        //0： 表示没有棋子 1：表示黑子  2：表示蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        //输出原始的二维数组
        System.out.println("原始的二维数组~~");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.print(data+"  ");
            }
            System.out.println();
        }
        //将二维数组转稀疏数组
        //1.先遍历二维数组 得到非0数据个数
        int sum = 0;
        for (int i = 0;i<11;i++){
            for (int j=0;j<11;j++){
                if (chessArr1[i][j]!=0){
                    sum++;
                }
            }
        }
        System.out.println("sum="+sum);
        //创建对应的稀疏数组
        int sparseArr[][] = new int[sum+1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //遍历二维数组，将非0的值存放到稀疏数组中
        int count = 0;//用于记录是第几个非零值
        for (int i = 0;i<11;i++){
            for (int j=0;j<11;j++){
                if (chessArr1[i][j]!=0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        //输出稀疏数组的形式
        System.out.println();
        System.out.println("得到的稀疏数组如下形式~~");
        for (int[] row : sparseArr) {
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        //将稀疏数组恢复为二维数组
        //1.先读取稀疏数组的第一行 根据第一行数据创建原始的二维数组
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];

        //2.在读取稀疏数组的后几行数据后，并赋给原始的二维数组即可
        for(int i=1;i<sparseArr.length;i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        System.out.println("恢复后的二维数组~~");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.print(data+"  ");
            }
            System.out.println();
        }

    }
}
