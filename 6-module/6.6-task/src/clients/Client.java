package clients;

import java.util.Random;

public abstract class Client {
    private long accountNumber;
    protected double balance;

    public Client(){
        long range = 1234567L;
        Random r = new Random();
        accountNumber = (long)(r.nextDouble()*range*range*range);
    }

    public double getBalance(){
        return balance;
    }


    public long getAccountNumber(){
        return accountNumber;
    }

    public abstract void setBalance(double money);
    public  abstract void cashOut(double money);


}
