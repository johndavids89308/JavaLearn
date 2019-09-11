package accounts;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DepositAccount extends BankAccount {
    private Calendar currentDate;
    private long currentTimestamp;
    private long monthLaterTimestamp;

    public DepositAccount(){
        balance = 0;
    }

    public DepositAccount(double balance){
        super(balance);
        updateDate();
    }

    public void setBalance(double balance) { // Положить деньги на счет
        this.balance = balance;
        updateDate();
    }

    // Получить наличные

    public void cashOut(double money){
        if (balance > 0 && balance >= money){
            if (checkDate()){
                balance -= money;
            }else {
                System.out.println("Временное ограничение на снятие средств!");
            }
        }else{
            System.out.println("Не достаточно средств!");
        }
    }

    // Проверка окончания запрета на снятие по дате

    private boolean checkDate(){

        currentDate = GregorianCalendar.getInstance();
        currentTimestamp = currentDate.getTime().getTime();

        if (monthLaterTimestamp - currentTimestamp <= 0){
            return true;
        }else {
            return false;
        }
    }

    private void updateDate(){

        // Текущая дата при пополнении баланса

        currentDate = GregorianCalendar.getInstance();
        currentTimestamp = currentDate.getTime().getTime();

        // Дата на месяц вперед от текущей даты

        Calendar monthLaterDate = new GregorianCalendar(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH) + 1, currentDate.get(Calendar.DAY_OF_WEEK), currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), currentDate.get(Calendar.SECOND));
        monthLaterTimestamp = monthLaterDate.getTime().getTime();
    }

}
