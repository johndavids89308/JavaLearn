package clients;

// Индивидуальный предпрениматель

public class SoleProprietor extends Client {

    @Override
    public void setBalance(double money) {
        if (money < 1000){
            balance += money - money / 100 * 1;
        }else {
            balance += money - money / 100 * 0.5;
        }
    }

    @Override
    public void cashOut(double money) {
        if (balance >= money){
            balance -= money;
        }
    }
}
