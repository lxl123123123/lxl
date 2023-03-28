package xiancheng;

public class extendsThread {
    public static void main(String[] args) {
        myThread m1 = new myThread();
        myThread m2 = new myThread();
        m1.setName("线程一");
        m2.setName("线程二");
        m1.start();
        m2.start();
        System.out.println(m1.isAlive());
        System.out.println(m2.isAlive());
    }
}
class myThread extends Thread{
    public void run(){
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }
}
