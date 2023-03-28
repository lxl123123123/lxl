package xiancheng;

public class Test2 {
    public static void main(String[] args) {
        myXianCheng my = new myXianCheng();
        Thread t1 = new Thread(my);
        Thread t2 = new Thread(my);
        t1.setName("线程一");
        t2.setName("线程二");
        t1.start();
        t2.start();
    }
}
class myXianCheng implements Runnable{

    @Override
    public void run() {
//        synchronized (this){
            for (int i = 0; i < 100; i++) {
                synchronized (this){
                    notify();
                    if(i % 2==0){
                        System.out.println(Thread.currentThread().getName()+i);
                    }
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
//                if(i % 2==0){
//                    System.out.println(Thread.currentThread().getName()+i);
//                }
            }
//        }
//        for (int i = 0; i < 100; i++) {
//            if(i % 2==0){
//                System.out.println(Thread.currentThread().getName()+i);
//            }
//        }
    }
}
