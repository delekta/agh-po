public class World {
    public static void main(String[] args) {
        Fork f1 = new Fork();
        Fork f2 = new Fork();
        Fork f3 = new Fork();
        Fork f4 = new Fork();
        Fork f5 = new Fork();

        Philosopher p1 = new Philosopher("P1", f1, f2);
        Philosopher p2 = new Philosopher("P2", f2, f3);
        Philosopher p3 = new Philosopher("P3", f3, f4);
        Philosopher p4 = new Philosopher("P4", f4, f5);
        // zmieniamy widelce miejscami
        Philosopher p5 = new Philosopher("P5", f1, f5);


        // wątek odpala runa
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();

        try{
            Thread.sleep(5000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }


        p1.interrupt();
        p2.interrupt();
        p3.interrupt();
        p4.interrupt();
        p5.interrupt();

        System.out.println(f1.getNumberOfUses() + f2.getNumberOfUses() + f3.getNumberOfUses() + f4.getNumberOfUses() + f5.getNumberOfUses());
    }
}
