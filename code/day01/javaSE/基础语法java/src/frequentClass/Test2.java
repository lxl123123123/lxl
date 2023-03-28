package frequentClass;
/*
将一个字符串反转
 */
public class Test2 {
    public static void main(String[] args) {
        Test2 test2 = new Test2();
        String str0 = test2.get("abcdefg",2,5);
        System.out.println(str0);
    }
//    public String get(String str){
//        char[]arr=str.toCharArray();
//        for (int i = 0,j=str.length()-1; i<j; i++,j--) {
//            char temp = arr[i];
//            arr[i]=arr[j];
//            arr[j]=temp;
//        }
//        String Str1 = new String(arr);
//        return Str1;
//    }
      public String get(String str,int beginIndex,int endIndex){
          char[] chars = str.toCharArray();
          for (int i = beginIndex,j=endIndex; i < j ; i++,j--) {
              char temp = chars[i];
              chars[i] = chars[j];
              chars[j] = temp;
          }
          String str1 = new String(chars);
          return str1;
      }
}
/*
    字符串指定部分反转
    public class Test2 {
    public static void main(String[] args) {
        Test2 test2 = new Test2();
        String str0 = test2.get("abcdefg",2,5);
        System.out.println(str0);
    }
    public String get(String str,int beginIndex,int endIndex){
        char[]arr=str.toCharArray();
        for (int i = beginIndex,j=endIndex; i<j; i++,j--) {
            char temp = arr[i];
            arr[i]=arr[j];
            arr[j]=temp;
        }
        String Str1 = new String(arr);
        return Str1;
    }
}
 */