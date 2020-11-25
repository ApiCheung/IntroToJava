package ThreadLearning.ChanllengeOneTwo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Esmee Zhang
 * @date 11/25/20 12:48 下午
 * @projectName IntroToJava-NYU
 *
 * concurrance
 */
public class BankAccount {

    private double balance;
    private String accountNumber;
    private Lock lock;

    public BankAccount(double initialBalance, String accountNumber) {
        this.balance = initialBalance;
        this.accountNumber = accountNumber;
        this.lock = new ReentrantLock();
    }

    /*public synchronized void deposit(double amount){
        balance += amount;
        System.out.println("deposit balance " + balance);
    }

    public synchronized void withdraw(double amount){
        balance -= amount;
        System.out.println("withdraw balance " + balance);
    }*/
    /*public void deposit(double amount){
        synchronized (this) {
            balance += amount;
            System.out.println("deposit balance " + balance);
        }
    }

    public void withdraw(double amount){
        synchronized (this) {
            balance -= amount;
            System.out.println("withdraw balance " + balance);
        }
    }
    */
    //chanllenge 4
    //需要注意两点
    //1。 两个线程 compete 同一个锁，所以只需要一个reentrent lock
    //2 。 需要try finallu statement 来确保lock releas了
    /*public synchronized void deposit(double amount){
        lock.lock();
        try {
            balance += amount;
        }finally {
            lock.unlock();
        }
        System.out.println("deposit balance " + balance);
    }

    public synchronized void withdraw(double amount){
        lock.lock();
        try {
            balance -= amount;
        }finally {
            lock.unlock();
        }
        System.out.println("withdraw balance " + balance);
    }*/

    //challenge 5 try lock
    public void deposit(double amount){
        //challenge 6 make status variable thread safe
        //local variables is safe are stored on the thread stack
        //so each threa will have its own copy
        boolean status = false;

        try{
            if(lock.tryLock(1000, TimeUnit.MILLISECONDS)){
                try{
                    balance += amount;
                }finally{
                    lock.unlock();
                }
            }else{
                System.out.println("deposit could not get the lock");
            }
        }catch(InterruptedException e){
            // do something here
        }

        System.out.println("transaction status = " + status);
    }

    public void withdraw(double amount){

        boolean status = false;

        try{
            if(lock.tryLock(1000, TimeUnit.MILLISECONDS)){
                try{
                    balance -= amount;
                    status = true;
                }finally{
                    lock.unlock();
                }
            }else{
                System.out.println("withdraw could not get the lock");
            }
        }catch(InterruptedException e){
            // do something here
        }
        System.out.println("transaction status = " + status);
    }

    //challenge 3
    //没有必要添加sychronize 因为根本没有阿癌变什么
    public String getAccountNumber(){
        return accountNumber;
    }

    public void printAccountNumber(){
        System.out.println("account number =" + accountNumber);
    }
}
