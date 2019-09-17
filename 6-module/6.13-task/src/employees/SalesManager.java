package employees;

// менеджер по продажам (зарплата складывается из фиксированной части
// и 5% от заработанных им для компании денег)

public class SalesManager extends Company implements Employee {

    public SalesManager(){
        fixPartSalary = randomSalary();
    }

    // Метод определения зарплаты

    @Override
    public double getMonthSalary() {
        return fixPartSalary + profitSelf / 100 * 5;
    }

    // геттер заработанной работником суммы суммы

    @Override
    public double getProfitSelf(){
        return profitSelf;
    }

    // метод зарабатывания денег

    @Override
    public void working(double money){
        profitSelf +=money;
        profit += money;
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
