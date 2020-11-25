package ThreadLearning.LiveLockPoliteWorker;

/**
 * @author Esmee Zhang
 * @date 11/25/20 12:08 下午
 * @projectName IntroToJava-NYU
 *
 * sout这个方法在线程执行过程中有可能是 会被suspended
 *
 *
 */
public class Main {
    public static void main(String[] args) {
        final Worker worker1 = new Worker("Worker 1", true);
        final Worker worker2 = new Worker("Worker 2", true);

        final SharedResource sharedResource = new SharedResource(worker1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                worker1.work(sharedResource, worker2);
            }
        }).start();

        new Thread((new Runnable() {
            @Override
            public void run() {
                worker2.work(sharedResource, worker1);
            }
        })).start();
    }


}
