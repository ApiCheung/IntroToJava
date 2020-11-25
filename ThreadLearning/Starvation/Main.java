package ThreadLearning.Starvation;
import ThreadLearning.ThreadColor;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Esmee Zhang
 * @date 11/25/20 11:35 上午
 * @projectName IntroToJava-NYU
 */
public class Main {
    //object 是 static的 only 1 instance
    private static ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        Thread y1 = new Thread(new Worker(ThreadColor.ANSI_PURPLE), "Priority 10");
        Thread y2 = new Thread(new Worker(ThreadColor.ANSI_BLUE), "Priority 8");
        Thread y3 = new Thread(new Worker(ThreadColor.ANSI_GREEN), "Priority 6");
        Thread y4 = new Thread(new Worker(ThreadColor.ANSI_CYAN), "Priority 4");
        Thread y5 = new Thread(new Worker(ThreadColor.ANSI_RED), "Priority 2");


        //只是suggest

        //fair lock first come first served
        y1.setPriority(10);
        y2.setPriority(8);
        y3.setPriority(6);
        y4.setPriority(4);
        y5.setPriority(2);

        y4.start();
        y2.start();
        y3.start();
        y1.start();
        y5.start();
    }

    private static class Worker implements Runnable{
        private int runCount = 1;
        private String threadColor;

        public Worker(String threadColor) {
            this.threadColor = threadColor;
        }

        @Override
        public void run() {
            for(int i = 0; i < 100; i++){
                lock.lock();
                try{
                    System.out.format(threadColor + "%s: runCount = %d\n", Thread.currentThread().getName(), runCount++);
                }finally{
                    lock.unlock();
                }
                //synchronized (lock){
                //    System.out.format(threadColor + "%s: runCount = %d\n", Thread.currentThread().getName(), runCount++);
                    //excute critial section of code
               // }
            }
        }
    }
}
