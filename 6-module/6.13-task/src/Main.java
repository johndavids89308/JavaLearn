import employees.*;

import java.util.TreeSet;

public class Main {
    public static void main(String[] args){

        System.out.println("Рокфеллер и компания!");

        Company rockefellerCompany = new Company();

        // Генерация списка сотрудников

        rockefellerCompany.generateEmployee();

        System.out.println();

        // Менеджер по продажам

        System.out.println("Менеджер по продажам:");
        System.out.println();

        Employee salesManager = rockefellerCompany.getEmployeeFromList(0);
        System.out.println(salesManager.getProfitSelf());
        salesManager.working(4000);
        System.out.println(salesManager.getProfitSelf());
        System.out.println(salesManager.getMonthSalary());

        System.out.println();

        // Топ менеджер

        System.out.println("Топ менеджер:");
        System.out.println();

        Employee topManager = rockefellerCompany.getEmployeeFromList(1);
        System.out.println(topManager.getProfitSelf());
        topManager.working(5000);
        System.out.println(topManager.getProfitSelf());
        System.out.println(topManager.getMonthSalary());

        System.out.println();

        // Операционист

        System.out.println("Операционист:");
        System.out.println();

        Employee clerk = rockefellerCompany.getEmployeeFromList(2);
        System.out.println(clerk.getProfitSelf());
        clerk.working(3000);
        System.out.println(clerk.getProfitSelf());
        System.out.println(clerk.getMonthSalary());



        System.out.println();
        System.out.println("Общая прибыль компании:");
        System.out.println();

        System.out.println(rockefellerCompany.getProfit());

        // Уволить сотрудника

        System.out.println();
        System.out.println("Увольнение сотрудника:");
        System.out.println();

        System.out.println("Всего сотрудников: " + rockefellerCompany.getCountEmployee());
        rockefellerCompany.dismiss(clerk);
        System.out.println("Всего сотрудников: " + rockefellerCompany.getCountEmployee());

        // Найм сотрудника

        System.out.println();
        System.out.println("Найм сотрудника:");
        System.out.println();

        rockefellerCompany.hire(new Clerk("Tony Montana"));
        System.out.println("Всего сотрудников: " + rockefellerCompany.getCountEmployee());


        // Вывод самых высоких зарплат

        System.out.println();
        System.out.println("Вывод самых высоких зарплат:");
        System.out.println();

        rockefellerCompany.getTopSalaryStaff(10);

        // Вывод самых маленьких зарплат

        System.out.println();
        System.out.println("Вывод самых маленьких зарплат:");
        System.out.println();

        rockefellerCompany.getLowestSalaryStaff(10);

        //rockefellerCompany.getAllSalary();

    }
}
