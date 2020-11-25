package ThreadLearning.Counter;
import ThreadLearning.ThreadColor;
/**
 * @author Esmee Zhang
 * @date 11/23/20 10:32 下午
 * @projectName IntroToJava-NYU
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        CountDown countDown = new CountDown();
        CountdownThread t1 = new CountdownThread(countDown);
        t1.setName("Thread 1");
        CountdownThread t2 = new CountdownThread(countDown);
        t2.setName("Thread 2");

        t1.start();
        t2.start();

    }
}

class CountDown{
    //如果为全局变量 thread 有自己的copy same object passed in jvm allocated space use
    //each thread stack
    //instace jvm allocated space point on the heap
    //thread share the heap
    //没有自己的copy 同时使用heap里面的 i
//或者是将 synchronized加载methond上面
    private int i;
    public void doCountDown(){


            String color;
            switch (Thread.currentThread().getName()) {
                case "Thread 1":
                    color = ThreadColor.ANSI_CYAN;
                    break;
                case "Thread 2":
                    color = ThreadColor.ANSI_PURPLE;
                    break;
                default:
                    color = ThreadColor.ANSI_GREEN;


            }
            //synchronized static method or static object
            synchronized (this) {
                for (i = 10; i > 0; i--) {
                    //for 是多个statement 每次的 i都是新的i
                    System.out.println(color + Thread.currentThread().getName() + " : i = " + i);
                }
            }

    }
}

class CountdownThread extends Thread{
    private CountDown threadCountDown;

    public CountdownThread(CountDown countdown){
        threadCountDown = countdown;
    }

    public void run(){
        threadCountDown.doCountDown();
    }
}
