package frequentClass;

public class Test1 {
    public static void main(String[] args) {
        Test1 test1 = new Test1();
        int sum = test1.getCount("acbmjhk","ab");
        System.out.println(sum);
    }
//    public int getCount(String myStr,String yourStr){
//        int myLength=myStr.length();
//        int yourLength=yourStr.length();
//        int count=0;
//        for (int i=0;i<myLength-yourLength-1;i++){
//            if(myStr.substring(i,i+yourLength).equals(yourStr)) {
//                count++;
//            }
//        }
//        return count;
//    }
    public int getCount(String myStr,String yourStr){
        int myLength = myStr.length();
        int yourLength = yourStr.length();
        int count = 0;
        for (int i = 0; i < myLength-yourLength-1; i++) {
            if (myStr.substring(i,i+yourLength).equals(yourStr)){
                count++;
            }
        }
        return count;
    }
}

