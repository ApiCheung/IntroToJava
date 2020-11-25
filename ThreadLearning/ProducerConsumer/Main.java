package ThreadLearning.ProducerConsumer;
import ThreadLearning.ThreadColor;

import javax.swing.*;
import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

import static ThreadLearning.ProducerConsumer.Main.EOF;

/**
 * @author Esmee Zhang
 * @date 11/24/20 10:55 上午
 * @projectName IntroToJava-NYU
 *
 * case for concurrency arraylist unsynchronized
 * 在这个情况下 同时可能获得的资源是buffer
 * arraylis 上面上锁即可
 *
 * 使用sychro 仍然可能interlaps
 * 使用 reentrant lock
 */
public class Main {
    public static final String EOF = "EOF";

    public static void main(String[] args) {
        //这个queen本身就是一个thread safe的
        ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<String>(6);
        //ReentrantLock bufferLock = new ReentrantLock();
        //如果线程已经又锁了，可以继续运行不需要重新获得该锁

        ExecutorService excutorService = Executors.newFixedThreadPool(6);

        MyProducer producer = new MyProducer(buffer, ThreadColor.ANSI_GREEN);
        MyConsumer c1 = new MyConsumer(buffer, ThreadColor.ANSI_PURPLE);
        MyConsumer c2 = new MyConsumer(buffer, ThreadColor.ANSI_RED);

        excutorService.execute(producer);
        excutorService.execute(c1);
        excutorService.execute(c2);

        //get something from the thread
        Future<String> future = excutorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception{

                System.out.println(ThreadColor.ANSI_CYAN + "I AM BEING PRINETED FOR THE CALLABLE CLASS");
                return "this is the callable result";
            }
        });

        try{
            System.out.println(future.get());
        }catch(ExecutionException e){
            System.out.println("smwthing went wrong");
        }catch(InterruptedException e){
            System.out.println("thread running the task was interrupted");
        }



        //程序不会自己结束需要shutdown

        excutorService.shutdown();
    }
}

class MyProducer implements Runnable{
    private ArrayBlockingQueue<String> buffer;
    private String color;
    //private ReentrantLock bufferLock;

    public MyProducer(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
        //this.bufferLock = bufferLock;
    }

    @Override
    public void run() {
        Random random = new Random();
        String[] nums = {"1", "2", "3", "4", "5"};

        for(String num : nums){
            try{
                System.out.println(color + "Adding... " + num);
                buffer.put(num);

                //bufferLock.lock();
                //try{
                //    buffer.add(num);
                //}finally {
                    //bufferLock.unlock();
                //}

                //因为producer sleep consufer 才有机会remove the data
                Thread.sleep(random.nextInt(1000));
            }catch (InterruptedException e){
                System.out.println("Producer was interrupted");
            }
        }

        System.out.println(color + "Adding EOF and existing...");

        //bufferLock.lock();
        try{
            buffer.put("EOF");
           // buffer.add("EOF");
        }catch(InterruptedException e){

        }
        //finally{
            //bufferLock.unlock();
        //}


    }
}

class MyConsumer implements Runnable{
    private ArrayBlockingQueue<String> buffer;
    private String color;
    //private ReentrantLock bufferLock;

    public MyConsumer(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
        //this.bufferLock = bufferLock;
    }

    @Override
    public void run() {
        //int counter = 0;
        while(true){
            //test if a lock is available

            //if(bufferLock.tryLock()){
            synchronized (buffer) {
                //但是两个不同的thread可能会同时拿 在一个线程suspend的时候另一个移除了
                try {
                    //Thread.sleep(1000);
                    if (buffer.isEmpty()) {

                        continue;
                    }
                    //System.out.println(color + "the counter = " + counter);
                    //counter = 0;
                    //could be suspended

                    if (buffer.peek().equals(EOF)) {
                        System.out.println(color + "Exiting");

                        break;
                    } else {
                        System.out.println(color + "Removed" + buffer.take());
                    }

                    //}catch(InterruptedException e){

                } catch (InterruptedException e) {

                    //finally {
                    // bufferLock.unlock();//不会relase the lock
                    //}
                    //}else{
                    //counter ++;

                }

//使用finally的好处 只需要unlock一次 如果 crash了 总是会unlock

            }
        }


    }
}