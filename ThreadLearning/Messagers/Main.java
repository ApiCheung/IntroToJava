package ThreadLearning.Messagers;

import java.util.Random;

/**
 * @author Esmee Zhang
 * @date 11/24/20 12:06 上午
 * @projectName IntroToJava-NYU
 * wait notify notifyall
 */
public class Main {
    public static void main(String[] args) {
        Message message = new Message();
        (new Thread(new Writer(message))).start();
        (new Thread(new Reader(message))).start();
    }
}
// arraylist 不是thread safe的

class Message{
    private String message;
    private boolean empty = true;

    public synchronized String read(){
        while(empty){
            try{
                wait();
                //具体是当前执行代码的线程进行等待，将当前线程置入预执行队列中， 并且wait（）
                //所在代码处停止执行，直到接到通知或被中断
                //并且会relase the lock it holding until other therad notify（）
            }catch(InterruptedException e){

            }
        }
        empty = true;
        notifyAll();
        return message;
    }
//没有办法改变 empty的值 如果将两个method都上锁 可以使用notifyall来解决这个问题
    public synchronized void write(String message){
        while(!empty){
            try{
                wait();
            }catch(InterruptedException e){

            }
        }
        empty = false;
        notifyAll();
        this.message = message;
    }
}

class Writer implements Runnable{
    private Message message;

    public Writer (Message message){
        this.message = message;
    }

    public void run(){
        String messages[] = {
                "Hunmpty dunpy se ton wqd",
                "miaomiaomi mmm",
                "waling kings houesre",
                "this is qkjbww again"
        };

        Random random = new Random();

        for(int i = 0; i < messages.length; i++){
            message.write(messages[i]);
            try{
                Thread.sleep(random.nextInt(2000));
            }catch(InterruptedException e){

            }
        }
        message.write("Finished");
    }
}

class Reader implements Runnable{
    private Message message;

    public Reader(Message message){
        this.message = message;
    }

    public void run(){
        Random random = new Random();
        for(String latestMessage = message.read(); !latestMessage.equals("Finished"); latestMessage = message.read()){
            System.out.println(latestMessage);

            try{
                Thread.sleep(random.nextInt(2000));
            }catch(InterruptedException e ){

            }
        }

    }
}

