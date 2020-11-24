package ThreadLearning;

import static ThreadLearning.ThreadColor.ANSI_RED;

/**
 * @author Esmee Zhang
 * @date 11/23/20 9:34 下午
 * @projectName IntroToJava-NYU
 */
public class MyRunnable implements Runnable {
    //lambda use thread
    @Override
    public void run(){
        System.out.println(ANSI_RED + "hello form runnable");
    }
}
