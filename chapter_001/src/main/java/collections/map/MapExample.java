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
        System.out.println(first.hashCode() == second.hashCode()); // false
        System.out.println(userMap);
        // Несмотря на то, что по методу equals() объекты считаются равными, их ссылки и, соответственно, hashcode
        // будут различными. Это означает, что карта распихает их по разным bucket'ам, соответствующим их хэшкодам, и
        // значения никак не будут пересекаться
    }
}
