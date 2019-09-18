package employees;

// операционист (фиксированная зарплата)

public class Clerk extends Company implements Employee {

    private String name;

    public Clerk(String name){
        fixPartSalary = randomSalary();
        this.name = name;
    }

    // Метод определения зарплаты

    @Override
    public double getMonthSalary() {
        return fixPartSalary;
    }

    // метод зарабатывания денег

    @Override
    public void working(double money){
        if (!getDismiss()){
            profitSelf += money;
            profit += money;
        }
    }

    // геттер заработанной работником суммы

    @Override
    public double getProfitSelf(){
        return profitSelf;
    }

    // Получить имя

    @Override
    public String getName(){
        return name;
    }

    // Получить флаг увольнения

    @Override
    public boolean getDismiss(){
        return dismiss;
    }

    // Установить флаг увольнения

    public void setDismiss(){
        dismiss = true;
    }

}
