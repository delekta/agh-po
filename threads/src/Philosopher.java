public class Philosopher extends Thread {
    protected String id;
    protected Fork lf, rf;
    protected int meals = 0;

    Philosopher(String id, Fork lf, Fork rf){
        this.id = id;
        this.lf = lf;
        this.rf = rf;
    }

    // zwracamy exception ze wzgledu na sleepy
    public void dine() throws InterruptedException{
        lf.lockInterruptibly();
        lf.use();
        sleep(1);
        System.out.println(id + ":podnosi lf");

        rf.lockInterruptibly();
        rf.use();
        System.out.println(id + ":podnosi rf");
        sleep(10);

        ++meals;
        rf.unlock();
        lf.unlock();
    }

    public void run(){
        try{
            while(true){
                dine();
                Thread.sleep(10);
            }
        }catch(InterruptedException e){
            System.out.println(id + ":zjadl " + meals + " razy.");
        }
    }
}
