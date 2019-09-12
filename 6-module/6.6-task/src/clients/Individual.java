package clients;

// Физическое лицо

public class Individual extends Client {

    @Override
    public void setBalance(double money) {
        balance += money;
    }

    @Override
    public void cashOut(double money) {
        if (balance >= money){
            balance -= money;
        }
    }


}
