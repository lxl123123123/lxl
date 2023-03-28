package Array;

public class xcTest2 {
    public static void main(String[] args) {
        ClerkLi clerkLi = new ClerkLi();
        ProducerLI producerLI = new ProducerLI(clerkLi);
        CustomerLI customerLI = new CustomerLI(clerkLi);
        producerLI.setName("生产者一号");
        customerLI.setName("消费者一号");
        producerLI.start();
        customerLI.start();
    }
}
class ClerkLi{
    private int productCount=0;
    public synchronized void produceProduct() {
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

    public synchronized void customProduct() {
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
}
class ProducerLI extends Thread{
    private ClerkLi clerkLi = new ClerkLi();

    public ProducerLI(ClerkLi clerkLi) {
        this.clerkLi = clerkLi;
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
            clerkLi.produceProduct();
        }
    }
}
class CustomerLI extends Thread{
    private ClerkLi clerkLi = new ClerkLi();

    public CustomerLI(ClerkLi clerkLi) {
        this.clerkLi = clerkLi;
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
            clerkLi.customProduct();
        }
    }
}