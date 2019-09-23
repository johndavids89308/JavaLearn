import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main
{
    private static String staffFile = "data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args)
    {
        ArrayList<Employee> staff = loadStaffFromFile();


        // В проекте с сотрудниками с помощью Stream API
        // рассчитать максимальную зарплату тех, кто пришёл в 2017 году.

        System.out.println("\nРассчитать максимальную зарплату тех, кто пришёл в 2017 году.\n");

        SimpleDateFormat finalDate = new SimpleDateFormat("yyyy");
        staff.stream().sorted(Comparator.comparing(Employee::getSalary))
                .sorted(Comparator.comparing(Employee::getWorkStart))
                .filter(e -> finalDate.format(e.getWorkStart()).equals("2017") )
                .forEach(System.out::println);
    }

    private static ArrayList<Employee> loadStaffFromFile()
    {
        ArrayList<Employee> staff = new ArrayList<>();
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for(String line : lines)
            {
                String[] fragments = line.split("\t");
                if(fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(
                    fragments[0],
                    Integer.parseInt(fragments[1]),
                    (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }
}