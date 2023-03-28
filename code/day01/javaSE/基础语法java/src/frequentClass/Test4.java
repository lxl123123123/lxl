package frequentClass;
/*
寻找两个子串中的最大相同子串
 */

public class Test4 {
    public static void main(String[] args) {
        Test4 test4 = new Test4();
        String myStr =  test4.get("aadfmakjfahelloacj","aaffafafauuhellopp");
        System.out.println(myStr);
    }
    public String get(String str1,String str2){
        if (str1!=null&&str2!=null){
            String maxStr = (str1.length()>=str2.length())?str1:str2;
            String minStr = (str1.length()<str2.length())?str1:str2;
            int length = minStr.length();
            for (int i = 0; i < length; i++) {
                for (int x=0,y=length-i;y<length;x++,y++){
                    String subStr = minStr.substring(x,y);
                    if (maxStr.contains(subStr)){
                        return subStr;
                    }
                }
            }
        }
        return null;
    }
//    public String get(String str1,String str2){
//        if ( str1!=null && str2!=null ){
//            String maxStr =  (str1.length() >= str2.length()) ? str1 : str2;
//            String minStr =  (str1.length() < str2.length()) ? str1 : str2;
//            int length = minStr.length();
//            for (int i = 0; i < length; i++) {
//                for (int x=0,y=length-i;y<length;x++,y++){
//                    String subStr = minStr.substring(x,y);
//                    if (maxStr.contains(subStr)){
//                        return subStr;
//                    }
//                }
//            }
//        }
//        return null;
//    }
}
