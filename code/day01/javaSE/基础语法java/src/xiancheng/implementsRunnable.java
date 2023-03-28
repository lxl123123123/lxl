package xiancheng;

public class implementsRunnable{
    public static void main(String[] args) {
        mThread m1 = new mThread();
        Thread m = new Thread(m1);
        m.setName("我的线程");
        m.start();
    }
}
class mThread implements Runnable{
    @Override
    public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
}
