package ThreadLearning.LiveLockPoliteWorker;

/**
 * @author Esmee Zhang
 * @date 11/25/20 12:08 下午
 * @projectName IntroToJava-NYU
 */
public class SharedResource {

    private Worker owner;

    public SharedResource(Worker owner) {
        this.owner = owner;
    }

    public Worker getOwner() {
        return owner;
    }

    public synchronized void setOwner(Worker owner) {
        this.owner = owner;
    }
}
