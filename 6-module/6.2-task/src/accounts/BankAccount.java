package accounts;

public class BankAccount {
    protected double balance;

    public BankAccount(){
        balance = 0;
    }

    protected BankAccount(double balance){
        this.balance = balance;
    }

    public void setBalance(double balance) { // Положить деньги на счет
        this.balance = balance;
    }

    public double getBalance() { // Показать баланс
        return balance;
    }

    // Получить наличные

    public void cashOut(double money){
        if (balance > 0 && balance >= money){
            balance -= money;
        }else{
            System.out.println("Не достаточно средств!");
        }
    }

}
