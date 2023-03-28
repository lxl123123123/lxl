package Array;

public class MethodTest6 {
    public static void main(String[] args) {
        Data data=new Data();
        data.m=10;
        data.n=20;
        data.swap(data);
        System.out.println(data.m+","+data.n);
    }
}
class Data{
    int m;
    int n;
    public void swap(Data data){
        int temp= data.m;
        data.m= data.n;
        data.n=temp;
    }
}
