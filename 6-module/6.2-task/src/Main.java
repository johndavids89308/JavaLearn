import accounts.BankAccount;
import accounts.CardAccount;
import accounts.DepositAccount;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args){

        // Работа с банковским счетом

        BankAccount myBankAccount = new BankAccount();
        myBankAccount.setBalance(100000);
        System.out.println("Баланс составляет: " + myBankAccount.getBalance());
        myBankAccount.cashOut(5000);
        System.out.println("Баланс составляет: " + myBankAccount.getBalance());

        System.out.println();

        // Работа с днпозитным счетом

        DepositAccount myDepositAccount = new DepositAccount(5000);
        myDepositAccount.cashOut(500);
        System.out.println("На счету депозита: " + myDepositAccount.getBalance());

        System.out.println();

        // Работа с карточным счетом

        CardAccount myCardAccount = new CardAccount(1000);
        System.out.println("Баланс на счете карты: " + myCardAccount.getBalance());
        myCardAccount.cashOut(500);
        System.out.println("Снятие средств с карты с вычетом 1%: " + myCardAccount.getBalance());
    }
}
