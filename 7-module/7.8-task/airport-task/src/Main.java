import com.skillbox.airport.Aircraft;
import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.text.SimpleDateFormat;
import java.util.*;

// Используя библиотеку airport.jar, распечатать время вылета
// и модели самолётов, вылетающие в ближайшие 2 часа.

public class Main {
    public static void main(String[] args){

        // Получаем текущую дату

        Calendar currentDate = GregorianCalendar.getInstance();
        long currentTimestamp = currentDate.getTime().getTime();

        // Получаем дату на 2 часа вперед

        Calendar twoHoursLater = GregorianCalendar.getInstance();
        twoHoursLater.add(Calendar.HOUR_OF_DAY,2);
        System.out.println(twoHoursLater.getTime());
        long twoHoursLaterTimestamp = twoHoursLater.getTime().getTime();

        Airport airport = Airport.getInstance();

        // Вывод - сортировка

        airport.getTerminals().stream().map(Terminal::getFlights)
                .forEach(e -> e.stream()
                        .filter(f -> f.getDate().getTime() >= currentTimestamp &&
                                f.getDate().getTime() <= twoHoursLaterTimestamp

                        )
                        .forEach(System.out::println)

                );
    }
}
