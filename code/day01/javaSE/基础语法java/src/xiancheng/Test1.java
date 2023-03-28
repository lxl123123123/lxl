package xiancheng;
/*
实现Runnable方法 完成夫妻银行同一账户存钱
 */
public class Test1 {
    public static void main(String[] args) {
        account acct = new account(0);
        customer c = new customer(acct);
        Thread t1 = new Thread(c);
        Thread t2 = new Thread(c);
        t1.setName("线程一");
        t2.setName("线程二");
        t1.start();
        t2.start();
    }
}
class account{
    private double balance;

    public account(int balance) {
        this.balance = balance;
    }
    public void inputBalance(int amt) {
        synchronized (this) {
            if (amt > 0) {
                balance += amt;

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + "存钱成功账户余额为" + balance);
            } else {
                throw new RuntimeException("您存入的钱为负数");
            }
        }
    }
}
class customer implements Runnable{
    private account acct = new account(0);

    public customer(account acct) {
        this.acct = acct;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            acct.inputBalance(1000);
        }
    }
}
