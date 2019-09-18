package employees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

    private String[] nameList = new String[]{
            "Smith","Smith","Smith","Smith","Smith","Smith","Smith","Smith","Smith","Smith","Smith","Smith","Smith","Smith","Smith","Smith","Smith","Smith","Smith","Smith","Smith","Smith","Smith","Smith","Smith","Smith","Smith","Smith","Neo","Neo","Neo","Neo","Neo","Melissa","Neo","Neo","Neo","Dennis","Mia","Morpheus","Morpheus","Morpheus","Morpheus","Morpheus","Morpheus","Morpheus","Morpheus","Morpheus","Morpheus","Morpheus","Morpheus","Morpheus","Morpheus","Michelle","Sandra","Jesus","Sybil","Jose","Savannah","Trinity","Trinity","Trinity","Trinity","Trinity","Trinity","Trinity","Trinity","Trinity","Trinity","Trinity","Trinity","Trinity","Trinity","Trinity","Trinity","Trinity","Trinity","Trinity","Trinity","Trinity","Trinity","Trinity","Trinity","Trinity","Trinity","Trinity","Trinity","Trinity","Neo","Neo","Neo","Neo","Neo","Neo","Neo","Neo","Neo","Neo","Neo","Neo","Neo","Neo","Neo","Neo","Neo"
    };
    private ArrayList<Employee> employeeArrayList = new ArrayList<>();

    public void generateEmployee(){

        for (int i = 0, j = 0; i <= 270; i++, j++){

            int min = 0, max = 104;
            max -= min;
            int num = (int) (Math.random() * ++max) + min;

            if (j > 2) j = 0;

            switch (j){
                case 0: employeeArrayList.add(new SalesManager(nameList[num])); break;
                case 1: employeeArrayList.add(new TopManager(nameList[num])); break;
                case 2: employeeArrayList.add(new Clerk(nameList[num])); break;
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

        Collections.sort(employeeArrayList, (o1, o2) -> {
            if (o1.getMonthSalary() > o2.getMonthSalary()){
                return -1;
            }
            if (o1.getMonthSalary() < o2.getMonthSalary()){
                return 1;
            }
            return o1.getName().compareTo(o2.getName());
        });

        Employee employee;

        for (int i = 0; i < count && count <= employeeArrayList.size(); i++){
            employee = employeeArrayList.get(i);
            System.out.println(i + 1 + " : " + employee.getName() + " - " + employee.getMonthSalary());
        }
    }

    // Самые низкие зарплаты

    public void getLowestSalaryStaff(int count){

        Collections.sort(employeeArrayList, (o1,o2) -> {
            if (o1.getMonthSalary() > o2.getMonthSalary()){
                return 1;
            }
            if (o1.getMonthSalary() < o2.getMonthSalary()){
                return -1;
            }
            return o1.getName().compareTo(o2.getName());
        }
        );

        Employee employee;

        for (int i = 0; i < count && count <= employeeArrayList.size(); i++){
            employee = employeeArrayList.get(i);
            System.out.println(i + 1 + " : " + employee.getName() + " - " + employee.getMonthSalary());
        }
    }

}
