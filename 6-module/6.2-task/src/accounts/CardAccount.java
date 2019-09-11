package accounts;

public class CardAccount extends BankAccount {
    public CardAccount(){
        balance = 0;
    }

    public CardAccount(double balance){
        super(balance);
    }

    public void cashOut(double money){

        double percent = money / 100 * 1;
        money += percent;

        if (balance > 0 && balance >= money){
            balance -= money;
        }else{
            System.out.println("Не достаточно средств!");
        }
    }

}
