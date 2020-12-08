import java.util.concurrent.locks.ReentrantLock;

public class Fork extends ReentrantLock {
    private int numberOfUses = 0;
    public void use(){
        numberOfUses++;
    }

    public int getNumberOfUses(){
        return numberOfUses;
    }
}
