package Array;
import java.util.Scanner;

public class ArrayTest1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入学生人数:");
        int n = scan.nextInt();
        int[] scores = new int[n];
        for(int i=0;i< scores.length;i++){
            System.out.println("请输入第"+(i+1)+"个学生成绩");
            scores[i]=scan.nextInt();
        }
        int max=0;
        for(int i=0;i< scores.length;i++){
            if(max<scores[i]){
                max=scores[i];
            }
        }
        char level;
        for(int i=0;i<scores.length;i++){
            if(max-scores[i]<=10){
                level='A';
            }else if(max-scores[i]<=20){
                level='B';
            }else if(max-scores[i]<=30){
                level='C';
            }else{
                level='D';
            }
            System.out.println("student "+i+" score is "+scores[i]+",grade is"+level);
        }
    }
}
