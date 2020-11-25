package ThreadLearning;

import static ThreadLearning.ThreadColor.*;

/**
 * @author Esmee Zhang
 * @date 11/23/20 9:11 下午
 * @projectName IntroToJava-NYU
 */
public class Main {
    public static void main(String[] args){
        //interrupt another
        System.out.println(ANSI_PURPLE + "hello tha main thread");

        Thread anotherThread = new AnotherThread();
        anotherThread.setName("== Another Thread ==");
        anotherThread.start();

        Thread runnableThread = new Thread(new MyRunnable());
        runnableThread.start();

        //anotherThread.interrupt();

        //another method to run a thread annoymous

        new Thread(() -> {
            System.out.println(ANSI_GREEN + "hello from the annoymous class thread");
            try{
                //等 another thread 结束
                anotherThread.join();
                System.out.println(ANSI_RED + "another thread terminated, or timed out");
            }catch(InterruptedException e){
                System.out.println(ANSI_RED + "i coul'd wait i was interrupted");
            }
        }).start();

        ////use lambda
        Thread lambdaThread = new Thread(()->{
            System.out.println("hello lambda ");
        });
        lambdaThread.run();

        System.out.println(ANSI_PURPLE + "Hello again from the main thread");





    }




}
