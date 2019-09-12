import clients.Individual;
import clients.LegalPerson;
import clients.SoleProprietor;

// Реализуйте классы, представляющие клиентов банка:
// абстрактный класс Client, а также классы для физических лиц,
// юридических лиц и индивидуальных предпринимателей.
// У каждого клиента есть расчётный счёт (число), который можно пополнять,
// с которого можно снимать, и баланс на котором можно смотреть.
// Реализовать методы таким образом,
// чтобы у физических лиц пополнение и снятие происходило без комиссии,
// у юридических лиц — снятие с комиссией 1%,
// а у ИП — пополнение с комиссией 1%, если сумма меньше 1000 рублей, и 0,5%,
// если сумма больше либо равна 1000 рублей.

public class Main {
    public static void main(String[] args){
        Individual person1 = new Individual();
        person1.setBalance(400);
        person1.cashOut(200);
        System.out.println("Физическое лицо:");
        System.out.println("Номер счета: " + person1.getAccountNumber());
        System.out.println("Баланс: " + person1.getBalance());

        System.out.println();

        LegalPerson person2 = new LegalPerson();
        person2.setBalance(2000);
        person2.cashOut(1000);
        System.out.println("Юридическое лицо:");
        System.out.println("Номер счета: " + person2.getAccountNumber());
        System.out.println("Баланс: " + person2.getBalance());

        System.out.println();

        SoleProprietor person3 = new SoleProprietor();
        person3.setBalance(2000);
        System.out.println("Индивидуальный предприниматель:");
        System.out.println("Номер счета: " + person3.getAccountNumber());
        System.out.println("Баланс: " + person3.getBalance());
    }
}
