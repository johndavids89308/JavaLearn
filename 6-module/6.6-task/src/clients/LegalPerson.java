package clients;

// Юридическое лицо

public class LegalPerson extends Client {

    @Override
    public void setBalance(double money) {
        balance += money;
    }

    @Override
    public void cashOut(double money) {
        if (balance >= money){
            balance -= money + money / 100 * 1;
        }
    }
}
