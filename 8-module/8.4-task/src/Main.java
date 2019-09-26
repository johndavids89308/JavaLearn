import java.util.Arrays;
import java.util.Scanner;

public class Main
{
    private static String addCommand = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    private static String commandExamples = "\t" + addCommand + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static String commandError = "Wrong command! Available command examples: \n" +
            commandExamples;
    private static String helpText = "Command examples:\n" + commandExamples;

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();
        for(;;)
        {
            String command = scanner.nextLine();
            String[] tokens = command.split("\\s+", 2);

            // Обработка неверного email и номера телефона

            try {

                if (tokens[0].equals("add")) {

                    // Добавил обработку исключений

                    try {
                        executor.addCustomer(tokens[1]);
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        System.out.println("Неверный формат данных!\n" +
                                "add Василий Петров vasily.petrov@gmail.com +79215637722"
                        );
                    }
                } else if (tokens[0].equals("list")) {
                    executor.listCustomers();
                } else if (tokens[0].equals("remove")) {
                    // Добавил обработку исключений

                    try {
                        executor.removeCustomer(tokens[1]);
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        System.out.println("Неверный формат!\n remove Василий Петров");
                    }
                } else if (tokens[0].equals("count")) {
                    System.out.println("There are " + executor.getCount() + " customers");
                } else if (tokens[0].equals("help")) {
                    System.out.println(helpText);
                } else {
                    System.out.println(commandError);
                }
            }catch (IllegalArgumentException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}
