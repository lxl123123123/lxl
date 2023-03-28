package xiancheng;

public class repeatTest1 {
    public static void main(String[] args) {
        myAccount acct = new myAccount(0);
        husbandAndWife h1 = new husbandAndWife(acct);
        Thread t1 = new Thread(h1);
        Thread t2 = new Thread(h1);
        t1.setName("我的线程");
        t2.setName("我老婆的线程");
        t1.start();
        t2.start();
    }
}
class myAccount{
    private int balance=0;

    public myAccount(int balance) {
        this.balance = balance;
    }

    public void insertMoney(int amt) throws Exception {
        synchronized (this){
//            notify();
            if (amt>0){
                balance+=amt;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName()+":存钱成功，余额为"+balance);
//                wait();
            }else {
                throw new Exception("存钱失败，您存的钱为负数");
            }
        }
//        if (amt>0){
//            balance+=amt;
//
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            System.out.println(Thread.currentThread().getName()+":存钱成功，余额为"+balance);
//        }else {
//            throw new Exception("存钱失败，您存的钱为负数");
//        }
    }
}
class husbandAndWife implements Runnable{
    private myAccount acct;

    public husbandAndWife(myAccount acct) {
        this.acct = acct;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                acct.insertMoney(1000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
