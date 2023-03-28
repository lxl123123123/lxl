package xiancheng;
/*
继承Thread方法 完成夫妻银行同一账户存钱
 */
public class Test3 {
    public static void main(String[] args) {
        account1 acct = new account1(0);
        customer1 c1 = new customer1(acct);
        customer1 c2 = new customer1(acct);
        c1.setName("线程一");
        c2.setName("线程二");
        c1.start();
        c2.start();
    }
}
class account1{
    private static double balance;

    public account1(double balance) {
        this.balance = balance;
    }

    public static synchronized void inputBalance(int amt) throws InterruptedException {
        if (amt > 0){
            balance+=amt;

            Thread.sleep(1000);

            System.out.println(Thread.currentThread().getName()+"存钱成功余额为"+balance);
        }else {
            try {
                throw new Exception("存钱错误，存入的为负数");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
class customer1 extends Thread{
    account1 acct = new account1(0);

    public customer1(account1 acct) {
        this.acct = acct;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                acct.inputBalance(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}