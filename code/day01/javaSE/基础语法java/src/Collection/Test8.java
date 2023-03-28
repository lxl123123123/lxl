package Collection;

import java.util.ArrayList;
import java.util.List;

public class Test8 {
    public static void main(String[] args) {
        //情况一说明：当两个集合泛型的类型为子父类关系时，这两个集合本身并没有子父类关系
        //,而是并列关系,所以他们不能相互赋值
        List<Object> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
//        list1 = list2;
        //情况二说明：当这两个集合为子父类关系且他们的泛型类型相同时
        // ，此时他们是可以相互赋值的
        List<String> list3 = new ArrayList<>();
        ArrayList<String> list4 = new ArrayList<>();
        list3 = list4;
    }
}
