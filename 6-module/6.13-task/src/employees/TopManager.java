package employees;

//топ-менеджер (фиксированная часть + премия, если доход компании составил
// более 10 миллионов рублей)

public class TopManager extends Company implements Employee{

    private String name;

    public TopManager(String name){
        fixPartSalary = randomSalary();
        this.name = name;
    }

    // Метод определения зарплаты

    @Override
    public double getMonthSalary() {
        if (profit > 100000){
            return fixPartSalary + 1000;
        }
        return fixPartSalary;
    }

    // метод зарабатывания денег

    @Override
    public void working(double money){
        profitSelf += money;
        profit += money;
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
