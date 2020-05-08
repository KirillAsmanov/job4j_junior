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
        System.out.println(first.equals(second)); // false
        System.out.println(first.hashCode() == second.hashCode()); // true
        System.out.println(userMap);
        // Здесь получилось, что хеш коды у двух объектов разные, но сами объекты не считаются равными по методу equals.
        // В таком случае они добавятся в карту в один и тот же bucket (произойдет коллизия). Но значения не
        // будут перезаписаны, а будут хранится в этом bucket'е в виде связанного списка, что, при большом количестве
        // таковых сведет на нет преимущества карты в производительности
    }
}
