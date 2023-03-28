package Array;

public class xcTest1 {
    public static void main(String[] args) {
        Account acct = new Account(0);
        CustomerLi c1 = new CustomerLi(acct);
        Thread t1 = new Thread(c1);
        Thread t2 = new Thread(c1);
        t1.setName("线程一");
        t2.setName("线程二");
        t1.start();
        t2.start();
    }
}
class Account{
    private double balance = 0;

    public Account(double balance) {
        this.balance = balance;
    }

    public synchronized void insertMoney(double amt) {
        if (amt>0){
            balance+=amt;
            notify();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+"存钱成功余额为:"+balance);
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            throw new RuntimeException("您存入的钱为负数");
        }
    }
}
class CustomerLi implements Runnable{
    private Account account = new Account(0);

    public CustomerLi(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            account.insertMoney(1000);
        }
    }
}