package frequentClass;
/*
枚举类的使用
 */

public class Test {
    public static void main(String[] args) {
        Season spring = Season.SPRING;
        System.out.println(spring);
        Season[] value = Season.values();
        for (int i = 0; i < value.length; i++) {
            System.out.println(value[i]);
            System.out.println("***********");
            value[i].show();
        }
        System.out.println("********");
        Season winder = Season.valueOf("WINTER");
        System.out.println(winder);
    }
}

interface Me{
    void show();
}
enum Season implements Me{
    SPRING("春天", "春暖花开"){
        @Override
        public void show() {
            System.out.println("我喜欢春天");
        }
    },
    SUMMER("夏天", "夏日炎炎"){
        @Override
        public void show() {
            System.out.println("我喜欢夏天");
        }
    },
    AUTUMN("秋天", "秋高气爽"){
        @Override
        public void show() {
            System.out.println("我喜欢秋天");
        }
    },
    WINTER("冬天", "冰天雪地"){
        @Override
        public void show() {
            System.out.println("我喜欢冬天");
        }
    };
    private final String name;
    private final String describe;

    private Season(String name, String describe) {
        this.name = name;
        this.describe = describe;
    }



//    @Override
//    public String toString() {
//        return "Season{" +
//                "name='" + name + '\'' +
//                ", describe='" + describe + '\'' +
//                '}';
//    }
}
