package Array;

public class MethodTest3 {
    public static void main(String[] args) {
        ju a=new ju();
        System.out.println(a.daYin(5,5));
    }
}
class ju{
    public int daYin(int m,int n){
        for (int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                System.out.print("* ");
            }
            System.out.println();
        }
        return m*n;
    }
}
