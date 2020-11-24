package ThreadLearning;

import static ThreadLearning.ThreadColor.ANSI_GREEN;
import static ThreadLearning.ThreadColor.ANSI_PURPLE;

/**
 * @author Esmee Zhang
 * @date 11/23/20 9:11 下午
 * @projectName IntroToJava-NYU
 */
public class Main {
    public static void main(String[] args){
        System.out.println(ANSI_PURPLE + "hello tha main thread");

        Thread anotherThread = new AnotherThread();
        anotherThread.start();

        Thread runnableThread = new Thread(new MyRunnable());
        runnableThread.start();

        //another method to run a thread annoymous

        new Thread(){
            public void run(){
                System.out.println(ANSI_GREEN + "hello from the annoymous class thread");
            }
        }.start();

        ////use lambda
        Thread lambdaThread = new Thread(()->{
            System.out.println("hello lambda ");
        });
        lambdaThread.run();

        System.out.println(ANSI_PURPLE + "Hello again from the main thread");





    }




}
