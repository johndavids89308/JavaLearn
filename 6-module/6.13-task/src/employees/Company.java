package employees;

import java.util.ArrayList;
import java.util.Collections;

public class Company {

    // Сделать метод генерации сотрудников с разными зарплатами +
    // метод нанять +
    // метод уволить +

    // метод getTopSalaryStaff(int count) +
    // метод getLowestSalaryStaff(int count) +
    // которые будут выводить заданное (в параметре count) количество сотрудников
    // с самыми высокими и самыми низкими зарплатами

    protected double fixPartSalary; // Фиксированная зарплата
    protected double profitSelf; // Заработанная сумма для компании
    protected boolean dismiss = false; // Флаг увольнения

    protected static double profit;

    public double getProfit(){
        return profit;
    }

    // Метод генерации случайной зарплаты

    protected double randomSalary(){
        int min = 30000, max = 120000;
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    // Метод генерации сотрудников

    ArrayList<Employee> employeeArrayList = new ArrayList<>();

    public void generateEmployee(){
        for (int i = 0, j = 0; i <= 270; i++, j++){

            if (j > 2) j = 0;

            switch (j){
                case 0: employeeArrayList.add(new SalesManager()); break;
                case 1: employeeArrayList.add(new TopManager()); break;
                case 2: employeeArrayList.add(new Clerk()); break;
            }
        }
    }

    // Получить сотрудника из списка

    public Employee getEmployeeFromList(int num){
        return employeeArrayList.get(num);
    }

    // Получить колличество сотрудников

    public int getCountEmployee(){
        return employeeArrayList.size() - 1;
    }

    // Уволить сотрудника

    public void dismiss(Employee employee){
        employee.setDismiss();
        employeeArrayList.remove(employee);
    }

    // Нанять сотрудника

    public void hire(Employee employee){
        employeeArrayList.add(employee);
    }


    // Самые высокие зарплаты

    public void getTopSalaryStaff(int count){

        Collections.sort(employeeArrayList, new TopSalaryCompanyComparator());

        for (int i = 0; i < count && count <= employeeArrayList.size(); i++){
            System.out.println(i + 1 + " : " + employeeArrayList.get(i).getMonthSalary());
        }
    }

    public void getLowestSalaryStaff(int count){

        Collections.sort(employeeArrayList, new LowestSalaryCompanyComparator());

        for (int i = 0; i < count && count <= employeeArrayList.size(); i++){
            System.out.println(i + 1 + " : " + employeeArrayList.get(i).getMonthSalary());
        }
    }

}
