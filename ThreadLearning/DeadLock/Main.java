package ThreadLearning.DeadLock;

import org.w3c.dom.ls.LSOutput;

/**
 * @author Esmee Zhang
 * @date 11/24/20 11:45 下午
 * @projectName IntroToJava-NYU
 */
public class Main {
//2 more thread competing 2more locks
    public static Object lock1 = new Object();
    public static Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread1().start();
        new Thread2().start();
    }


    private static class Thread1 extends Thread {
        public void run() {
            synchronized (lock1) {
                System.out.println("Thread 1 has lcok1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
                System.out.println("Thread 1 waiting for lock 2");

                synchronized (lock2) {
                    System.out.println("thread 1 has lock 1 and lock 2");
                }
                System.out.println("thread 1 releadsed lock2");
            }
            System.out.println("thread 1 realed lock 1 existing");
        }


    }

    private  static class Thread2 extends Thread{
        public void run(){
            synchronized (lock1){
                System.out.println("thread 2 has lock 1");
                try{
                    Thread.sleep(100);
                }catch(InterruptedException e){

                }
                System.out.println("thread 2 waiting for lock2");
                synchronized (lock2){
                    System.out.println("thread 2 has lock2 and lock1 ");
                }
                System.out.println("thread 2 released lock 2");
            }
            System.out.println("thread 2 relsed exsiting");
        }
    }
}
