package ThreadLearning.ChanllengeOneTwo;

/**
 * @author Esmee Zhang
 * @date 11/25/20 12:51 下午
 * @projectName IntroToJava-NYU
 */
public class Main {
    public static void main(String[] args) {


        final BankAccount account = new BankAccount(1000, "12345-678");

        Thread trThread1 = new Thread(){
            public void run(){
                account.deposit(300);
                account.withdraw(50);
            }
        };

        Thread trThread2 = new Thread(){
            public void run(){
                account.deposit(203.75);
                account.withdraw(100);
            }
        };

        trThread1.start();
        trThread2.start();



    }
}
