package Assignment3.PartII;
public class Account {
    static int obid = 1;
    double balance;
    int id;

    public Account(){
        this.balance = 1000.0;
        this.id = obid++;
    }

    public Account(double startingBalance){
        this.balance = startingBalance;
        this.id = obid++;

    }


    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount){
        if(balance >= amount){
            balance -= amount;
            return true;
        }else{
            return false;

        }

    }

    public void deposit(double amount){
        balance += amount;
    }




}
