package xiancheng;

public class repeatProducerTest {
    public static void main(String[] args) {
        Clerk1 clerk0 = new Clerk1(0);
        myProducer p1 = new myProducer(clerk0);
        myCustomer c1 = new myCustomer(clerk0);
        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(c1);
        t1.setName("生产者一号");
        t2.setName("消费者一号");
        t1.start();
        t2.start();
    }
}
class Clerk1{
    private int productCount;

    public Clerk1(int productCount) {
        this.productCount = productCount;
    }

    public void produceProduct() {
        synchronized (this){
            if (productCount<20){
                productCount++;
                System.out.println(Thread.currentThread().getName()+"开始生产第"+productCount+"个产品");
                notify();
            }else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
//        if (productCount<20){
//            productCount++;
//            System.out.println(Thread.currentThread().getName()+"开始生产第"+productCount+"个产品");
//        }else {
////            wait();
//        }
    }

    public void customProduct() {
        synchronized (this){
            if (productCount>0){
                System.out.println(Thread.currentThread().getName()+"开始消费第"+productCount+"个产品");
                productCount--;
                notify();
            }else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
//        if (productCount>0){
//            System.out.println(Thread.currentThread().getName()+"开始消费第"+productCount+"个产品");
//            productCount--;
//        }else {
////            wait();
//        }
    }
}
class myProducer implements Runnable{
    private Clerk1 clerk0;

    public myProducer(Clerk1 clerk0) {
        this.clerk0 = clerk0;
    }

    @Override
    public void run() {
        System.out.println("生产者1号开始生产产品");
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk0.produceProduct();
        }
    }
}
class myCustomer implements Runnable{
    private Clerk1 clerk0;

    public myCustomer(Clerk1 clerk0) {
        this.clerk0 = clerk0;
    }
    @Override
    public void run() {
        System.out.println("消费者1号开始消费产品");
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk0.customProduct();
        }
    }
}