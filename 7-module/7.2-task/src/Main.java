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

        // Сортировка

/*        Collections.sort(staff, (o1,o2) -> {
            if (o1.getSalary() > o2.getSalary()){
                return -1;
            }
            if (o1.getSalary() < o2.getSalary()){
                return 1;
            }
            return o1.getName().compareTo(o2.getName());
        });*/

        Collections.sort(staff, Comparator.comparing(Employee::getSalary).reversed()
                .thenComparing(Employee::getName));

        SimpleDateFormat date = new SimpleDateFormat(dateFormat);

        for (int i = 0; i < staff.size(); i++){
            System.out.println(staff.get(i).getName() + " -> " + staff.get(i).getSalary() + " -> "
                    + date.format(staff.get(i).getWorkStart()));
        }

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