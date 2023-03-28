package Girlfriend;

public class Test1 {
    public static void main(String[] args) {
        account2 acct = new account2();
        customer c1 = new customer(acct);
        Thread t1 = new Thread(c1);
        Thread t2 = new Thread(c1);
        t1.setName("线程一");
        t2.setName("线程二");
        t1.start();
        t2.start();
    }
}
class account2{

    private double balance=8000;
    public synchronized void insertMoney(int amt) throws Exception {
        if (balance>amt){
            balance-=amt;
            notify();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+"取钱成功，余额为"+balance);
            wait();
        }else {
            throw new Exception("您输入的有误，银行卡内没有那么多钱！");
        }
    }
}
class customer implements Runnable{
    private final account2 acct;

    public customer(account2 acct) {
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
