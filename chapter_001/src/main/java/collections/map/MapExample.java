package collections.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class MapExample {
    public static void main(String[] args) {
        Map<User, String> userMap = new HashMap<>();
        User first = new User("Кирилл", 1, new GregorianCalendar(1997, Calendar.SEPTEMBER, 5));
        User second = new User("Кирилл", 1, new GregorianCalendar(1997, Calendar.SEPTEMBER, 5));
        userMap.put(first, "value1");
        userMap.put(second, "value2");
        System.out.println(first.equals(second)); // true
        System.out.println(first.hashCode() == second.hashCode()); // true
        System.out.println(userMap);
        // В данном случае переопределены оба метода. Карта видит, что два объекта имеют один хэшкод и равны по
        // методу equals() и производит перезапись значений.
    }
}
