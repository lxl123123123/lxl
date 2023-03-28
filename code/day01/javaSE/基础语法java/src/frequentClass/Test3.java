package frequentClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test3 {
    public static void main(String[] args) {
        String s1 = "1989-2-4";
        String s2 = "1990-1-14";
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;

        {
            try {
                date1 = sdf1.parse(s2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        Date date=null;
        {
            try {
                date = sdf1.parse(s1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
//        long  t0 = ((date1.getTime()-data.getTime())/(1000*60*60*24)+1);
        Calendar calendar = Calendar.getInstance();
        Calendar calendar1 = Calendar.getInstance();
        calendar.setTime(date1);
        calendar1.setTime(date);
        int i = calendar.get(Calendar.DAY_OF_YEAR);
        int j = calendar1.get(Calendar.DAY_OF_YEAR);
        System.out.println(i);
        System.out.println(j);
        int t0 = (i+j+365);
        My m1 = new My();
        m1.get(t0);
    }
}
class My{
    public void get(int  t1){
        if (t1 % 5 == 1) {
            System.out.println("今天打渔");
        }
        else if(t1%5==2){
            System.out.println("今天打渔");
        }
        else if(t1%5==3){
            System.out.println("今天打渔");
        }
        else if(t1%5==4){
            System.out.println("今天晒网");
        }
        else if(t1%5==0){
            System.out.println("今天晒网");
        }
    }

}