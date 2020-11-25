package ThreadLearning.SayHelloMoreDeadLock;

import java.lang.management.PlatformLoggingMXBean;

/**
 * @author Esmee Zhang
 * @date 11/25/20 12:10 上午
 * @projectName IntroToJava-NYU
 *
 * 为什么会有这种情况
 * t1 用hello的时候 lock 在了jane上面
 * t2 用hello的时候 lock 在了john上面
 *
 *
 * t1 再跑 say back to john object 但是t2此时hold john
 */
public class Main {
    public static void main(String[] args) {
        final PolitePerson jane = new PolitePerson("Jane");
        final PolitePerson john = new PolitePerson("John");

        new Thread(new Runnable() {
            @Override
            public void run() {
                jane.sayHello(john);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                john.sayHello(jane);
            }
        }).start();
    }

    static class PolitePerson{
        private  final String name;

        public PolitePerson(String name) {
            this.name = name;

        }

        public String getName() {
            return name;
        }

        public synchronized void sayHello(PolitePerson person){
            System.out.format("%s: %s" + "has said hello to me %n", this.name, person.getName());
            person.sayHelloBack(this);
        }

        public synchronized void sayHelloBack(PolitePerson person){
            System.out.format("%s: %s" + "has said hello back to me %n", this.name, person.getName());
        }
    }
}

