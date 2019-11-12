import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "testtest";

        try{
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT name AS names, MONTH(subscription_date) AS month" +
                    " FROM Courses " +
                    " JOIN Subscriptions ON Courses.id = Subscriptions.course_id;");

            Map<Integer,String> mapData = new HashMap<Integer, String>();
            int i = 0;

            while (resultSet.next()){
                String names = resultSet.getString("names");
                String months = resultSet.getString("month");

                mapData.put(i,names + ":" + months);
                i++;
            }

            Map<String,ArrayList<String>> mapDataTwo = new HashMap<String, ArrayList<String>>();

            for (Map.Entry<Integer,String> entry : mapData.entrySet()){
                String [] nameMonth = entry.getValue().split(":");

                // Запись
                mapDataTwo.put(nameMonth[0], new ArrayList<String>());
            }

            for (Map.Entry<String,ArrayList<String>> entryTwo : mapDataTwo.entrySet()){

                // entryTwo.getKey() - имена курсов
                // entryTwo.getValue() - массивы

                for (Map.Entry<Integer,String> entryThree : mapData.entrySet()){
                    String [] nameMonth = entryThree.getValue().split(":");

                    if (entryTwo.getKey().contains(nameMonth[0])){
                        entryTwo.getValue().add(nameMonth[1]);
                    }

                }
            }

            Map<String, Integer> letters = new HashMap<String, Integer>();

            for (Map.Entry<String,ArrayList<String>> entryTwo : mapDataTwo.entrySet()){

                for (int l = 0; l < entryTwo.getValue().size(); l++){
                    if (entryTwo.getValue().get(0) == entryTwo.getValue().get(l)){

                        for (int p = 0; p < entryTwo.getValue().size(); p++){

                            String tempChar = entryTwo.getValue().get(p);

                            if(!letters.containsKey(tempChar)){
                                letters.put(tempChar, 1);
                            }else {
                                letters.put(tempChar, letters.get(tempChar) + 1);
                            }

                        }

                        System.out.println(entryTwo.getKey() + " : " + entryTwo.getValue());
                    }
                }

                //System.out.println(entryTwo.getKey() + " : " + entryTwo.getValue());
            }

            for (Map.Entry<String, Integer> entry : letters.entrySet()) {
                System.out.println("Буква = " + entry.getKey() + ", Повторений = " + entry.getValue());
            }




            resultSet.close();
            statement.close();
            connection.close();

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
